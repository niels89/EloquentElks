package eloquentelks.poi.api.service;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import eloquentelks.poi.api.mapping.PoiMapper;
import eloquentelks.poi.api.model.PoiGetDto;
import eloquentelks.poi.api.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @inheritDoc
 */
@Service
public class PoiService implements IPoiService {

    /**
     * Database accessor
     */
    private final FeatureRepository poiRepository;


    /**
     * Constructor
     * @param poiRepository MongoDb repository to access the database
     */
    @Autowired
    public PoiService(FeatureRepository poiRepository) {
        this.poiRepository = poiRepository;
    }


    /**
     * @inheritDoc
     */
    @Override
    public List<PoiGetDto> getAllPois(double longitude, double latitude) {
        List<Feature> features = poiRepository.getFeatures(Point.fromLngLat(longitude, latitude), 500);

        return PoiMapper.mapToDto(features);
    }
}
