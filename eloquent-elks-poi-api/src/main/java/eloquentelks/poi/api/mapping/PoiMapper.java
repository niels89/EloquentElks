package eloquentelks.poi.api.mapping;

import com.google.gson.JsonElement;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import eloquentelks.poi.api.model.PoiGetDto;

import java.util.ArrayList;
import java.util.List;

public class PoiMapper {
    public static List<PoiGetDto> mapToDto(List<Feature> features){
        List<PoiGetDto> dtos = new ArrayList<>();

        for (Feature feature: features) {
            PoiGetDto dto = new PoiGetDto();
            Point p = (Point)feature.geometry();
            dto.setLatitude(p.latitude());
            dto.setLongitude(p.longitude());

            dto.setName(getProperty(feature, "name"));
            dto.setType(getProperty(feature, "tourism"));

            dtos.add(dto);
        }

        return dtos;
    }

    private static String getProperty(Feature feature, String name){
        JsonElement element = feature.properties().get(name);

        if(element == null){
            return null;
        }

        return element.getAsString();
    }
}
