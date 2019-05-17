package ru.job4j.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Wheel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "wheel")
    List<Ad> ad;

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
