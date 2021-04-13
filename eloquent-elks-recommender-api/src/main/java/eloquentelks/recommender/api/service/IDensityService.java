package eloquentelks.recommender.api.service;

import com.mapbox.geojson.FeatureCollection;

import java.util.List;

/**
 * Provides access to the density calculation of specific attraction types by accessing the density API.
 */
public interface IDensityService {
    /**
     * Retrieves the result of the density calculation
     * Density calculation from @see{@url https://stats.stackexchange.com/questions/70801/how-to-normalize-data-to-0-1-range}
     * @param attractionTypes List of attraction types according to the GeoJSON feature properties.
     * @return A GeoJson FeatureCollection with density property
     */
    List<FeatureCollection> getDensities(List<String> attractionTypes);

    /**
     * Normalize all densities such that they can be aggregated afterwards. This enables the combination of
     * densities for POI categories with any amount of POIs.
     * @param featureCollections Contains a FeatureCollection per attraction type
     * @return A list of GeoJSON FeatureCollections with normalized densities
     */
    List<FeatureCollection> normalizeDensities(List<FeatureCollection> featureCollections);

    /**
     * Combines the poi counts of the specified FeatureCollections into a single aggregated FeatureCollection
     * @param featureCollections Contains a FeatureCollection per attraction type
     * @return A single aggregated GeoJSON FeatureCollection with combined densities
     */
    FeatureCollection aggregateDensities(List<FeatureCollection> featureCollections);
}
