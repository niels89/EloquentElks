package com.airbnb.eloquentelksbackend.service;


import com.airbnb.eloquentelksbackend.entity.PoiGetDto;

import java.util.List;

/**
 * Service to retrieve Points of Interest from the database
 */
public interface IPoiService {
    /**
     * Loads all Points of Interest from the database and returns them as Dtos
     * @param longitude Geographical longitude of center
     * @param latitude Geographical latitude of center
     * @return A list of @see {@link com.airbnb.eloquentelksbackend.entity.PoiGetDto}, which is the structure that is exposed to the clients of the API
     */
    List<PoiGetDto> getAllPois(double longitude, double latitude);
}
