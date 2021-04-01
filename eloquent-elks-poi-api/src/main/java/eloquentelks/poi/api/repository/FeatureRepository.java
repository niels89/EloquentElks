package eloquentelks.poi.api.repository;

import com.google.gson.JsonObject;
import com.mapbox.geojson.Feature;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FeatureRepository implements IFeatureRepository{
    private final MongoTemplate mongoTemplate;

    public FeatureRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Feature> getFeatures() {
        List<Feature> features = new ArrayList<Feature>();

        Query query = new Query();

        List<String> documents = mongoTemplate.findAll(String.class, "feature");

        documents.forEach(document -> {
            features.add(Feature.fromJson(document.toString()));
        });

        return features;
    }
}
