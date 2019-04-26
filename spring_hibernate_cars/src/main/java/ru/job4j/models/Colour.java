package ru.job4j.models;

import java.util.Objects;

/**
 * car's colour
 */
public class Colour {
    private long id;
    private String name;

    public Colour() {
    }

    public Colour(String name) {
        this.name = name;
    }

    public Colour(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colour colour = (Colour) o;
        return id == colour.id &&
                Objects.equals(name, colour.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
