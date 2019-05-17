package ru.job4j.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "engine")
    List<Ad> ad;

    public Engine(String name) {
        this.name = name;
    }

    public Engine() {
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
