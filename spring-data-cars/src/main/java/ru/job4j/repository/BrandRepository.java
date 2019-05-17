package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.job4j.models.*;
@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    Brand findByName(String name);

}
