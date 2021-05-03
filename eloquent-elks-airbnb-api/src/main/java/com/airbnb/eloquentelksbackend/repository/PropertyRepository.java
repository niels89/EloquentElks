package com.airbnb.eloquentelksbackend.repository;

import com.airbnb.eloquentelksbackend.entity.BoundingBox;
import com.airbnb.eloquentelksbackend.entity.Property;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
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
    List<Property> findByPriceBetween(int min, int max);
<<<<<<< HEAD:eloquent-elks-airbnb-api/src/main/java/com/airbnb/eloquentelksbackend/repository/PropertyRepository.java

    @Query(value = "{ 'latitude': {'$lt': ?0, '$gt': ?2}, 'longitude': {'$lt': ?1, '$gt': ?3}, 'price': {'$gt': ?4, '$lt': ?5}, 'availability_365': {'$gt': 120}}")
    List<Property> findInBoundingBox(double north, double east, double south, double west, int minPrice, int maxPrice);
=======
>>>>>>> feature/ASEP-47:eloquent-elks-backend/src/main/java/com/airbnb/eloquentelksbackend/repository/PropertyRepository.java
}
