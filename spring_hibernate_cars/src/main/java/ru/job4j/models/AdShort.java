package ru.job4j.models;

public class AdShort {
    private long id;
    private String brand;
    private String model;
    private long year;
    private double price;
    private long meleage;
    private long volume;
    private String transmission;
    private long power;
    private String body;
    private String drive;
    private String fuel;

    public AdShort() {
    }

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public long getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public long getMeleage() {
        return meleage;
    }

    public long getVolume() {
        return volume;
    }

    public String getTransmission() {
        return transmission;
    }

    public long getPower() {
        return power;
    }

    public String getBody() {
        return body;
    }

    public String getDrive() {
        return drive;
    }

    public String getFuel() {
        return fuel;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMeleage(long meleage) {
        this.meleage = meleage;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setPower(long power) {
        this.power = power;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
}
