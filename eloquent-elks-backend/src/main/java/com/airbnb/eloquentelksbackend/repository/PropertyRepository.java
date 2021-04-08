package com.airbnb.eloquentelksbackend.repository;

import com.airbnb.eloquentelksbackend.entity.Property;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Repository to access the Property data from the database
 */
@Repository("propertyRepository")
public interface PropertyRepository extends MongoRepository<Property, Long> {

    /**
     * findAll method returns all the Property present in the database
     * @return List of Properties @see {@link com.airbnb.eloquentelksbackend.entity.Property}
     */
    List<Property> findAll();
}
