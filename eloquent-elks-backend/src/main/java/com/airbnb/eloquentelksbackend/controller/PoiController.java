package com.airbnb.eloquentelksbackend.controller;


import com.airbnb.eloquentelksbackend.entity.PoiGetDto;
import com.airbnb.eloquentelksbackend.service.IPoiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller which serves Points of Interest
 */
@RestController
public class PoiController {

    /**
     * Service that provides the Points of Interests from the database
     */
    private final IPoiService poiService;

    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(PoiController.class);

    /**
     * @param poiService Service that provides the Points of Interests from the database
     */
    @Autowired
    public PoiController(IPoiService poiService){
        this.poiService = poiService;
    }

    /**
     * Returns all points of interest
     * @param longitude Geographical longitude of the center
     * @param latitude Geographical latitude of the center
     * @return A list of points of interest in New York city
     */
    @GetMapping("poi")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PoiGetDto> getAllPoi(@RequestParam("longitude") double longitude,
                                     @RequestParam("latitude") double latitude){
        List<PoiGetDto> poiList = poiService.getAllPois(longitude, latitude);

        return poiList;
    }
}
