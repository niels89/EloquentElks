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
            int id = k.intValue();
            int poiCount = v.intValue();

            features.add(Feature.fromJson(
                    "{\n" +
                            "      \"type\": \"Feature\",\n" +
                            "      \"properties\": {\n" +
                            "        \"poiCount\": " + poiCount + ",\n" +
                            "        \"id\": " + id + "\n" +
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
                            "    }\n")
            );
        });
        return features;
    }
}
