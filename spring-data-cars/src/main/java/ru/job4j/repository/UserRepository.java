package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.AdUser;

@Repository
public interface UserRepository extends CrudRepository<AdUser, Long> {
    public AdUser findByName(String username);
}
