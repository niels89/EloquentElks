package eloquentelks.recommender.api.helper;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static eloquentelks.recommender.api.Constants.GEOJSON_FEATURE_PROPERTY_POICOUNT;

/**
 * @inheritDoc
 */
@Service
public class FeatureCollectionAccessor implements IFeatureCollectionAccessor {

    /**
     * @inheritDoc
     */
    @Override
    public int getDensity(FeatureCollection collection, int id){
        Feature feature = getFeatureById(collection, id);

        if(feature == null){
            throw new IllegalArgumentException("Feature with id " + id + " does not exist in the FeatureCollection");
        }

        JsonObject properties = feature.properties();

        return properties.get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsInt();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Feature getFeatureById(FeatureCollection collection, int id){
        Optional<Feature> feature = collection.features().stream().filter(f -> f.properties().get("id").getAsInt() == id).findFirst();

        if(!feature.isPresent()){
            return null;
        }

        return feature.get();
    }

    /**
     * @inheritDoc
     */
    @Override
    public FeatureCollection copyFeatureIds(FeatureCollection featureCollection){
        FeatureCollection collection = FeatureCollection.fromJson(featureCollection.toJson());

        collection.features().forEach(f -> f.properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT));

        collection.features().forEach(f -> f.properties().add(GEOJSON_FEATURE_PROPERTY_POICOUNT, new JsonPrimitive(0)));

        return collection;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getMinDensity(FeatureCollection collection) {
        int minDensity = Integer.MAX_VALUE;

        for (Feature f : collection.features()) {
            int density = f.properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsInt();
            minDensity = Math.min(minDensity, density);
        }

        return minDensity;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getMaxDensity(FeatureCollection collection) {
        int maxDensity = Integer.MIN_VALUE;

        for (Feature f : collection.features()) {
            int density = f.properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsInt();
            maxDensity = Math.max(maxDensity, density);
        }

        return maxDensity;
    }

    /**
     * @inheritDoc
     */
    public void setDensity(FeatureCollection collection, int id, double density){
        Feature feature = getFeatureById(collection, id);

        feature.properties().remove(GEOJSON_FEATURE_PROPERTY_POICOUNT);

        feature.properties().add(GEOJSON_FEATURE_PROPERTY_POICOUNT, new JsonPrimitive(density));
    }
}
