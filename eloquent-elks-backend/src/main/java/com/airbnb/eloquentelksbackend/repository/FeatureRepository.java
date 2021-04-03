package com.airbnb.eloquentelksbackend.repository;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.turf.TurfConstants;
import com.mapbox.turf.TurfMeasurement;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides access to MongoDb feature collection
 */
@Repository
public class FeatureRepository implements IFeatureRepository{

    /**
     * Data access component
     */
    private final MongoTemplate mongoTemplate;

    /**
     * Name of the feature collection on the database
     */
    private final String FEATURE_COLLECTION = "feature";

    /**
     * Constructor
     * @param mongoTemplate Data access component
     */
    public FeatureRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Feature> getFeatures() {
        List<Feature> features = new ArrayList<>();

        List<String> documents = mongoTemplate.findAll(String.class, FEATURE_COLLECTION);

        documents.forEach(document -> {
            features.add(Feature.fromJson(document));
        });

        return features;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Feature> getFeatures(Point center, double radius) {
        List<Feature> allFeatures = getFeatures();
        List<Feature> featuresInRadius = new ArrayList<>();

        allFeatures.forEach(feature -> {
            double distance = TurfMeasurement.distance(center, (Point)feature.geometry(), TurfConstants.UNIT_METRES);
            if(distance <= radius){
                featuresInRadius.add(feature);
            }
        });

        return featuresInRadius;
    }
}
