package ru.job4j.models;

import java.util.Objects;

/**
 * Transmission. КПП
 */
public class Transmission {
    private long id;
    private String name;

    public Transmission() {
    }

    public Transmission(String name) {
        this.name = name;
    }

    public Transmission(long id) {
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
        Transmission that = (Transmission) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
