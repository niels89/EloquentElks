package eloquentelks.recommender.api.helper;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class will be moved to the test package as soon as the real density api is integrated
 */
public class FeatureCollectionFactory {

    public static FeatureCollection create(Map<Integer, Integer> densities) {
        List<Feature> features = createFeatures(densities);

        return FeatureCollection.fromFeatures(features);
    }

    private static List<Feature> createFeatures(Map<Integer, Integer> densities) {
        List<Feature> features = new ArrayList<>();

        densities.forEach((k, v) -> {
            int id = k;
            int poiCount = v;

            features.add(Feature.fromJson(
                    "{\n" +
                            "      \"type\": \"Feature\",\n" +
                            "      \"properties\": {\n" +
                            "        \"poiCount\": " + poiCount + ",\n" +
                            "        \"id\": " + id + "\n" +
                            "      },\n" +
                            "      \"geometry\": {\n" +
                            "        \"type\": \"Polygon\",\n" +
                            "        \"coordinates\": []\n" +
                            "        ]\n" +
                            "      }\n" +
                            "    }\n")
            );
        });
        return features;
    }
}
