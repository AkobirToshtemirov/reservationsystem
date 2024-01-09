package com.pdp.reservationsystem.mapper;

import com.pdp.reservationsystem.dto.CompanyDTO;
import com.pdp.reservationsystem.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDTO toDTO(Company company);

    Company toEntity(CompanyDTO companyDTO);
}
