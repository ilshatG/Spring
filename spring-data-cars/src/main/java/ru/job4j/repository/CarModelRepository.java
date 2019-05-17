package ru.job4j.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import ru.job4j.models.CarModel;

@Repository
public interface CarModelRepository extends CrudRepository<CarModel, Long> {
    CarModel findByName(String name);
}
