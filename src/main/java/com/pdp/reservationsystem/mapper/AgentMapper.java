package com.pdp.reservationsystem.mapper;

import com.pdp.reservationsystem.dto.AgentDTO;
import com.pdp.reservationsystem.entity.Agent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AgentMapper {

    AgentMapper INSTANCE = Mappers.getMapper(AgentMapper.class);

    @Mapping(target = "company", source = "company")
    AgentDTO toDTO(Agent agent);

    @Mapping(target = "company", source = "company")
    Agent toEntity(AgentDTO agentDTO);
}

