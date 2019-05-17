package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Ad;

@Repository
public interface AdRepository extends CrudRepository<Ad, Long> {
}
