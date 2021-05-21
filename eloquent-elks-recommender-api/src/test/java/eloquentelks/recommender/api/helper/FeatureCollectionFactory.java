package eloquentelks.recommender.api.helper;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class is used to generate empty features with the specified densities and ids
 */
public class FeatureCollectionFactory {

    private FeatureCollectionFactory(){
        throw new IllegalStateException("This class is not meant to be instantiated");
    }
    /**
     * Creates a FeatureCollection of densities
     * @param densities Map with Key = Id and Value = Poi Density
     * @return A FeatureCollection containing densities
     */
    public static FeatureCollection create(Map<Integer, Integer> densities) {
        List<Feature> features = createFeatures(densities);

        return FeatureCollection.fromFeatures(features);
    }

    private static List<Feature> createFeatures(Map<Integer, Integer> densities) {
        List<Feature> features = new ArrayList<>();

        densities.forEach((k, v) -> {
            var id = k;
            var poiCount = v;

            features.add(Feature.fromJson(
                    "{\n" +
                    "  \"type\": \"Feature\",\n" +
                    "  \"properties\": {\n" +
                    "    \"poiCount\": " + poiCount + ",\n" +
                    "    \"id\": " + id + "\n" +
                    "  },\n" +
                    "  \"geometry\": {\n" +
                    "    \"type\": \"Polygon\",\n" +
                    "    \"coordinates\": []\n" +
                    "  }\n" +
                    "}\n")
            );
        });
        return features;
    }
}
