package com.pdp.reservationsystem.dto;

public record AirportDTO(
        Long id,
        String airportName,
        CityDTO city
) {
}
