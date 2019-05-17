package ru.job4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.models.*;
import ru.job4j.repository.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@SessionAttributes({"idAd", "adUser"})
public class MainController {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CarModelRepository carModelRepository;
    @Autowired
    private BrandModelRepository brandModelRepository;
    @Autowired
    private BodyRepository bodyRepository;
    @Autowired
    private ColourRepository colourRepository;
    @Autowired
    private DriveRepository driveRepository;
    @Autowired
    private EngineRepository engineRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TransmissionRepository transmissionRepository;
    @Autowired
    private WheelRepository wheelRepository;
    @Autowired
    private AdRepository adRepository;

    private Picture addedPicture = new Picture();

    @GetMapping(value = "/index")
    public String showItems(ModelMap model) {
        return "index";
    }

    @ResponseBody
    @GetMapping(value = "/picture-{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable long id) throws IOException {
        byte[] image = null;
        ;
       if(id != 0) {
            Ad ad = adRepository.findById(id).get();
            if (ad.getPicture() == null) {
                ad.setPicture(new Picture().getImage());
            }
            image = getNoPictureIfAbsent(ad.getPicture());
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
    @GetMapping(value = "/jsonData-{jsonToRequest}-{param}", produces = "application/json")
    public ResponseEntity<String> createJson(@PathVariable String jsonToRequest, @PathVariable String param) throws Exception {
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List items = null;
        switch (jsonToRequest) {
            case "shortAd":
                    items = (List) adRepository.findAll();
                break;
            case "table":
                try {
                    items = (List)getCrudRepository(param).findAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "modelsOfBrand":
                Brand brand = brandRepository.findById(Long.parseLong(param)).get();
                items = brandModelRepository.findAllByBrand(brand);
                break;
                default:
                    items = null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return new ResponseEntity<String>(mapper.writeValueAsString(items), httpHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/add{id}")
    public String addAd(ModelMap modelMap, @PathVariable Long id) {
        Ad ad = adRepository.findById(id).orElse(new Ad());

        if (addedPicture.getId() != id) {
            addedPicture.setId(id);
            Picture pic = new Picture(ad.getPicture());
            if (pic != null ) {
                addedPicture.setImage(pic.getImage());
            }
        }
        modelMap.addAttribute(ad);
        modelMap.addAttribute("idAd", new IdAd(id));
        if (ad.getAdUser() == null) {
            ad.setAdUser(new AdUser(1));
        }
        modelMap.addAttribute("adUser", ad.getAdUser());
        return "editCar";
    }

    @PostMapping(value = "/add")
    public String addAd(ModelMap modelMap, @ModelAttribute Ad ad, @ModelAttribute IdAd idAd, @ModelAttribute AdUser adUser,
                        @RequestParam long brand, @RequestParam long model) {
        ad.setId(idAd.getId());
        ad.setAdUser(adUser);
        Brand br = brandRepository.findById(brand).get();
        CarModel md = carModelRepository.findById(model).get();
        BrandModel bm = brandModelRepository.findByBrandAndCarModel(br, md);
        ad.setBrandModel(bm);
        ad.setPicture(addedPicture.getImage());
        addedPicture.setImage(null);
        addedPicture.setId(0);
        adRepository.save(ad);

        return "redirect:index";
    }

    @PostMapping("/uploadFile")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   HttpServletRequest request) {
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            if (bytes.length > 0) {
                addedPicture.setImage(bytes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    public CrudRepository<?, Long> getCrudRepository(String repoName) {
        Map<String, CrudRepository<?, Long>> cruds = new HashMap();
        cruds.put("ad", adRepository);
        cruds.put("brands", brandRepository);
        cruds.put("brandModel", brandModelRepository);
        cruds.put("carModel", carModelRepository);
        cruds.put("wheel", wheelRepository);
        cruds.put("drive", driveRepository);
        cruds.put("colour", colourRepository);
        cruds.put("body", bodyRepository);
        cruds.put("engine", engineRepository);
        cruds.put("transmission", transmissionRepository);
        return cruds.get(repoName);
    }
}
