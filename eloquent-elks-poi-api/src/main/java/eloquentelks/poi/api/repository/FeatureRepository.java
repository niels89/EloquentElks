package eloquentelks.poi.api.repository;

import com.mapbox.geojson.Feature;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides access to MongoDb feature collection
 */
@Repository
public class FeatureRepository implements IFeatureRepository{

    /**
     * Data access component
     */
    private final MongoTemplate mongoTemplate;

    /**
     * Name of the feature collection on the database
     */
    private final String FEATURE_COLLECTION = "feature";

    /**
     * Constructor
     * @param mongoTemplate Data access component
     */
    public FeatureRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Feature> getFeatures() {
        List<Feature> features = new ArrayList<>();

        List<String> documents = mongoTemplate.findAll(String.class, FEATURE_COLLECTION);

        documents.forEach(document -> {
            features.add(Feature.fromJson(document));
        });

        return features;
    }
}
