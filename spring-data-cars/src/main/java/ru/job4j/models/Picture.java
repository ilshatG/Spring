package ru.job4j.models;

import org.springframework.stereotype.Component;

public class Picture {
    private long id;
    private byte[] image;

    public Picture() {
    }

    public Picture(byte[] image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
