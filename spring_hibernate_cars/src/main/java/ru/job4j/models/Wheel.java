package ru.job4j.models;

import java.util.Objects;

public class Wheel {
    private long id;
    private String name;
    private String sid;

    public void setSid(String sid) {
        this.sid = sid;
        this.id = Long.parseLong(sid);
    }

    public String getSid() {
        sid = Long.toString(id);
        return sid;
    }

    public Wheel() {
    }

    public Wheel(long id) {
        this.id = id;
    }

    public Wheel(String name) {
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
        Wheel wheel = (Wheel) o;
        return id == wheel.id &&
                Objects.equals(name, wheel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
