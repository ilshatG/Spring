package ru.job4j.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Drive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "drive")
    List<Ad> ad;

    public Drive(String name) {
        this.name = name;
    }

    public Drive() {
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
}
