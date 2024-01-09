package com.pdp.reservationsystem.controller;

import com.pdp.reservationsystem.dto.AgentDTO;
import com.pdp.reservationsystem.dto.FlightDTO;
import com.pdp.reservationsystem.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {

    private final AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/{agentId}/flights")
    public ResponseEntity<Void> registerFlights(@PathVariable Long agentId, @RequestBody List<FlightDTO> flights) {
        agentService.registerFlights(agentId, flights);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/flights/{flightId}")
    public ResponseEntity<Void> updateFlightDetails(@PathVariable Long flightId, @RequestBody FlightDTO updatedFlight) {
        agentService.updateFlightDetails(flightId, updatedFlight);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addAgent(@RequestBody AgentDTO agentDTO) {
        agentService.saveAgent(agentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{agentId}/delete")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long agentId) {
        agentService.deleteAgentById(agentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{agentId}")
    public ResponseEntity<AgentDTO> getAgentById(@PathVariable Long agentId) {
        AgentDTO agentDTO = agentService.findAgentById(agentId);
        return ResponseEntity.ok(agentDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AgentDTO>> getAllAgents() {
        List<AgentDTO> agents = agentService.getAllAgents();
        return ResponseEntity.ok(agents);
    }
}