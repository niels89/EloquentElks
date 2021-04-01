package eloquentelks.poi.api.service;

import com.mapbox.geojson.Feature;
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
    public List<PoiGetDto> getAllPois() {
        List<Feature> features = poiRepository.getFeatures();

        return PoiMapper.mapToDto(features);
    }
}
