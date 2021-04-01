package eloquentelks.poi.api.mapping;

import com.google.gson.JsonElement;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import eloquentelks.poi.api.model.PoiGetDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps Points of Interest from Features (GeoJson) to @see {@link eloquentelks.poi.api.model.PoiGetDto}
 */
public class PoiMapper {

    /**
     * Maps GeoJson features to @see {@link eloquentelks.poi.api.model.PoiGetDto}
     *
     * @param features A list of GeoJson features
     * @return A list of @see {@link eloquentelks.poi.api.model.PoiGetDto}
     */
    public static List<PoiGetDto> mapToDto(List<Feature> features){
        List<PoiGetDto> dtos = new ArrayList<>();

        for (Feature feature: features) {
            PoiGetDto dto = fromFeature(feature);

            dtos.add(dto);
        }

        return dtos;
    }

    /**
     * Takes a GeoJson feature and converts it to a @see {@link eloquentelks.poi.api.model.PoiGetDto}
     *
     * @param feature GeoJson feature
     * @return
     */
    private static PoiGetDto fromFeature(Feature feature){
        PoiGetDto dto = new PoiGetDto();
        Point p = (Point)feature.geometry();
        dto.setLatitude(p.latitude());
        dto.setLongitude(p.longitude());

        dto.setName(getProperty(feature, "name"));
        dto.setType(getProperty(feature, "tourism"));

        return dto;
    }

    /**
     * Retrieves the GeoJson feature property with the specified name
     *
     * @param feature A GeoJson feature
     * @param name The name of the desired feature property
     * @return String containing the value of the accessed property
     */
    private static String getProperty(Feature feature, String name){
        JsonElement element = feature.properties().get(name);

        if(element == null){
            return null;
        }

        return element.getAsString();
    }
}
