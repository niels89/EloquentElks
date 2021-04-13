package eloquentelks.recommender.api.service;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import eloquentelks.recommender.api.helper.FeatureCollectionAccessor;
import eloquentelks.recommender.api.helper.FeatureCollectionFactory;
import eloquentelks.recommender.api.helper.IFeatureCollectionAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static eloquentelks.recommender.api.Constants.GEOJSON_FEATURE_PROPERTY_ID;
import static eloquentelks.recommender.api.Constants.GEOJSON_FEATURE_PROPERTY_POICOUNT;

/**
 * Density service, currently returning a static GeoJson, will be replaced with real API call to density-api.
 */
@Service
public class DensityService implements IDensityService {

    private final IFeatureCollectionAccessor featureCollectionAccessor;

    @Autowired
    public DensityService(FeatureCollectionAccessor helper){
        this.featureCollectionAccessor = helper;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<FeatureCollection> getDensities(List<String> attractionTypes) {
        Map<Integer, Integer> densities1 = Map.of(1, 42);
        Map<Integer, Integer> densities2 = Map.of(1, 22);
        return List.of(
                FeatureCollectionFactory.create(densities1),
                FeatureCollectionFactory.create(densities2)
        );
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<FeatureCollection> normalizeDensities(List<FeatureCollection> featureCollections) {
        for (FeatureCollection collection: featureCollections) {
            int minDensity = featureCollectionAccessor.getMinDensity(collection);
            int maxDensity = featureCollectionAccessor.getMaxDensity(collection);

            normalize(collection, minDensity, maxDensity);
        }

        return featureCollections;
    }

    /**
     * @inheritDoc
     */
    @Override
    public FeatureCollection aggregateDensities(List<FeatureCollection> featureCollections) {
        if(featureCollections.size() == 0){
            throw new IllegalArgumentException("featureCollections must not be an empty list");
        }

        Optional<FeatureCollection> first = featureCollections.stream().findFirst();
        if(!first.isPresent()){
            throw new IllegalArgumentException("featureCollections must contain at least one FeatureCollection");
        }

        FeatureCollection combinedFeatures = featureCollectionAccessor.copyFeatureIds(first.get());

        for (Feature countingFeature : combinedFeatures.features()) {
            aggregateDensity(featureCollections, countingFeature, (id, density) -> featureCollectionAccessor.setDensity(combinedFeatures, id, density));
        }

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
            int id = feature.properties().get(GEOJSON_FEATURE_PROPERTY_ID).getAsInt();
            int poiCount = feature.properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsInt();

            double density = (poiCount - minDensity)/(maxDensity - minDensity);

            featureCollectionAccessor.setDensity(collection, id, density);
        }
    }

    /**
     * Increments the density value of the combinedFeatures collection
     * @param featureCollections List of input feature collections containing normalized densities
     * @param countingFeature Feature that contains the measured poiCount
     * @param densitySetter Lambda expression to set the density in the FeatureCollection
     */
    private void aggregateDensity(List<FeatureCollection> featureCollections, Feature countingFeature, DensitySetter densitySetter) {
        int id = countingFeature.properties().get(GEOJSON_FEATURE_PROPERTY_ID).getAsInt();

        double density = countingFeature.properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsDouble();

        for(FeatureCollection featureCollection: featureCollections) {
                density += featureCollectionAccessor.getDensity(featureCollection, id);
        }

        densitySetter.set(id, density);
    }

    /**
     * Function interface for setting density via lambda expression
     */
    @FunctionalInterface
    private interface DensitySetter{
        void set(int id, double density);
    }
}
