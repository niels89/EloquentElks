package eloquentelks.recommender.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the model @see{@link eloquentelks.recommender.api.model.AreaPostRequestDto}
 */
public class AreaPostRequestDtoTests {

    /**
     * Tests it attractionType is handled correctly
     */
    @Test
    public void testAttractionType(){
        // arrange
        AreaPostRequestDto requestDto = new AreaPostRequestDto();

        // act
        requestDto.setAttractionType("museum");

        // assert
        assertEquals("museum", requestDto.getAttractionType());
    }
}
