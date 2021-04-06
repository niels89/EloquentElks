package eloquentelks.recommender.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a recommended area
 */
@Getter
@Setter
public class AreaPostResponseDto {
    /**
     * Name of the recommended area
     */
    private String name;

    /**
     * Bounding box defining a recommended area
     */
    private BoundingBoxDto boundingBox;
}
