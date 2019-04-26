package ru.job4j.models;

import java.util.Objects;

public class Picture {
    private long id;
    private byte[] image;

    public Picture() {
    }

    public Picture(byte[] image) {
        this.image = image;
    }

    public Picture(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return id == picture.id;
    }
}
