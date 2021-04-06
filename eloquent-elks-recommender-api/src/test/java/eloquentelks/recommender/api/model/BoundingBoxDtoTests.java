package eloquentelks.recommender.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the model @see{@link eloquentelks.recommender.api.model.BoundingBoxDto}
 */
public class BoundingBoxDtoTests {

    /**
     * Tests if the non-default constructor works as intended
     */
    @Test
    public void testCustomConstructor(){
        // arrange, act
        BoundingBoxDto boundingBox = new BoundingBoxDto(-75.03, 42.9, -74.93, 43.02);

        // assert
        assertEquals(-75.03, boundingBox.getMinLong());
        assertEquals(-74.93, boundingBox.getMaxLong());
        assertEquals(42.9, boundingBox.getMinLat());
        assertEquals(43.02, boundingBox.getMaxLat());
    }
}
