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
import java.util.stream.Collectors;

/**
 * Rest controller for fetching property
 */

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    /**
     * Service to interact and fetch the properties from DAO.
     */
    private final PropertyService propertyService;

    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(PropertyController.class);

    /**
     *
     * @param propertyService autowired
     */
    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     *
     * @return A list of all the airbnb properties list in the database for New York @see {@link com.airbnb.eloquentelksbackend.entity.Property}.
     */
    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("/airbnb")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PropertyFetchDTO> getProperty(){
        List<Property> propertiesList = propertyService.getAllProperties();
        return DTOMapper.INSTANCE.map(propertiesList.stream().limit(100).collect(Collectors.toList()));
    }



}
