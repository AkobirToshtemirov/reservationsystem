package com.pdp.reservationsystem.service;

import com.pdp.reservationsystem.dto.AirportDTO;
import com.pdp.reservationsystem.dto.CityDTO;
import com.pdp.reservationsystem.entity.Airport;
import com.pdp.reservationsystem.entity.City;
import com.pdp.reservationsystem.mapper.AirportMapper;
import com.pdp.reservationsystem.mapper.CityMapper;
import com.pdp.reservationsystem.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;
    private final CityMapper cityMapper;

    @Autowired
    public AirportService(AirportRepository airportRepository, AirportMapper airportMapper, CityMapper cityMapper) {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
        this.cityMapper = cityMapper;
    }

    public List<AirportDTO> getAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream()
                .map(airportMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AirportDTO getAirportById(Long id) {
        Optional<Airport> optionalAirport = airportRepository.findById(id);
        return optionalAirport.map(airportMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Airport not found with ID: " + id));
    }

    public List<AirportDTO> getAirportsByCity(CityDTO cityDTO) {
        City city = cityMapper.toEntity(cityDTO);
        List<Airport> airports = airportRepository.findByCity(city);
        return airports.stream()
                .map(airportMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void addAirport(AirportDTO airportDTO) {
        Airport airport = airportMapper.toEntity(airportDTO);
        airportRepository.save(airport);
        System.out.println("Added airport: " + airport);
    }

    public void deleteAirportById(Long id) {
        Optional<Airport> optionalAirport = airportRepository.findById(id);
        if (optionalAirport.isPresent()) {
            airportRepository.deleteById(id);
            System.out.println("Airport deleted with ID: " + id);
        } else {
            throw new RuntimeException("Airport not found with ID: " + id);
        }
    }
}
