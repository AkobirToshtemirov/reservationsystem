package com.pdp.reservationsystem.repository;

import com.pdp.reservationsystem.entity.Agent;
import com.pdp.reservationsystem.entity.Airport;
import com.pdp.reservationsystem.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirport(Airport airport);

    List<Flight> findByArrivalAirport(Airport airport);

    List<Flight> findByAgent(Agent agent);

    List<Flight> findByDepartureAirport_City_Id(Long id);
}
