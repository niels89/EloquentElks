package eloquentelks.poi.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PoiGetDto {
    private String name;
    private String type;
    private double longitude;
    private double latitude;

}
