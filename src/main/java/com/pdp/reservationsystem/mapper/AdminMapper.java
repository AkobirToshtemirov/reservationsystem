package com.pdp.reservationsystem.mapper;

import com.pdp.reservationsystem.dto.AdminDTO;
import com.pdp.reservationsystem.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    AdminDTO toDTO(Admin admin);

    Admin toEntity(AdminDTO adminDTO);
}
