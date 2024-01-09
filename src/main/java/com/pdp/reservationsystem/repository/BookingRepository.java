package com.pdp.reservationsystem.repository;

import com.pdp.reservationsystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByFlightId(Long flightId);
}
