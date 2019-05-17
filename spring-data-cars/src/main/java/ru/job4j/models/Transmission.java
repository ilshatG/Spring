package ru.job4j.models;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Transmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "transmission")
    List<Ad> ad;

    public Transmission() {
    }

    public Transmission(String name) {
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
}
