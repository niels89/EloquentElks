package eloquentelks.recommender.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the model @see{@link eloquentelks.recommender.api.model.AreaPostResponseDto}
 */
public class AreaPostResponseDtoTests {

    /**
     * Tests the name assignment
     */
    @Test
    public void testName(){
        // arrange
        AreaPostResponseDto responseDto = new AreaPostResponseDto();

        // act
        responseDto.setName("Manhattan");

        // assert
        assertEquals("Manhattan", responseDto.getName());
    }

    /**
     * Tests the bounding box assignment
     */
    @Test
    public void testBoundingBox(){
        // arrange
        AreaPostResponseDto responseDto = new AreaPostResponseDto();
        BoundingBoxDto boundingBoxDto = new BoundingBoxDto(-25, 42, -25.1, 42.1);

        // act
        responseDto.setBoundingBox(boundingBoxDto);

        // assert
        assertEquals(boundingBoxDto, responseDto.getBoundingBox());
    }

}
