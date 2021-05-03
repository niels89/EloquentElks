package com.airbnb.eloquentelksbackend.service;

import com.airbnb.eloquentelksbackend.entity.BoundingBox;
import com.airbnb.eloquentelksbackend.entity.Property;
import com.airbnb.eloquentelksbackend.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    private final Logger log = LoggerFactory.getLogger(PropertyService.class);

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

<<<<<<< HEAD:eloquent-elks-airbnb-api/src/main/java/com/airbnb/eloquentelksbackend/service/PropertyService.java
    public List<Property> getAllProperties(BoundingBox boundingBox, int min, int max){
        List<Property> allEntries = propertyRepository.findInBoundingBox(boundingBox.getNorth(), boundingBox.getEast(),
                boundingBox.getSouth(), boundingBox.getWest(), min, max);
        return allEntries;
    }
=======
    public List<Property> getAllProperties(int min, int max){
        List<Property> allEntries = propertyRepository.findByPriceBetween(min,max);
        return allEntries;
    }

>>>>>>> feature/ASEP-47:eloquent-elks-backend/src/main/java/com/airbnb/eloquentelksbackend/service/PropertyService.java
}
