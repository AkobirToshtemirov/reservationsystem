package com.pdp.reservationsystem.repository;

import com.pdp.reservationsystem.entity.Agent;
import com.pdp.reservationsystem.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findByUsername(String username);

    Optional<Agent> findByEmail(String email);

    List<Agent> findByCompany(Company company);
}