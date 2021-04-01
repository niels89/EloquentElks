package eloquentelks.poi.api.service;

import com.mapbox.geojson.Feature;
import eloquentelks.poi.api.mapping.PoiMapper;
import eloquentelks.poi.api.model.PoiGetDto;
import eloquentelks.poi.api.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoiService implements IPoiService {

    private final FeatureRepository poiRepository;

    @Autowired
    public PoiService(FeatureRepository poiRepository) {
        this.poiRepository = poiRepository;
    }

    @Override
    public List<PoiGetDto> getAllPois() {
        List<Feature> features = poiRepository.getFeatures();

        return PoiMapper.mapToDto(features);
    }
}
