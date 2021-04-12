package eloquentelks.recommender.api.service;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import eloquentelks.recommender.api.helper.FeatureCollectionAccessor;
import eloquentelks.recommender.api.helper.IFeatureCollectionAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static eloquentelks.recommender.api.Constants.GEOJSON_FEATURE_PROPERTY_ID;
import static eloquentelks.recommender.api.Constants.GEOJSON_FEATURE_PROPERTY_POICOUNT;

/**
 * Density service, currently returning a static GeoJson, will be replaced with real API call to density-api.
 */
@Service
public class DensityService implements IDensityService {

    private final IFeatureCollectionAccessor featureCollectionAccessor;

    private String featureCollectionJson1 = "{\n" +
            "  \"type\": \"FeatureCollection\",\n" +
            "  \"features\": [\n" +
            "    {\n" +
            "      \"type\": \"Feature\",\n" +
            "      \"properties\": {\n" +
            "        \"poiCount\": 42,\n" +
            "        \"id\": 0\n" +
            "      },\n" +
            "      \"geometry\": {\n" +
            "        \"type\": \"Polygon\",\n" +
            "        \"coordinates\": [\n" +
            "          [\n" +
            "            [\n" +
            "              -74.57080229744054,\n" +
            "              40.41846628754955\n" +
            "            ],\n" +
            "            [\n" +
            "              -74.57673618693602,\n" +
            "              40.42625463036081\n" +
            "            ],\n" +
            "            [\n" +
            "              -74.588603965927,\n" +
            "              40.42625463036081\n" +
            "            ],\n" +
            "            [\n" +
            "              -74.59453785542249,\n" +
            "              40.41846628754955\n" +
            "            ],\n" +
            "            [\n" +
            "              -74.588603965927,\n" +
            "              40.41067794473829\n" +
            "            ],\n" +
            "            [\n" +
            "              -74.57673618693602,\n" +
            "              40.41067794473829\n" +
            "            ],\n" +
            "            [\n" +
            "              -74.57080229744054,\n" +
            "              40.41846628754955\n" +
            "            ]\n" +
            "          ]\n" +
            "        ]\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}\n";
    private String featureCollectionJson2 = "{\n" +
             "  \"type\": \"FeatureCollection\",\n" +
             "  \"features\": [\n" +
             "    {\n" +
             "      \"type\": \"Feature\",\n" +
             "      \"properties\": {\n" +
             "        \"poiCount\": 22,\n" +
             "        \"id\": 0\n" +
             "      },\n" +
             "      \"geometry\": {\n" +
             "        \"type\": \"Polygon\",\n" +
             "        \"coordinates\": [\n" +
             "          [\n" +
             "            [\n" +
             "              -74.57080229744054,\n" +
             "              40.41846628754955\n" +
             "            ],\n" +
             "            [\n" +
             "              -74.57673618693602,\n" +
             "              40.42625463036081\n" +
             "            ],\n" +
             "            [\n" +
             "              -74.588603965927,\n" +
             "              40.42625463036081\n" +
             "            ],\n" +
             "            [\n" +
             "              -74.59453785542249,\n" +
             "              40.41846628754955\n" +
             "            ],\n" +
             "            [\n" +
             "              -74.588603965927,\n" +
             "              40.41067794473829\n" +
             "            ],\n" +
             "            [\n" +
             "              -74.57673618693602,\n" +
             "              40.41067794473829\n" +
             "            ],\n" +
             "            [\n" +
             "              -74.57080229744054,\n" +
             "              40.41846628754955\n" +
             "            ]\n" +
             "          ]\n" +
             "        ]\n" +
             "      }\n" +
             "    }\n" +
             "  ]\n" +
             "}";

    @Autowired
    public DensityService(FeatureCollectionAccessor helper){
        this.featureCollectionAccessor = helper;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<FeatureCollection> getDensities(List<String> attractionTypes) {
        return List.of(FeatureCollection.fromJson(featureCollectionJson1), FeatureCollection.fromJson(featureCollectionJson2));
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<FeatureCollection> normalizeDensities(List<FeatureCollection> featureCollections) {
        return featureCollections;
    }

    /**
     * @inheritDoc
     */
    @Override
    public FeatureCollection aggregateDensities(List<FeatureCollection> featureCollections) {
        if(featureCollections.size() == 0){
            throw new IllegalArgumentException("featureCollection must not be an empty list");
        }

        FeatureCollection combinedFeatures = featureCollectionAccessor.copyFeatureIds(featureCollections.stream().findFirst().get());

        for (Feature countingFeature : combinedFeatures.features()) {
            aggregateDensity(featureCollections, countingFeature, (id, density) -> featureCollectionAccessor.setDensity(combinedFeatures, id, density));
        }

        return combinedFeatures;
    }

    /**
     * Increments the density value of the combinedFeatures collection
     * @param featureCollections List of input feature collections containing normalized densities
     * @param countingFeature Feature that contains the measured poiCount
     * @param densitySetter Lambda expression to set the density in the FeatureCollection
     */
    private void aggregateDensity(List<FeatureCollection> featureCollections, Feature countingFeature, DensitySetter densitySetter) {
        int id = countingFeature.properties().get(GEOJSON_FEATURE_PROPERTY_ID).getAsInt();

        int density = countingFeature.properties().get(GEOJSON_FEATURE_PROPERTY_POICOUNT).getAsInt();

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
        void set(int id, int density);
    }
}
