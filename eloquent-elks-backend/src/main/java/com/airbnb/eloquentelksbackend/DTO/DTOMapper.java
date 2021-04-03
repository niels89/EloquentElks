package com.airbnb.eloquentelksbackend.DTO;


import com.airbnb.eloquentelksbackend.entity.Property;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DTOMapper {

    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    @Mapping(source = "name", target="name")
    @Mapping(source = "longitude", target="longitude")
    @Mapping(source = "latitude", target="latitude")
    @Mapping(source = "roomType", target="roomType")
    PropertyFetchDTO convertPropertyToPropertyFetchDTO(Property property);

    List<PropertyFetchDTO> map(List<Property> propertyList);
}
