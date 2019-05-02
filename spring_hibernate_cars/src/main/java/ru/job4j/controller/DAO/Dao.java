package ru.job4j.controller.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import ru.job4j.models.*;

import java.io.File;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Component
public class Dao {
    private final Store store;
    private static int instanceCounter;

    @Autowired
    public Dao(final Store store) throws Exception{
        this.store = store;
        if(instanceCounter++ == 0) {
            addData();
        }
    }

    public <T> boolean add(T t) {
        return store.add(t);
    }

    public void update(Ad item) {
        store.update(item);
    }

    public void delete(Ad item) {
        store.delete(item);
    }

    public Map<Long, Ad> getAll() {
        return store.getAll();
    }

    public User currentUser(String login, String password) {
        return null;
    }

    public List getListFromSQL(String query) {
        return store.getListFromSQL(query);
    }

    public <T> List<T> getListFromSQL(String query, Class<T> tClass) {
        return store.getListFromSQL(query, tClass);
    }

    public <T> T get(T t, long id) {
        return store.get(t, id);
    }

    private void addData() throws Exception{
        store.add(new Brand("Toyota"));

        store.add(new CarModel("Camry"));
        store.add(new CarModel("Corolla"));
        store.add(new CarModel("RAV-4"));

        store.add(new Brand("Nissan"));

        store.add(new CarModel("Patrol"));
        store.add(new CarModel("Teana"));
        store.add(new CarModel("Quashqai"));

        store.add(new BrandModel(1, 1));
        store.add(new BrandModel(1, 2));
        store.add(new BrandModel(1, 3));

        store.add(new BrandModel(2, 4));
        store.add(new BrandModel(2, 5));
        store.add(new BrandModel(2, 6));

        store.add(new Wheel("left"));
        store.add(new Wheel("right"));

        store.add(new Drive("front"));
        store.add(new Drive("rear"));
        store.add(new Drive("4WD"));

        store.add(new Colour("white"));
        store.add(new Colour("black"));
        store.add(new Colour("red"));
        store.add(new Colour("silver"));

        store.add(new CarBody("hatchback"));
        store.add(new CarBody("sedan"));
        store.add(new CarBody("estate"));

        store.add(new EngineType("gasoline"));
        store.add(new EngineType("diesel"));

        store.add(new Transmission("automatic"));
        store.add(new Transmission("manual"));
        store.add(new Transmission("variator"));

        Role adminRole = new Role("administraror");
        Role userRole = new Role("user");
        store.add(adminRole);
        store.add(userRole);

        User adminUser = new User("Abraham",
                "Lincoln",
                "+7-9058-44-83-00",
                "admin",
                "admin",
                "Philadelphia",
                new Timestamp(System.currentTimeMillis()),
                adminRole);

        User plainUser = new User("Benjamin",
                "Franklin",
                "+7-9058-44-83-02",
                "user",
                "user",
                "Philadelphia",
                new Timestamp(System.currentTimeMillis()),
                userRole);

        store.add(adminUser);
        store.add(plainUser);

        File pictureFile = ResourceUtils.getFile("classpath:toyota-camry.jpg");
        byte[] image = Files.readAllBytes(pictureFile.toPath());
        Picture picture = new Picture(1, image);

        store.add(picture);

        pictureFile = ResourceUtils.getFile("classpath:toyota-corolla.jpg");
        image = Files.readAllBytes(pictureFile.toPath());
        store.add(new Picture(2, image));

        pictureFile = ResourceUtils.getFile("classpath:nissan-patrol.jpg");
        image = Files.readAllBytes(pictureFile.toPath());
        store.add(new Picture(3, image));

        Ad ad = new Ad();
        ad.setPicture(new Picture(1));
        ad.setBrandModel(new BrandModel(1));
        ad.setWheel(new Wheel(1));
        ad.setDrive(new Drive(2));
        ad.setColour(new Colour(2));
        ad.setCarBody(new CarBody(2));
        ad.setEngineType(new EngineType(1));
        ad.setTransmission(new Transmission(1));
        ad.setUser(plainUser);
        ad.setOwners(1);
        ad.setEngineVolume(3500);
        ad.setCarMeleage(10_000);
        ad.setYear(2014);
        ad.setPowerOfEngine(270);
        ad.setDescription("Nice car.");
        ad.setPublished(new Timestamp(System.currentTimeMillis()));
        ad.setPrice(100_000.0);
        ad.setSale(false);

        store.add(ad);


        Ad ad2 = new Ad();
        ad2.setBrandModel(new BrandModel(2));
        ad2.setWheel(new Wheel(1));
        ad2.setDrive(new Drive(1));
        ad2.setColour(new Colour(1));
        ad2.setCarBody(new CarBody(2));
        ad2.setEngineType(new EngineType(1));
        ad2.setTransmission(new Transmission(1));
        ad2.setUser(plainUser);
        ad2.setOwners(1);
        ad2.setEngineVolume(2500);
        ad2.setCarMeleage(80_000);
        ad2.setYear(2015);
        ad2.setPowerOfEngine(230);
        ad2.setDescription("Excellent car.");
        ad2.setPublished(new Timestamp(System.currentTimeMillis()));
        ad2.setPrice(100_000.0);
        ad2.setSale(false);
        
        store.add(ad2);

        Ad ad3 = new Ad();
        ad3.setBrandModel(new BrandModel(4));
        ad3.setWheel(new Wheel(1));
        ad3.setDrive(new Drive(1));
        ad3.setColour(new Colour(1));
        ad3.setCarBody(new CarBody(3));
        ad3.setEngineType(new EngineType(1));
        ad3.setTransmission(new Transmission(1));
        ad3.setUser(plainUser);
        ad3.setOwners(1);
        ad3.setEngineVolume(2500);
        ad3.setCarMeleage(80_000);
        ad3.setYear(2015);
        ad3.setPowerOfEngine(230);
        ad3.setDescription("The car.");
        ad3.setPublished(new Timestamp(System.currentTimeMillis()));
        ad3.setPrice(100_000.0);
        ad3.setSale(false);

        store.add(ad3);

        Ad ad4 = new Ad();
        ad4.setBrandModel(new BrandModel(4));
        ad4.setWheel(new Wheel(1));
        ad4.setDrive(new Drive(1));
        ad4.setColour(new Colour(2));
        ad4.setCarBody(new CarBody(3));
        ad4.setEngineType(new EngineType(1));
        ad4.setTransmission(new Transmission(1));
        ad4.setUser(plainUser);
        ad4.setOwners(1);
        ad4.setEngineVolume(2800);
        ad4.setCarMeleage(88_000);
        ad4.setYear(2011);
        ad4.setPowerOfEngine(230);
        ad4.setDescription("The old car.");
        ad4.setPublished(new Timestamp(System.currentTimeMillis()));
        ad4.setPrice(102_000.0);
        ad4.setSale(false);

        store.add(ad4);
    }
}
