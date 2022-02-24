package ru.egor.kulizhnyh.distance.calculator.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egor.kulizhnyh.distance.calculator.models.Way;

@Repository
public interface WayRepository extends JpaRepository<Way, Integer> {
}
