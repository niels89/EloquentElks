package eloquentelks.poi.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a Point of Interest
 */
@Getter
@Setter
@ToString
public class PoiGetDto {
    /**
     * Name of the point of interest
     */
    private String name;
    private String type;
    private double longitude;
    private double latitude;
}
