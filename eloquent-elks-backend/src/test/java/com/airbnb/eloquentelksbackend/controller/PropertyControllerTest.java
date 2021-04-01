package com.airbnb.eloquentelksbackend.controller;

import com.airbnb.eloquentelksbackend.DTO.PropertyFetchDTO;
import com.airbnb.eloquentelksbackend.entity.Property;
import com.airbnb.eloquentelksbackend.service.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {

    @Mock
    private PropertyService propertyService;

    @BeforeEach
    public void setUp(){
        Property testProperty = new Property();
        testProperty.setName("Test Property");
        testProperty.setLatitude(47.376888);
        testProperty.setLongitude(8.541694);
        testProperty.setRoomType("test type");

        when(propertyService.getAllProperties()).thenReturn(Arrays.asList(testProperty));
    }

    @Test
    public void getAllPropertyTest() {
        // arrange
        PropertyController controller = new PropertyController(propertyService);

        // act
        List<PropertyFetchDTO> properties = controller.getProperty();
        PropertyFetchDTO dto = properties.get(0);

        // assert
        assertEquals(47.376888, dto.getLatitude());
        assertEquals(8.541694, dto.getLongitude());
        assertEquals("Test Property", dto.getName());
        assertEquals("test type", dto.getRoomType());
    }
}
