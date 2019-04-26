package ru.job4j.models;

import java.util.Objects;

/**
 * Car body. Кузов автомобиля
 */
public class CarBody {
    private long id;
    private String name;

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

    public CarBody() {
    }

    public CarBody(long id) {
        this.id = id;
    }

    public CarBody(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarBody carBody = (CarBody) o;
        return id == carBody.id &&
                Objects.equals(name, carBody.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
