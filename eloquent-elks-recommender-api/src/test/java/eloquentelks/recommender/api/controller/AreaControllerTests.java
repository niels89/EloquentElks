package eloquentelks.recommender.api.controller;

import com.mapbox.geojson.FeatureCollection;
import eloquentelks.recommender.api.helper.FeatureCollectionAccessor;
import eloquentelks.recommender.api.helper.IFeatureCollectionAccessor;
import eloquentelks.recommender.api.model.AreaPostRequestDto;
import eloquentelks.recommender.api.service.DensityService;
import eloquentelks.recommender.api.service.IDensityService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Unit tests for @see{@link eloquentelks.recommender.api.controller.AreaController}
 */
public class AreaControllerTests {

    /**
     * Tests if the @see{@link eloquentelks.recommender.api.controller.AreaController} returns an area recommendation
     */
    @Test
    public void testPostArea(){
        // arrange
        IFeatureCollectionAccessor accessor = new FeatureCollectionAccessor();
        IDensityService densityService = new DensityService(new FeatureCollectionAccessor());
        AreaController controller = new AreaController(densityService);
        AreaPostRequestDto requestDto = new AreaPostRequestDto();
        requestDto.setAttractionTypes(List.of("restaurant"));

        // act
        FeatureCollection featureCollection = FeatureCollection.fromJson(controller.postArea(requestDto));

        // assert
        assertEquals(0.09090909090909087, accessor.getDensity(featureCollection, 1));
        assertEquals(0, accessor.getDensity(featureCollection, 2));
        assertEquals(1, accessor.getDensity(featureCollection, 3));
    }
}
