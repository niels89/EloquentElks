package com.airbnb.eloquentelksbackend.repository;

import com.airbnb.eloquentelksbackend.entity.Property;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository("userRepository")
public interface PropertyRepository extends MongoRepository<Property, Long> {
    List<Property> findAll();
}
