package eloquentelks.poi.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a Point of Interest
 */
@Getter
@Setter
public class PoiGetDto {
    /**
     * Name of the point of interest
     */
    private String name;
    private String type;
    private double longitude;
    private double latitude;

    /**
     * Distance to this famous Point of Interest (in kilometers)
     */
    private double distance;
}
