package ru.egor.kulizhnyh.distance.calculator.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.egor.kulizhnyh.distance.calculator.models.Distance;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, Integer> {

    @Modifying
    @Query(value = "INSERT INTO distance (id_from_city, id_to_city, id_way, distance) VALUES (:in_id_from_city, :in_id_to_city, :in_id_way, :in_distance)", nativeQuery = true)
    @Transactional
    public void addDistance(@Param("in_id_from_city") long idFromCity, @Param("in_id_to_city") long idToCity, @Param("in_id_way") long idWay, @Param("in_distance") double distance);
}
