package com.airbnb.eloquentelksbackend.repository;

import com.airbnb.eloquentelksbackend.entity.Property;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PropertyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PropertyRepository propertyRepository;

    @Test
    public void testFindAllFunctionality(){
        Property testProperty = new Property();
        testProperty.setId("testId");
        testProperty.setLongitude(8.541694);
        testProperty.setLatitude(47.376888);
        testProperty.setRoomType("testRoomType");
        testProperty.setName("testName");
        testProperty.setAvailability365(120);
        testProperty.setHostName("testHostName");
        testProperty.setPrice(300);
        testProperty.setNeigbhourhoodGroup("testNeighborhhodGroup");

        entityManager.persist(testProperty);
        entityManager.flush();

        List<Property> getAllPropertiesList = propertyRepository.findAll();

        assertEquals(getAllPropertiesList.size(),1);
        assertEquals(getAllPropertiesList.get(0).getId(),testProperty.getId());
        assertEquals(getAllPropertiesList.get(0), testProperty.getName(), testProperty.getName());


    }

}
