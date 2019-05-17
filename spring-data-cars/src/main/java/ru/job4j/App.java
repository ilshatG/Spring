package ru.job4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;
import ru.job4j.models.*;
import ru.job4j.repository.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@ServletComponentScan
@EntityScan(basePackages={"ru.job4j.models"})
@EnableJpaRepositories(basePackages={"ru.job4j.repository"})

public class App extends SpringBootServletInitializer implements CommandLineRunner {
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

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Brand toyotaBrand = new Brand("Toyota");
        brandRepository.save(toyotaBrand);
        Brand nissanBrand = new Brand("Nissan");
        brandRepository.save(nissanBrand);
        CarModel camry = new CarModel("Camry");
        carModelRepository.save(camry);
        CarModel corolla = new CarModel("Corolla");
        carModelRepository.save(corolla);
        CarModel rav4 = new CarModel("RAV-4");
        carModelRepository.save(rav4);

        BrandModel toyotaCamry = new BrandModel(toyotaBrand, camry);
        BrandModel toyotaCorolla = new BrandModel(toyotaBrand, corolla);
        BrandModel toyotaRav4 = new BrandModel(toyotaBrand, rav4);
        brandModelRepository.save(toyotaCamry);
        brandModelRepository.save(toyotaCorolla);
        brandModelRepository.save(toyotaRav4);

        CarModel teana = new CarModel("Teana");
        CarModel almera = new CarModel("Almera");
        CarModel patrol = new CarModel("Patrol");
        carModelRepository.save(teana);
        carModelRepository.save(almera);
        carModelRepository.save(patrol);

        BrandModel nissanTeana = new BrandModel(nissanBrand, teana);
        BrandModel nissanAlmera = new BrandModel(nissanBrand, almera);
        BrandModel nissanPatrol = new BrandModel(nissanBrand, patrol);
        brandModelRepository.save(nissanTeana);
        brandModelRepository.save(nissanAlmera);
        brandModelRepository.save(nissanPatrol);

        Body sedan = new Body("sedan");
        Body hatchBack = new Body("hatch back");
        Body estate = new Body("estate");
        bodyRepository.save(sedan);
        bodyRepository.save(hatchBack);
        bodyRepository.save(estate);

        Colour black = new Colour("black");
        Colour white = new Colour("white");
        Colour red = new Colour("red");
        Colour silver = new Colour("silver");
        colourRepository.save(black);
        colourRepository.save(white);
        colourRepository.save(red);
        colourRepository.save(silver);

        Drive front = new Drive("front");
        Drive rear = new Drive("rear");
        driveRepository.save(front);
        driveRepository.save(rear);

        Engine gasoline = new Engine("gosoline");
        Engine diesel = new Engine("diesel");
        engineRepository.save(gasoline);
        engineRepository.save(diesel);

        Wheel left = new Wheel("left");
        Wheel right = new Wheel("right");
        wheelRepository.save(left);
        wheelRepository.save(right);

        Transmission automatic = new Transmission("automatic");
        Transmission manual = new Transmission("manual");
        Transmission variator = new Transmission("variator");
        transmissionRepository.save(automatic);
        transmissionRepository.save(manual);
        transmissionRepository.save(variator);

        Role admin = new Role("admin");
        roleRepository.save(admin);

        AdUser adUser = new AdUser("adUser");
        adUser.setRole(admin);
        userRepository.save(adUser);

        Ad ad1 = new Ad();
        ad1.setBody(sedan);
        ad1.setBrandModel(toyotaCamry);
        ad1.setColour(black);
        ad1.setDrive(front);
        ad1.setEngine(gasoline);
        ad1.setTransmission(automatic);
        ad1.setWheel(left);
        ad1.setAdUser(adUser);
        ad1.setOwners(1);
        ad1.setEngineVolume(2500);
        ad1.setCarMeleage(100_000);
        ad1.setYear(2017);
        ad1.setPowerOfEngine(170);
        ad1.setDescription("Wonderful car");
        ad1.setPrice(900_000);
        File pictureFile = null;
        try {
            pictureFile = ResourceUtils.getFile("classpath:toyota-camry.jpg");
            ad1.setPicture(Files.readAllBytes(pictureFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        adRepository.save(ad1);

        Ad ad2 = new Ad();
        ad2.setBody(sedan);
        ad2.setBrandModel(toyotaCorolla);
        ad2.setColour(white);
        ad2.setDrive(front);
        ad2.setEngine(gasoline);
        ad2.setTransmission(automatic);
        ad2.setWheel(left);
        ad2.setAdUser(adUser);
        ad2.setOwners(2);
        ad2.setEngineVolume(2000);
        ad2.setCarMeleage(80_000);
        ad2.setYear(2016);
        ad2.setPowerOfEngine(120);
        ad2.setDescription("speed as shark");
        ad2.setPrice(700_000);
        try {
            pictureFile = ResourceUtils.getFile("classpath:toyota-corolla.jpg");
            ad2.setPicture(Files.readAllBytes(pictureFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        adRepository.save(ad2);

        Ad ad3 = new Ad();
        ad3.setBody(estate);
        ad3.setBrandModel(nissanPatrol);
        ad3.setColour(silver);
        ad3.setDrive(rear);
        ad3.setEngine(diesel);
        ad3.setTransmission(automatic);
        ad3.setWheel(left);
        ad3.setAdUser(adUser);
        ad3.setOwners(1);
        ad3.setEngineVolume(3000);
        ad3.setCarMeleage(90_000);
        ad3.setYear(2018);
        ad3.setPowerOfEngine(270);
        ad3.setDescription("powerful car");
        ad3.setPrice(1700_000);
        try {
            pictureFile = ResourceUtils.getFile("classpath:nissan-patrol.jpg");
            ad3.setPicture(Files.readAllBytes(pictureFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        adRepository.save(ad3);

        Ad ad4 = new Ad();
        ad4.setBody(estate);
        ad4.setBrandModel(nissanPatrol);
        ad4.setColour(silver);
        ad4.setDrive(rear);
        ad4.setEngine(diesel);
        ad4.setTransmission(automatic);
        ad4.setWheel(left);
        ad4.setAdUser(adUser);
        ad4.setOwners(1);
        ad4.setEngineVolume(3200);
        ad4.setCarMeleage(99_000);
        ad4.setYear(2017);
        ad4.setPowerOfEngine(295);
        ad4.setDescription("excellent powerful car");
        ad4.setPrice(1_600_000);

        adRepository.save(ad4);

    }
}

