package eloquentelks.recommender.api.service;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import eloquentelks.recommender.api.helper.FeatureCollectionAccessor;
import eloquentelks.recommender.api.helper.IFeatureCollectionAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Density service, currently returning a static GeoJson, will be replaced with real API call to density-api.
 */
@Service
public class DensityService implements IDensityService {

    private final IFeatureCollectionAccessor featureCollectionAccessor;
    private IDensityRestService densityRestService;

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

        normalize(featureCollection, minDensity, maxDensity);

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
            int id = featureCollectionAccessor.getId(feature);

            double poiCount = featureCollectionAccessor.getDensity(feature);

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
        int id = featureCollectionAccessor.getId(countingFeature);

        double density = featureCollectionAccessor.getDensity(countingFeature);

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
