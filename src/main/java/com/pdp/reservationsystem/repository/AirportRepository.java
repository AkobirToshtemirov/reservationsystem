package com.pdp.reservationsystem.repository;

import com.pdp.reservationsystem.entity.Airport;
import com.pdp.reservationsystem.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByAirportName(String airportName);

    List<Airport> findByCity(City city);
}