package eloquentelks.poi.api.repository;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides access to MongoDb feature collection
 */
@Repository
public class FeatureRepository implements IFeatureRepository{

    /**
     * Key for the GeoJson distance query
     */
    private final String QUERY_KEY = "geometry";

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
    public List<Feature> getFeatures(Point center, double radius){
        Criteria criteria = Criteria.where(QUERY_KEY).nearSphere(new GeoJsonPoint(center.longitude(), center.latitude())).maxDistance(radius);

        Query query = new Query(criteria);

        List<String> documents = mongoTemplate.find(query, String.class, FEATURE_COLLECTION);

        List<Feature> features = convert(documents);

        return features;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Feature> getFeatures(String attractionType){
        Criteria criteria = Criteria.where("properties.tourism").is(attractionType);

        Query query = new Query(criteria);

        List<String> document = mongoTemplate.find(query, String.class, FEATURE_COLLECTION);

        List<Feature> features = convert(document);

        return features;
    }

    /**
     * Converts JSON documents to @see{@link com.mapbox.geojson.Feature}
     * @param documents List of JSON strings from the database
     * @return List of GeoJson Features
     */
    private List<Feature> convert(List<String> documents){
        List<Feature> features = new ArrayList<>();

        documents.forEach(document -> features.add(Feature.fromJson(document)));

        return features;
    }
}
