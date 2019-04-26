package ru.job4j.models;

import java.util.Objects;

/**
 * Automobile brand name such as Toyota, Nissan etc.
 */
public class Brand {
    private long id;
    private String name;
//    private Set<CarModel> models = new HashSet<>(0);

/*
    public Set<CarModel> getModels() {
        return models;
    }

    public void setModels(Set<CarModel> models) {
        this.models = models;
    }
*/

    public Brand(){}

    public Brand(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return id == brand.id &&
                Objects.equals(name, brand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
