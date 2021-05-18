package eloquentelks.recommender.api.service;

import com.google.gson.JsonPrimitive;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import eloquentelks.recommender.api.helper.FeatureCollectionAccessor;
import eloquentelks.recommender.api.helper.IFeatureCollectionAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Density service, currently returning a static GeoJson, will be replaced with real API call to density-api.
 */
@Service
public class DensityService implements IDensityService {

    /**
     * Helper to access FeatureCollections in a convenient manner
     */
    private final IFeatureCollectionAccessor featureCollectionAccessor;

    /**
     * Accesses the POI density REST service
     */
    private IDensityRestService densityRestService;

    /**
     * Constructor
     * @param helper Provides access to FeatureCollections
     * @param densityRestService Provides access to the POI Density API
     */
    @Autowired
    public DensityService(FeatureCollectionAccessor helper, IDensityRestService densityRestService){
        this.featureCollectionAccessor = helper;
        this.densityRestService = densityRestService;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<FeatureCollection> getDensities(List<String> attractionTypes) {
        return densityRestService.getDensities(attractionTypes);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<FeatureCollection> normalizeDensities(List<FeatureCollection> featureCollections) {
        for (FeatureCollection collection: featureCollections) {
            normalizeDensity(collection);
        }

        return featureCollections;
    }

    /**
     * @inheritDoc
     */
    @Override
    public FeatureCollection normalizeDensity(FeatureCollection featureCollection) {
        double minDensity = featureCollectionAccessor.getMinDensity(featureCollection);
        double maxDensity = featureCollectionAccessor.getMaxDensity(featureCollection);

        normalize(featureCollection, 0, maxDensity);

        return featureCollection;
    }

    /**
     * @inheritDoc
     */
    @Override
    public FeatureCollection aggregateDensities(List<FeatureCollection> featureCollections) {
        if(featureCollections.size() == 0){
            throw new IllegalArgumentException("featureCollections must not be an empty list");
        }

        FeatureCollection combinedFeatures = FeatureCollection.fromFeatures(new ArrayList<>());

        List<Integer> ids = new ArrayList<>();
        featureCollections.stream().forEach(collection -> collection.features().forEach(feature -> {
            int featureId = featureCollectionAccessor.getId(feature);

            if(!ids.contains(featureId)){
                Feature emptyFeature = Feature.fromGeometry(feature.geometry());
                emptyFeature.properties().add("id", new JsonPrimitive(featureId));
                emptyFeature.properties().add("poiCount", new JsonPrimitive(feature.properties().get("poiCount").getAsDouble()));
                combinedFeatures.features().add(emptyFeature);
                ids.add(featureId);
            } else{
                double existingDensity = featureCollectionAccessor.getDensity(combinedFeatures, featureId);
                double currentDensity = featureCollectionAccessor.getDensity(collection, featureId);
                featureCollectionAccessor.setDensity(combinedFeatures, featureId, existingDensity + currentDensity);
            }
        }));

        return combinedFeatures;
    }

    /**
     * Normalizes the poiCounts inside a collection to values between 0 and 1
     * Density calculation from @see{@url https://stats.stackexchange.com/questions/70801/how-to-normalize-data-to-0-1-range}
     * @param collection FeatureCollection to be processed
     * @param minDensity Minimum poiCount value
     * @param maxDensity Maximum poiCount value
     */
    private void normalize(FeatureCollection collection, double minDensity, double maxDensity){
        for (Feature feature: collection.features()) {
            int id = featureCollectionAccessor.getId(feature);

            double poiCount = featureCollectionAccessor.getDensity(feature);

            double density = (poiCount - minDensity)/(maxDensity - minDensity);

            featureCollectionAccessor.setDensity(collection, id, density);
        }
    }

}
