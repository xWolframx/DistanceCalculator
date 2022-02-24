package ru.egor.kulizhnyh.distance.calculator.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.egor.kulizhnyh.distance.calculator.models.DistanceMatrix;

import javax.transaction.Transactional;

@Repository
public interface DistanceMatrixRepository extends JpaRepository<DistanceMatrix, Integer> {

    @Modifying
    @Query(value = "INSERT INTO distance_matrix (id, matrix) VALUES (:id, :matrix)", nativeQuery = true)
    @Transactional
    public void addDistanceMatrix(@Param("id") long id, @Param("matrix") byte[] distanceMatrix);

//    @Query("SELECT EXISTS(SELECT new ru.egor.kulizhnyh.distance.calculator.models.DistanceMatrix(d.id, d.matrix) FROM DistanceMatrix d)")
//    public int existsDistanceMatrix();
}
