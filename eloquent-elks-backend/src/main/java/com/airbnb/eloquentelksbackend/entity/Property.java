package com.airbnb.eloquentelksbackend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

/**
 * Contract for the Property
 */

@Getter
@Setter
@ToString
@Document(collection = "airbnbs")
public class Property{

    private String id;
    private String name;
    private int host_id;
    private String host_name;
    private String neighbourhood_group;
    private String neighbourhood;
    private double longitude;
    private double latitude;
    private String room_type;
    private double price;
    private int minimum_nights;
    private int noOfReviews;
    private Date lastReview;
    private double number_of_reviews;
    private double calculated_host_listings_count;
    private int availability_365;

}