package com.pdp.reservationsystem.service;

import com.pdp.reservationsystem.dto.FlightDTO;
import com.pdp.reservationsystem.entity.Flight;
import com.pdp.reservationsystem.mapper.FlightMapper;
import com.pdp.reservationsystem.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Autowired
    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    public List<FlightDTO> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flights.stream()
                .map(flightMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FlightDTO getFlightById(Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        return optionalFlight.map(flightMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + id));
    }

    public void addFlight(FlightDTO flightDTO) {
        Flight flight = flightMapper.toEntity(flightDTO);
        flightRepository.save(flight);
        System.out.println("Added flight: " + flight);
    }

    public void deleteFlightById(Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        if (optionalFlight.isPresent()) {
            flightRepository.deleteById(id);
            System.out.println("Flight deleted with ID: " + id);
        } else {
            throw new RuntimeException("Flight not found with ID: " + id);
        }
    }
}