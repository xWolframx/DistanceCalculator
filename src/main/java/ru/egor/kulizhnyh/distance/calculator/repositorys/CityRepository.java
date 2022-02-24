package ru.egor.kulizhnyh.distance.calculator.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.egor.kulizhnyh.distance.calculator.models.City;
import ru.egor.kulizhnyh.distance.calculator.projectionclasses.CityPC;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    @Query("SELECT new ru.egor.kulizhnyh.distance.calculator.projectionclasses.CityPC(c.id, c.name) FROM City c")
    public List<CityPC> findAllNameAndId();
}
