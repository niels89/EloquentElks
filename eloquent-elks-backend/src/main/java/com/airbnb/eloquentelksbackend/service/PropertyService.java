package com.airbnb.eloquentelksbackend.service;

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

    public List<Property> getAllProperties(String min, String max){
        List<Property> allEntries = propertyRepository.findByPriceBetween(min,max);
        return allEntries;
    }

}
