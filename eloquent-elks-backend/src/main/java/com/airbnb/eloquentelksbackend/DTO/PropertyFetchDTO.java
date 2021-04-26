package com.airbnb.eloquentelksbackend.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PropertyFetchDTO {
    private String name;
    private double longitude;
    private double latitude;
    private String roomType;
    private double price;
    private String hostName;
}
