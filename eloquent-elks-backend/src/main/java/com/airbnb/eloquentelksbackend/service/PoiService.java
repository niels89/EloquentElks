package com.airbnb.eloquentelksbackend.service;

import com.airbnb.eloquentelksbackend.DTO.PoiMapper;
import com.airbnb.eloquentelksbackend.entity.PoiGetDto;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;

import com.mapbox.turf.TurfConstants;
import com.mapbox.turf.TurfMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airbnb.eloquentelksbackend.repository.secondary.IFeatureRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @inheritDoc
 */
@Service
public class PoiService implements IPoiService {

    /**
     * Database accessor
     */
    private final IFeatureRepository featureRepository;

    /**
     * Constructor
     * @param featureRepository MongoDb repository to access the database
     */
    @Autowired
    public PoiService(IFeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }


    /**
     * @inheritDoc
     */
    @Override
    public List<PoiGetDto> getAllPois(double longitude, double latitude) {

        List<Feature> allFeatures= featureRepository.findAll();

        List<Feature> featuresInRadius = new ArrayList<>();

        allFeatures.forEach(feature -> {
            double distance = TurfMeasurement.distance(Point.fromLngLat(longitude, latitude), (Point)feature.geometry(), TurfConstants.UNIT_METRES);
            if(distance <= 500){
                featuresInRadius.add(feature);
            }
        });

        return PoiMapper.mapToDto(featuresInRadius);
    }
}
