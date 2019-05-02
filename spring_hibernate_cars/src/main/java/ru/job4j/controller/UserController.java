package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.job4j.controller.DAO.Dao;
import ru.job4j.models.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


@Controller
@SessionAttributes({"id", "user"})
public class UserController {
    @Autowired
    private Dao dao;


    @Autowired
    private AddedPicture addedPicture;

//    private static String UPLOADED_FOLDER = "C://temp//";

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showItems(ModelMap model) {
//        model.addAttribute("index", this.users);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/picture-{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable long id) throws IOException {
        byte[] image;

        if(id != 0) {
            Picture picture = dao.get(new Picture(), id);
            if (picture == null) {
                picture = new Picture();
            }
            image = getNoPictureIfAbsent(picture.getImage());
        } else {
            image = getNoPictureIfAbsent(addedPicture.getImage());
        }
        return image;
    }

    //показать изображение фиги, если нет реального рисунка
    private byte[] getNoPictureIfAbsent(byte[] imageToShow) throws IOException {
        byte[] result = imageToShow;
        if (imageToShow == null || imageToShow.length == 0) {
            File pictureFile = ResourceUtils.getFile("classpath:noPhoto.jpg");
            result = Files.readAllBytes(pictureFile.toPath());
        }
        return result;
    }

    /**
     * json dispatcher
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/jsonData-{jsonToRequest}-{param}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> createJson(@PathVariable String jsonToRequest, @PathVariable String param) throws Exception {
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List items = null;
        switch (jsonToRequest) {
            case "shortAd":
                File sqlFile = ResourceUtils.getFile("classpath:mainJsonSql.sql");
                String query = new String(Files.readAllBytes(sqlFile.toPath()));
                items = dao.getListFromSQL(query);
                break;
            case "table":
                String orderByName = " ORDER BY name";
               if (param.equals("ad")) {
                    orderByName = "";
                }
                try {
                    items = dao.getListFromSQL("SELECT * from " + param + orderByName);
                } catch (Exception e) {

                }
                break;
            case "modelsOfBrand":
                try {
                    items = dao.getListFromSQL("SELECT car_models.id, name from car_models " +
                            "LEFT OUTER JOIN brand_models ON brand_models.model_id = car_models.id " +
                            "WHERE brand_models.brand_id=" + param);
                } catch (Exception e) {

                }
                break;
                default:
                    items = null;
        }

        ObjectMapper mapper = new ObjectMapper();

        return new ResponseEntity<String>(mapper.writeValueAsString(items), httpHeaders, HttpStatus.OK);
    }


    @RequestMapping(value = "/add{id}", method = RequestMethod.GET)
    public String addAd(ModelMap modelMap, @PathVariable Long id) {
        Ad ad = new Ad();
        ad = dao.get(ad, id);
        if(ad ==null) {
            ad = new Ad();
        }

        if (addedPicture.getId() != id) {
            addedPicture.setId(id);
            Picture pic = dao.get(new Picture(), id);
            if (pic != null ) {
                addedPicture.setImage(pic.getImage());
            }
        }
        modelMap.addAttribute(ad);
        modelMap.addAttribute("id", new Id(id));
        if (ad.getUser() == null) {
            ad.setUser(new User(1));
        }
        modelMap.addAttribute("user", ad.getUser());
        return "editCar";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAd(ModelMap modelMap, @ModelAttribute Ad ad, @ModelAttribute Id id, @ModelAttribute User user,
                         @RequestParam long brand, @RequestParam long model) {
        ad.setId(id.getId());
        ad.setUser(user);
        List<Map<String, BigInteger>> bm = dao.getListFromSQL("SELECT id from brand_models where brand_id=" +
                brand + " and model_id=" + model);
        long lid = bm.get(0).entrySet().iterator().next().getValue().longValue();
        ad.setBrandModel(new BrandModel(lid));
        dao.add(ad);
        if (addedPicture.getImage().length > 0) {
            addedPicture.setId(ad.getId());
            dao.add(new Picture(ad.getId(), addedPicture.getImage()));
            addedPicture.setImage(null);
            addedPicture.setId(0);
        }
        return "redirect:index.do";
    }

    //@RequestMapping(value = "/upload", method = RequestMethod.POST)
    @PostMapping("/uploadFile") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   HttpServletRequest request) {

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            if (bytes.length > 0) {
                addedPicture.setImage(bytes);
            }
            //Path path = Paths.get(UPLOADED_FOLDER + "test.jpg"/*file.getOriginalFilename()*/);
            //Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
