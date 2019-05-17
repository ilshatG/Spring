package ru.job4j.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "brand_model_id")
    @JsonIgnore
    private BrandModel brandModel;
    @ManyToOne
    @JoinColumn(name = "wheel_id")
    @JsonIgnore
    private Wheel wheel;
    @ManyToOne
    @JoinColumn(name = "drive_id")
    @JsonIgnore
    private Drive drive;
    @ManyToOne
    @JoinColumn(name = "colour_id")
    @JsonIgnore
    private Colour colour;
    @ManyToOne
    @JoinColumn(name = "body_id")
    @JsonIgnore
    private Body body;
    @ManyToOne
    @JoinColumn(name = "engine_id")
    @JsonIgnore
    private Engine engine;
    @ManyToOne
    @JoinColumn(name = "transmission_id")
    @JsonIgnore
    private Transmission transmission;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AdUser adUser;
    private int owners;
    @JsonProperty("engine_volume")
    private int engineVolume;
    @JsonProperty("car_meleage")
    private int carMeleage;
    private int year;
    @JsonProperty("power_of_engine")
    private int powerOfEngine;
    private String Description;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy")
    private Timestamp published = new Timestamp(System.currentTimeMillis());
    private double price;
    private boolean sale;
    @JsonIgnore
    private byte[] picture;

    public Ad() {
    }
    @JsonGetter("brand")
    public String getBrandName() {
        return brandModel.getBrand().getName();
    }

    @JsonGetter("model")
    public String getModel() {
        return brandModel.getCarModel().getName();
    }

    @JsonGetter("transmission")
    public String getTransmissionName() {
        return transmission.getName();
    }

    @JsonGetter("car_body")
    public String getCarBodyName() {
        return body.getName();
    }

    @JsonGetter("drive")
    public String getDriveName() {
        return drive.getName();
    }

    @JsonGetter("fuel")
    public String getFuel() {
        return engine.getName();
    }

    public long getBrandId() {
        return brandModel.getBrand().getId();
    }

    public long getModelId() {
        return brandModel.getCarModel().getId();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BrandModel getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(BrandModel brandModel) {
        this.brandModel = brandModel;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public AdUser getAdUser() {
        return adUser;
    }

    public void setAdUser(AdUser adUser) {
        this.adUser = adUser;
    }

    public int getOwners() {
        return owners;
    }

    public void setOwners(int owners) {
        this.owners = owners;
    }

    public int getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(int engineVolume) {
        this.engineVolume = engineVolume;
    }

    public int getCarMeleage() {
        return carMeleage;
    }

    public void setCarMeleage(int carMeleage) {
        this.carMeleage = carMeleage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPowerOfEngine() {
        return powerOfEngine;
    }

    public void setPowerOfEngine(int powerOfEngine) {
        this.powerOfEngine = powerOfEngine;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Timestamp getPublished() {
        return published;
    }

    public void setPublished(Timestamp published) {
        this.published = published;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
