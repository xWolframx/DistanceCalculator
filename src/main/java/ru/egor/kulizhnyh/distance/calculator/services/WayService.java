package ru.egor.kulizhnyh.distance.calculator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egor.kulizhnyh.distance.calculator.models.Way;
import ru.egor.kulizhnyh.distance.calculator.repositorys.WayRepository;

import java.util.List;

@Service
public class WayService {
    @Autowired
    private WayRepository wayRepository;

    public List<Way> getAll() {
        return wayRepository.findAll();
    }
}
