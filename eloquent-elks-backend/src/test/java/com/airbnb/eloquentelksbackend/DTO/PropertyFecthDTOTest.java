//package com.airbnb.eloquentelksbackend.DTO;
//
//import com.airbnb.eloquentelksbackend.entity.Property;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class PropertyFecthDTOTest {
//
//    @Test
//    public void testPropertyGetDTO(){
//        Property testProperty = new Property();
//        testProperty.setId("testId");
//        testProperty.setLongitude(8.541694);
//        testProperty.setLatitude(47.376888);
//        testProperty.setRoomType("testRoomType");
//        testProperty.setName("testName");
//        testProperty.setAvailability365(120);
//        testProperty.setHostName("testHostName");
//        testProperty.setPrice(300);
//        testProperty.setNeigbhourhoodGroup("testNeighborhhodGroup");
//
//        PropertyFetchDTO propertyFetchDTO = DTOMapper.INSTANCE.convertPropertyToPropertyFetchDTO(testProperty);
//        assertEquals(propertyFetchDTO.getName(), testProperty.getName());
//        assertEquals(propertyFetchDTO.getRoomType(), testProperty.getRoomType());
//        assertEquals(propertyFetchDTO.getLatitude(),testProperty.getLatitude());
//        assertEquals(propertyFetchDTO.getLongitude(),testProperty.getLongitude());
//    }
//
//}
