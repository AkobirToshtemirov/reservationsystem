package com.pdp.reservationsystem.mapper;

import com.pdp.reservationsystem.dto.CustomerDTO;
import com.pdp.reservationsystem.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "cityOfResidence", source = "cityOfResidence")
    CustomerDTO toDTO(Customer customer);

    @Mapping(target = "cityOfResidence", source = "cityOfResidence")
    Customer toEntity(CustomerDTO customerDTO);
}
