package ru.job4j.models;

import java.util.Objects;

/**
 * Drive. Front, Rear
 */
public class Drive {
    private long id;
    private String name;

    public Drive() {
    }

    public Drive(long id) {
        this.id = id;
    }

    public Drive(String name) {
        this.name = name;
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
        Drive drive = (Drive) o;
        return id == drive.id &&
                Objects.equals(name, drive.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
