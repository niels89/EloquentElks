package com.airbnb.eloquentelksbackend.repository.secondary;

import com.airbnb.eloquentelksbackend.entity.Property;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for GeoJson features
 */
@Repository
public interface IFeatureRepository extends MongoRepository<Feature, String> {

    List<Feature> findAll();

}
