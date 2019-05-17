package ru.job4j.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "brand_model")
public class BrandModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name = "model_id", nullable = false)
    private CarModel carModel;

    @OneToMany(mappedBy = "brandModel")
    List<Ad> ad;

    public BrandModel() {
    }

    public BrandModel(Brand brand, CarModel carModel) {
        this.brand = brand;
        this.carModel = carModel;
    }

    public long getBrandId() {
        return brand.getId();
    }

    public long getModelId() {
        return carModel.getId();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandModel that = (BrandModel) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
