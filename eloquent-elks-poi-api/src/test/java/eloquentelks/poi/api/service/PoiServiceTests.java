package eloquentelks.poi.api.service;

import com.mapbox.geojson.Feature;
import eloquentelks.poi.api.model.PoiGetDto;
import eloquentelks.poi.api.repository.FeatureRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PoiServiceTests {
    @Mock
    private FeatureRepository poiRepository;
    private static List<Feature> features;

    @BeforeAll
    public static void setUp(){
        Feature f1 = Feature.fromJson("{\n" +
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
                "\t}");

        Feature f2 = Feature.fromJson("{\n" +
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
                "\t}");

        features = new ArrayList<>();
        features.add(f1);
        features.add(f2);
    }

    @Test
    public void testGetAllPois(){
        // arrange
        when(poiRepository.getFeatures()).thenReturn(features);
        IPoiService service = new PoiService(poiRepository);

        // act
        List<PoiGetDto> pois = service.getAllPois();

        // assert
        assertEquals(2, pois.size());
    }
}
