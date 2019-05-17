package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Wheel;

@Repository
public interface WheelRepository extends CrudRepository<Wheel, Long> {
}
