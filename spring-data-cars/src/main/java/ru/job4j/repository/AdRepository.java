package ru.job4j.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Ad;

@Repository
public interface AdRepository extends CrudRepository<Ad, Long> {
    @Query("select b.name from Ad a inner join AdUser b on a.adUser.id = b.id where a.id =?1")
    public String getAdUserNameById(Long id);
}
