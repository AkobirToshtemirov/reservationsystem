package com.pdp.reservationsystem.service;

import com.pdp.reservationsystem.dto.CityDTO;
import com.pdp.reservationsystem.entity.City;
import com.pdp.reservationsystem.mapper.CityMapper;
import com.pdp.reservationsystem.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Autowired
    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    public List<CityDTO> getAllCities() {
        List<City> cities = cityRepository.findAll();
        return cities.stream()
                .map(cityMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CityDTO getCityById(Long id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        return optionalCity.map(cityMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("City not found with ID: " + id));
    }

    public void addCity(CityDTO cityDTO) {
        City city = cityMapper.toEntity(cityDTO);
        cityRepository.save(city);
        System.out.println("Added city: " + city);
    }

    public void deleteCityById(Long id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isPresent()) {
            cityRepository.deleteById(id);
            System.out.println("City deleted with ID: " + id);
        } else {
            throw new RuntimeException("City not found with ID: " + id);
        }
    }
}
