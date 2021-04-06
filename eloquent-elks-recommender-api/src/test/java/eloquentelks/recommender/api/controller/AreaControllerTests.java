package eloquentelks.recommender.api.controller;

import eloquentelks.recommender.api.model.AreaPostRequestDto;
import eloquentelks.recommender.api.model.AreaPostResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for @see{@link eloquentelks.recommender.api.controller.AreaController}
 */
public class AreaControllerTests {

    /**
     * Tests if the @see{@link eloquentelks.recommender.api.controller.AreaController} returns an area recommendation
     */
    @Test
    public void testGetArea(){
        // arrange
        AreaController controller = new AreaController();
        AreaPostRequestDto requestDto = new AreaPostRequestDto();
        requestDto.setAttractionType("restaurant");

        // act
        List<AreaPostResponseDto> area = controller.postArea(requestDto);

        // assert
        assertNotNull(area);
        assertEquals("Brooklyn", area.get(0).getName());
    }
}
