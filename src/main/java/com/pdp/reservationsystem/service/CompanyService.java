package com.pdp.reservationsystem.service;

import com.pdp.reservationsystem.dto.CompanyDTO;
import com.pdp.reservationsystem.entity.Company;
import com.pdp.reservationsystem.mapper.CompanyMapper;
import com.pdp.reservationsystem.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CompanyDTO getCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.map(companyMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + id));
    }

    public void addCompany(CompanyDTO companyDTO) {
        Company company = companyMapper.toEntity(companyDTO);
        companyRepository.save(company);
        System.out.println("Added company: " + company);
    }

    public void deleteCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            companyRepository.deleteById(id);
            System.out.println("Company deleted with ID: " + id);
        } else {
            throw new RuntimeException("Company not found with ID: " + id);
        }
    }

}