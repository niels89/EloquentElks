package eloquentelks.poi.api.repository;

import com.mapbox.geojson.Feature;

import java.util.List;

public interface IFeatureRepository {
    List<Feature> getFeatures();
}
