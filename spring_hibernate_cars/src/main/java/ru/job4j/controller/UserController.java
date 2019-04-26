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
import ru.job4j.controller.DAO.Dao;
import ru.job4j.models.*;


import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class UserController {
    @Autowired
    Dao dao;

    @Autowired
    ServletContext servletContext;

    private static List<User> users = new CopyOnWriteArrayList<>();

    {
        //users = dao.getListFromSQL("Select name from users");
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showItems(ModelMap model) {
        model.addAttribute("index", this.users);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/picture-{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable long id) throws IOException {
        byte[] image;
        Picture picture = dao.get(new Picture(), id);
        return picture.getImage();
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
                    items = dao.getListFromSQL("SELECT models.id, name from models " +
                            "LEFT OUTER JOIN brand_models ON brand_models.model_id = models.id " +
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

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String addItem(@ModelAttribute User user) {
        this.users.add(user);
        return "redirect:users.do";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addAd(ModelMap modelMap) {
        Ad ad = new Ad();
        ad = dao.get(ad, 3);
        long modelId = ad.getBrandModel().getModelId();
        modelMap.addAttribute(ad);
        return "editCar";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAd(@ModelAttribute Ad ad) {
        dao.add(ad);
        return "redirect:index.do";
    }
}
