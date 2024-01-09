package com.pdp.reservationsystem.mapper;

import com.pdp.reservationsystem.dto.FlightDTO;
import com.pdp.reservationsystem.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FlightMapper {

    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    @Mapping(target = "departureAirport", source = "departureAirport")
    @Mapping(target = "arrivalAirport", source = "arrivalAirport")
    @Mapping(target = "agent", source = "agent")
    FlightDTO toDTO(Flight flight);

    @Mapping(target = "departureAirport", source = "departureAirport")
    @Mapping(target = "arrivalAirport", source = "arrivalAirport")
    @Mapping(target = "agent", source = "agent")
    Flight toEntity(FlightDTO flightDTO);
}
