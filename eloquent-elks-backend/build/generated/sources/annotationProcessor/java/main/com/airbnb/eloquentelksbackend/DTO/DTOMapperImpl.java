package com.airbnb.eloquentelksbackend.DTO;

import com.airbnb.eloquentelksbackend.entity.Property;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-26T16:47:08+0200",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.2 (Oracle Corporation)"
)
public class DTOMapperImpl implements DTOMapper {

    @Override
    public PropertyFetchDTO convertPropertyToPropertyFetchDTO(Property property) {
        if ( property == null ) {
            return null;
        }

        PropertyFetchDTO propertyFetchDTO = new PropertyFetchDTO();

        propertyFetchDTO.setName( property.getName() );
        propertyFetchDTO.setLongitude( property.getLongitude() );
        propertyFetchDTO.setLatitude( property.getLatitude() );
        propertyFetchDTO.setRoomType( property.getRoomType() );

        return propertyFetchDTO;
    }

    @Override
    public List<PropertyFetchDTO> map(List<Property> properties) {
        if ( properties == null ) {
            return null;
        }

        List<PropertyFetchDTO> list = new ArrayList<PropertyFetchDTO>( properties.size() );
        for ( Property property : properties ) {
            list.add( convertPropertyToPropertyFetchDTO( property ) );
        }

        return list;
    }
}
