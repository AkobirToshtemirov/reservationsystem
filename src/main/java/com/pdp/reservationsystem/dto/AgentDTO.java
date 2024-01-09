package com.pdp.reservationsystem.dto;

public record AgentDTO(
        Long id,
        String username,
        String email,
        CompanyDTO company
) {
}
