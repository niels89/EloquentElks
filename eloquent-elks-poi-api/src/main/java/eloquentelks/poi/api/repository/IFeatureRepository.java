package eloquentelks.poi.api.repository;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;

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

    /**
     * Loads GeoJson features from the database within the radius around the specified center.
     * @param center Center point of the circle
     * @param radius Radius of the circle
     * @return
     */
    List<Feature> getFeatures(Point center, double radius);
}
