package com.pdp.reservationsystem.dto;

public record CustomerDTO(
        Long id,
        String username,
        String email,
        CityDTO cityOfResidence
) {
}
