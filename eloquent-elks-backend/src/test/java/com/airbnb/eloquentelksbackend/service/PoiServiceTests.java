package com.airbnb.eloquentelksbackend.service;

import com.airbnb.eloquentelksbackend.entity.PoiGetDto;
import com.airbnb.eloquentelksbackend.repository.secondary.IFeatureRepository;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for @see {@link com.airbnb.eloquentelksbackend.service.PoiService}
 */
@ExtendWith(MockitoExtension.class)
public class PoiServiceTests {

    /**
     * Stub of a @see{@link com.airbnb.eloquentelksbackend.repository.secondary.IFeatureRepository}
     */
    @Mock
    private IFeatureRepository poiRepository;

    @Mock
    private PoiService poiService;

    /**
     * Features list that is used by the FeatureRepository stub
     * */
    private static List<PoiGetDto> poiList;

    /**
     * Sets up two features that are returned by the FeatureRepository stub
     */
    @BeforeAll
    public static void setUp(){
        PoiGetDto poi1 = new PoiGetDto();
        poi1.setLatitude(42.1d);
        poi1.setLongitude(1.323d);

        PoiGetDto poi2 = new PoiGetDto();
        poi2.setLatitude(-25.49d);
        poi2.setLongitude(173.82d);

        poiList = new ArrayList<>();
        poiList.add(new PoiGetDto());
        poiList.add(new PoiGetDto());
    }

    /**
     * Tests if the PoiService is able to return all PoIs stored on the database
     */
    @Test
    public void testGetAllPois(){

        when(poiService.getAllPois(40.6804506, -73.9475876)).thenReturn(poiList);
        List<PoiGetDto> pois = poiService.getAllPois(40.6804506, -73.9475876);

        // assert
        assertEquals(2, pois.size());
    }
}
