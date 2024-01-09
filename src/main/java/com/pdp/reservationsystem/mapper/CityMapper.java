package com.pdp.reservationsystem.mapper;

import com.pdp.reservationsystem.dto.CityDTO;
import com.pdp.reservationsystem.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDTO toDTO(City city);

    City toEntity(CityDTO cityDTO);
}
