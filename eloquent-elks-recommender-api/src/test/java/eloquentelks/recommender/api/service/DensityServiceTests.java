package eloquentelks.recommender.api.service;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import eloquentelks.recommender.api.helper.FeatureCollectionAccessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for @see {@link eloquentelks.recommender.api.service.DensityService}
 */
public class DensityServiceTests {

    private String featureCollectionJson = "{\n" +
            "  \"type\": \"FeatureCollection\",\n" +
            "  \"features\": [\n" +
            "    {\n" +
            "      \"type\": \"Feature\",\n" +
            "      \"properties\": {\n" +
            "        \"poiCount\": 42,\n" +
            "        \"id\": 1\n" +
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

    /**
     * Object under test
     */
    private IDensityService densityService;

    /**
     * Test initialization
     */
    @BeforeEach
    public void setUp(){
        // arrange
        densityService = new DensityService(new FeatureCollectionAccessor());
    }

    /**
     * Tests if the density property is contained in the FeatureCollection
     */
    @Test
    public void testGetDensity()
    {
        // act
        List<FeatureCollection> densities = densityService.getDensities(List.of("museum"));
        Optional<Feature> feature = densities.stream().findFirst().get().features().stream().findFirst();
        int poiCount = feature.get().properties().get("poiCount").getAsInt();

        // assert
        assertEquals(42, poiCount);
    }

    /**
     * Tests if the density normalization works properly
     */
    @Test
    public void testNormalizeDensities(){
        // arrange
        FeatureCollection featureCollection = FeatureCollection.fromJson(featureCollectionJson);

        // act
        List<FeatureCollection> resultingCollection = densityService.normalizeDensities(List.of(featureCollection, featureCollection));

        // assert
        assertEquals(42, resultingCollection.get(0).features().get(0).properties().get("poiCount").getAsInt());
    }

    /**
     * Tests if the density aggregation works properly
     */
    @Test
    public void testAggregateDensities(){
        // arrange
        FeatureCollection featureCollection = FeatureCollection.fromJson(featureCollectionJson);

        // act
        FeatureCollection resultingCollection = densityService.aggregateDensities(List.of(featureCollection, featureCollection));

        // assert
        assertEquals(84, resultingCollection.features().get(0).properties().get("poiCount").getAsInt());
    }

    /**
     * Tests if the density aggregation throws an exception if an empty feature collection list is passed
     */
    @Test
    public void testAggregateDensities_emptyList(){
        // act, assert
        assertThrows(IllegalArgumentException.class, () -> densityService.aggregateDensities(List.of()));
    }
}
