package com.pdp.reservationsystem.controller;

import com.pdp.reservationsystem.dto.AdminDTO;
import com.pdp.reservationsystem.dto.AgentDTO;
import com.pdp.reservationsystem.dto.CityDTO;
import com.pdp.reservationsystem.dto.CompanyDTO;
import com.pdp.reservationsystem.service.AdminService;
import com.pdp.reservationsystem.service.AgentService;
import com.pdp.reservationsystem.service.CityService;
import com.pdp.reservationsystem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final AgentService agentService;
    private final CityService cityService;
    private final CompanyService companyService;

    @Autowired
    public AdminController(AdminService adminService, AgentService agentService,
                           CityService cityService, CompanyService companyService) {
        this.adminService = adminService;
        this.agentService = agentService;
        this.cityService = cityService;
        this.companyService = companyService;
    }

    @PostMapping("/agents")
    public ResponseEntity<Void> addAgent(@RequestBody AgentDTO agentDTO) {
        agentService.saveAgent(agentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/agents/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id) {
        agentService.deleteAgentById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cities")
    public ResponseEntity<Void> addCity(@RequestBody CityDTO cityDTO) {
        cityService.addCity(cityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCityById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/companies")
    public ResponseEntity<Void> addCompany(@RequestBody CompanyDTO companyDTO) {
        companyService.addCompany(companyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompanyById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admins")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<AdminDTO> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        AdminDTO admin = adminService.findAdminById(id);
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/admins")
    public ResponseEntity<Void> addAdmin(@RequestBody AdminDTO adminDTO) {
        adminService.saveAdmin(adminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}