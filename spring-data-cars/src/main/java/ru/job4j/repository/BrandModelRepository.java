package ru.job4j.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import ru.job4j.models.*;

import java.util.List;


public interface BrandModelRepository extends CrudRepository<BrandModel, Long> {
    BrandModel findByBrandAndCarModel(Brand brand, CarModel carModel);

    @Query("SELECT new ru.job4j.models.CarModel(a.carModel.id, a.carModel.name) FROM brand_model a where a.brand=:brand ORDER BY a.carModel.name")
    List<CarModel> findAllByBrand(Brand brand);
}
