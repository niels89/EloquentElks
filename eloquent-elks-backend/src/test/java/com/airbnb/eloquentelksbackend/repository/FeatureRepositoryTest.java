//package com.airbnb.eloquentelksbackend.repository;
//
//import com.airbnb.eloquentelksbackend.repository.secondary.IFeatureRepository;
//import com.mapbox.geojson.Feature;
//import com.mapbox.geojson.Point;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
///**
// * Unit tests for @see{@link com.airbnb.eloquentelksbackend.repository.FeatureRepository}
// */
//@ExtendWith(MockitoExtension.class)
//public class FeatureRepositoryTest {
//
//    /**
//     * Stub of @see{@link MongoTemplate}
//     */
//    @Mock
//    private MongoTemplate mongoTemplate;
//
//    /**
//     * List of features as strings to be returned by @see{@link MongoTemplate}
//     */
//    private static List<String> featureList;
//
//    /**
//     * Initializes two features that are returned by the MongoTemplate stub
//     */
//    @BeforeAll
//    public static void setUp(){
//        String featureJson1 = "{\n" +
//                "\t  \"type\": \"Feature\",\n" +
//                "\t  \"properties\": {\n" +
//                "\t\t\"@id\": \"node/8533468096\",\n" +
//                "\t\t\"opening_hours\": \"Mo-Sa 11:00-19:00; Su 12:00-17:00\",\n" +
//                "\t\t\"tourism\": \"gallery\"\n" +
//                "\t  },\n" +
//                "\t  \"geometry\": {\n" +
//                "\t\t\"type\": \"Point\",\n" +
//                "\t\t\"coordinates\": [\n" +
//                "\t\t  -74.0447579,\n" +
//                "\t\t  40.7868407\n" +
//                "\t\t]\n" +
//                "\t  },\n" +
//                "\t  \"id\": \"node/8533468096\"\n" +
//                "\t}";
//
//        String featureJson2 = "{\n" +
//                "\t  \"type\": \"Feature\",\n" +
//                "\t  \"properties\": {\n" +
//                "\t\t\"@id\": \"node/8541462092\",\n" +
//                "\t\t\"artwork_type\": \"mural\",\n" +
//                "\t\t\"name\": \"Yusuf Hawkins\",\n" +
//                "\t\t\"tourism\": \"artwork\"\n" +
//                "\t  },\n" +
//                "\t  \"geometry\": {\n" +
//                "\t\t\"type\": \"Point\",\n" +
//                "\t\t\"coordinates\": [\n" +
//                "\t\t  -73.9475876,\n" +
//                "\t\t  40.6804506\n" +
//                "\t\t]\n" +
//                "\t  },\n" +
//                "\t  \"id\": \"node/8541462092\"\n" +
//                "\t}";
//        featureList = Arrays.asList(featureJson1, featureJson2);
//    }
//
//    /**
//     * Checks if @see {@link com.airbnb.eloquentelksbackend.repository.FeatureRepository} returns the correct amount of features in the database
//     */
//    @Test
//    public void testGetFeatures(){
//        // arrange
//        when(mongoTemplate.findAll(String.class, "feature")).thenReturn(featureList);
//        IFeatureRepository featureRepository = new FeatureRepository(mongoTemplate);
//
//        // act
//        List<Feature> features = featureRepository.getFeatures();
//
//        // assert
//        assertEquals(2, features.size());
//    }
//
//    /**
//     * Checks if @see {@link com.airbnb.eloquentelksbackend.repository.FeatureRepository} considers the radius for returning the relevant features
//     */
//    @ParameterizedTest(name="{0}: radius={1} expectedCount={2}")
//    @CsvSource({
//            "insideRadius, 16000, 2",
//            "outsideRadius, 11000, 1"
//    })
//    public void testGetFeaturesRadius(String caption, double radius, int expectedCount){
//        // arrange
//        when(mongoTemplate.findAll(String.class, "feature")).thenReturn(featureList);
//        IFeatureRepository featureRepository = new FeatureRepository(mongoTemplate);
//        Feature f1 = Feature.fromJson(featureList.get(0));
//        Point center = (Point)f1.geometry();
//
//        // act
//        List<Feature> features = featureRepository.getFeatures(center, radius);
//
//        // assert
//        assertEquals(expectedCount, features.size());
//    }
//}