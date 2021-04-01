package eloquentelks.poi.api.repository;

import com.mapbox.geojson.Feature;

import java.util.List;

/**
 * Repository for GeoJson features
 */
public interface IFeatureRepository {

    /**
     * Loads GeoJson features from the database
     * @return List of GeoJson features
     */
    List<Feature> getFeatures();
}
