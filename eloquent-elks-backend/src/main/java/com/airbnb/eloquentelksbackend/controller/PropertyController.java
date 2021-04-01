package com.airbnb.eloquentelksbackend.controller;

import com.airbnb.eloquentelksbackend.DTO.DTOMapper;
import com.airbnb.eloquentelksbackend.DTO.PropertyFetchDTO;
import com.airbnb.eloquentelksbackend.entity.Property;
import com.airbnb.eloquentelksbackend.service.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    private final PropertyService propertyService;
    private final Logger log = LoggerFactory.getLogger(PropertyController.class);

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    @GetMapping("/airbnb")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PropertyFetchDTO> getProperty(){
        List<Property> propertiesList = propertyService.getAllProperties();
        return DTOMapper.INSTANCE.map(propertiesList);
    }



}
