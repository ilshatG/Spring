package ru.job4j.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Advertisement of car selling
 */

public class Ad {
    private long id;
    private BrandModel brandModel;
    private Wheel wheel;
    private Drive drive;
    private Colour colour;
    private CarBody carBody;
    private EngineType engineType;
    private Transmission transmission;
    private User user;
    private int owners;
    private int engineVolume;
    private int carMeleage;
    private int year;
    private int powerOfEngine;
    private String Description;
    private Timestamp published = new Timestamp(System.currentTimeMillis());
    private double price;
    private boolean sale;
    private Picture picture;

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public long getBrandId() {
        return brandModel.getBrandId();
    }

    public long getModelId() {
        return brandModel.getModelId();
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

    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return id == ad.id &&
                owners == ad.owners &&
                engineVolume == ad.engineVolume &&
                carMeleage == ad.carMeleage &&
                year == ad.year &&
                powerOfEngine == ad.powerOfEngine &&
                Double.compare(ad.price, price) == 0 &&
                sale == ad.sale &&
                Objects.equals(brandModel, ad.brandModel) &&
                Objects.equals(wheel, ad.wheel) &&
                Objects.equals(drive, ad.drive) &&
                Objects.equals(colour, ad.colour) &&
                Objects.equals(carBody, ad.carBody) &&
                Objects.equals(engineType, ad.engineType) &&
                Objects.equals(transmission, ad.transmission) &&
                Objects.equals(user, ad.user) &&
                Objects.equals(Description, ad.Description) &&
                Objects.equals(published, ad.published);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandModel, wheel, drive, colour, carBody, engineType, transmission, user, owners, engineVolume, carMeleage, year, powerOfEngine, Description, published, price, sale);
    }
}
