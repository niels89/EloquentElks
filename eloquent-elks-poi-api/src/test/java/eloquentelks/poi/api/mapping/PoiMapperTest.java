package eloquentelks.poi.api.mapping;

import com.mapbox.geojson.Feature;
import eloquentelks.poi.api.model.PoiGetDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for @see{@link eloquentelks.poi.api.mapping.PoiMapper}
 */
class PoiMapperTest {

    /**
     * Features list that is used for mapping
     */
    private static List<Feature> features;

    /**
     * Sets up two features from json structures that will be used for the mapping process
     */
    @BeforeAll
    public static void setUp(){
        Feature f1 = Feature.fromJson("{\n" +
                "\t  \"type\": \"Feature\",\n" +
                "\t  \"properties\": {\n" +
                "\t\t\"@id\": \"node/367697349\",\n" +
                "\t\t\"addr:state\": \"NJ\",\n" +
                "\t\t\"ele\": \"9\",\n" +
                "\t\t\"gnis:county_name\": \"Union\",\n" +
                "\t\t\"gnis:feature_id\": \"2056103\",\n" +
                "\t\t\"gnis:import_uuid\": \"57871b70-0100-4405-bb30-88b2e001a944\",\n" +
                "\t\t\"gnis:reviewed\": \"no\",\n" +
                "\t\t\"name\": \"Bonnel House Historical Museum\",\n" +
                "\t\t\"source\": \"USGS Geonames\",\n" +
                "\t\t\"tourism\": \"museum\"\n" +
                "\t  },\n" +
                "\t  \"geometry\": {\n" +
                "\t\t\"type\": \"Point\",\n" +
                "\t\t\"coordinates\": [\n" +
                "\t\t  -74.2084784,\n" +
                "\t\t  40.6637138\n" +
                "\t\t]\n" +
                "\t  },\n" +
                "\t  \"id\": \"node/367697349\"\n" +
                "\t}");

        Feature f2 = Feature.fromJson("{\n" +
                "\t  \"type\": \"Feature\",\n" +
                "\t  \"properties\": {\n" +
                "\t\t\"@id\": \"node/367697388\",\n" +
                "\t\t\"addr:state\": \"NJ\",\n" +
                "\t\t\"ele\": \"3\",\n" +
                "\t\t\"gnis:county_name\": \"Hudson\",\n" +
                "\t\t\"gnis:feature_id\": \"2060323\",\n" +
                "\t\t\"gnis:import_uuid\": \"57871b70-0100-4405-bb30-88b2e001a944\",\n" +
                "\t\t\"gnis:reviewed\": \"no\",\n" +
                "\t\t\"name\": \"Hoboken Historical Museum\",\n" +
                "\t\t\"source\": \"USGS Geonames\",\n" +
                "\t\t\"tourism\": \"museum\",\n" +
                "\t\t\"wikidata\": \"Q14705586\"\n" +
                "\t  },\n" +
                "\t  \"geometry\": {\n" +
                "\t\t\"type\": \"Point\",\n" +
                "\t\t\"coordinates\": [\n" +
                "\t\t  -74.0249925,\n" +
                "\t\t  40.7521438\n" +
                "\t\t]\n" +
                "\t  },\n" +
                "\t  \"id\": \"node/367697388\"\n" +
                "\t}");

        features = new ArrayList<>();
        features.add(f1);
        features.add(f2);
    }

    /**
     * Tests if the name of a feature is mapped correctly
     */
    @Test
    public void testMapToDto_name(){
        // act
        List<PoiGetDto> poiGetDtos = PoiMapper.mapToDto(features);

        // assert
        assertEquals("Bonnel House Historical Museum", poiGetDtos.get(0).getName());
        assertEquals("Hoboken Historical Museum", poiGetDtos.get(1).getName());
    }

    /**
     * Tests if the type of a feature is mapped correctly
     */
    @Test
    public void testMapToDto_type(){
        // act
        List<PoiGetDto> poiGetDtos = PoiMapper.mapToDto(features);

        // assert
        assertEquals("museum", poiGetDtos.get(0).getType());
        assertEquals("museum", poiGetDtos.get(1).getType());
    }
}