package eloquentelks.poi.api.model;

import com.mongodb.client.model.geojson.Point;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
//@Document(collection = "pois")
public class OsmFeature {
    private Point geometry;

    public double getLatitude(){
        List<Double> values = geometry.getCoordinates().getValues();

        return values.get(1);
    }

    public double getLongitude(){
        List<Double> values = geometry.getCoordinates().getValues();

        return values.get(0);
    }
}
