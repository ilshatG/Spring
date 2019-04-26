package ru.job4j;

import org.springframework.util.ResourceUtils;
import ru.job4j.controller.DAO.Dao;
import ru.job4j.controller.DAO.HibernateDao;
import ru.job4j.models.BrandModel;
import ru.job4j.models.Wheel;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;

public class ConsoleApp {

    public static void main(String[] args) throws Exception {
        Dao dao = new Dao(new HibernateDao());
        BrandModel brandModel = new BrandModel(1,1);
        brandModel = dao.get(brandModel, 3);
        System.out.println("brand-" + brandModel.getBrandId());
        System.out.println("model-" + brandModel.getModelId());

        Wheel wheel = new Wheel();
        wheel = dao.get(wheel, 2);
        System.out.println("wheel - " + wheel.getName());
    }
}

