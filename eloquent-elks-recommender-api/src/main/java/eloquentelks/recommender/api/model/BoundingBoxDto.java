package eloquentelks.recommender.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Bounding box representation according to @link https://wiki.openstreetmap.org/wiki/Bounding_Box
 */
@Getter
@Setter
public class BoundingBoxDto {

    /**
     * Left boundary
     */
    private double minLong;

    /**
     * Bottom boundary
     */
    private double minLat;

    /**
     * Right boundary
     */
    private double maxLong;

    /**
     * Top boundary
     */
    private double maxLat;

    /**
     * Custom constructor with all bounding box parameters
     * @param minLong Defines the left boundary
     * @param minLat Defines the bottom boundary
     * @param maxLong Defines the right boundary
     * @param maxLat Defines the top boundary
     */
    public BoundingBoxDto(double minLong, double minLat, double maxLong, double maxLat){
        setMinLong(minLong);
        setMinLat(minLat);
        setMaxLong(maxLong);
        setMaxLat(maxLat);
    }
}
