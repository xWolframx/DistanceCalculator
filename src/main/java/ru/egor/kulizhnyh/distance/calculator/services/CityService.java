package ru.egor.kulizhnyh.distance.calculator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egor.kulizhnyh.distance.calculator.models.City;
import ru.egor.kulizhnyh.distance.calculator.projectionclasses.CityPC;
import ru.egor.kulizhnyh.distance.calculator.repositorys.CityRepository;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public void addCities(List<City> cityList) {
        cityRepository.saveAllAndFlush(cityList);
    }

    public List<CityPC> getAllNameId() {
        return cityRepository.findAllNameAndId();
    }

    public City getById(int id) {
        return cityRepository.getById(id);
    }
}
