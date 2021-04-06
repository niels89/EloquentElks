package com.airbnb.eloquentelksbackend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;



@Getter
@Setter
@ToString
@Document(collection = "airbnbs")
public class Property{

    private String id;
    private String name;
    private Long hostId;
    private String hostName;
    private String neigbhourhoodGroup;
    private String neighbourhood;
    private double longitude;
    private double latitude;
    private String roomType;
    private double price;
    private int minimumNights;
    private int noOfReviews;
    private Date lastReview;
    private double noOfReviewsPerMonth;
    private double calculatedHostListingCount;
    private int availability365;

}
