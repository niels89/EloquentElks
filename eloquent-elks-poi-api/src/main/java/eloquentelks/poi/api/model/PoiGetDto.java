package eloquentelks.poi.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
public class PoiGetDto {
    private double longitude;
    private double latitude;
}
