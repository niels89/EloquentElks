package eloquentelks.poi.api.repository;

import com.mapbox.geojson.Feature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit tests for @see{@link eloquentelks.poi.api.repository.FeatureRepository}
 */
public class FeatureRepositoryTest {

    /**
     * List of features as strings to be returned by @see{@link org.springframework.data.mongodb.core.MongoTemplate}
     */
    private static List<String> featureList;

    /**
     * Initializes two features that are returned by the MongoTemplate stub
     */
    @BeforeAll
    public static void setUp(){
        String featureJson1 = "{\n" +
                "\t  \"type\": \"Feature\",\n" +
                "\t  \"properties\": {\n" +
                "\t\t\"@id\": \"node/8533468096\",\n" +
                "\t\t\"opening_hours\": \"Mo-Sa 11:00-19:00; Su 12:00-17:00\",\n" +
                "\t\t\"tourism\": \"gallery\"\n" +
                "\t  },\n" +
                "\t  \"geometry\": {\n" +
                "\t\t\"type\": \"Point\",\n" +
                "\t\t\"coordinates\": [\n" +
                "\t\t  -74.0447579,\n" +
                "\t\t  40.7868407\n" +
                "\t\t]\n" +
                "\t  },\n" +
                "\t  \"id\": \"node/8533468096\"\n" +
                "\t}";

        String featureJson2 = "{\n" +
                "\t  \"type\": \"Feature\",\n" +
                "\t  \"properties\": {\n" +
                "\t\t\"@id\": \"node/8541462092\",\n" +
                "\t\t\"artwork_type\": \"mural\",\n" +
                "\t\t\"name\": \"Yusuf Hawkins\",\n" +
                "\t\t\"tourism\": \"artwork\"\n" +
                "\t  },\n" +
                "\t  \"geometry\": {\n" +
                "\t\t\"type\": \"Point\",\n" +
                "\t\t\"coordinates\": [\n" +
                "\t\t  -73.9475876,\n" +
                "\t\t  40.6804506\n" +
                "\t\t]\n" +
                "\t  },\n" +
                "\t  \"id\": \"node/8541462092\"\n" +
                "\t}";
        featureList = Arrays.asList(featureJson1, featureJson2);
    }

    /**
     * Checks if @see {@link eloquentelks.poi.api.repository.FeatureRepository} returns the correct amount of features in the database
     */
    @Test
    public void testGetFeatures(){
        // arrange
        MongoTemplate mongoTemplate = Mockito.mock(MongoTemplate.class);
        when(mongoTemplate.findAll(String.class, "feature")).thenReturn(featureList);
        IFeatureRepository featureRepository = new FeatureRepository(mongoTemplate);

        // act
        List<Feature> features = featureRepository.getFeatures();

        // assert
        assertEquals(2, features.size());
    }
}