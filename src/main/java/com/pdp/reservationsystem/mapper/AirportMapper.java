package com.pdp.reservationsystem.mapper;

import com.pdp.reservationsystem.dto.AirportDTO;
import com.pdp.reservationsystem.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    @Mapping(target = "city", source = "city")
    AirportDTO toDTO(Airport airport);

    @Mapping(target = "city", source = "city")
    Airport toEntity(AirportDTO airportDTO);
}
