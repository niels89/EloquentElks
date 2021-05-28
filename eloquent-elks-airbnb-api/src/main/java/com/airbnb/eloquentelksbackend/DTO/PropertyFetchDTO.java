package com.airbnb.eloquentelksbackend.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PropertyFetchDTO {
    /**
     * Name of the property
     */
    private String name;
    /**
     * Longitude of the property
     */
    private double longitude;
    /**
     * Latitude of the property
     */
    private double latitude;
    /**
     * * Type of the room in the property
     */
    private String roomType;
    /**
     * Price of the property per day
     */
    private double price;
    /**
     * Name of the host of the property
     */
    private String hostName;
}
