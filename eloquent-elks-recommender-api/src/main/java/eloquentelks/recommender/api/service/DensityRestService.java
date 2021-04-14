package eloquentelks.recommender.api.service;

import com.mapbox.geojson.FeatureCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @inheritDoc
 */
@Service
public class DensityRestService implements IDensityRestService {

    /**
     * Protocol for the Request, in this case HTTP or HTTPS
     */
    @Value("${eloquentelks.poi.density.api.protocol}")
    private String protocol;

    /**
     * Host for the HTTP Request
     */
    @Value("${eloquentelks.poi.density.api.host}")
    private String host;

    /**
     * Port for the HTTP Request
     */
    @Value("${eloquentelks.poi.density.api.port}")
    private String port;

    /**
     * Returns the endpoint route of the poi density api
     */
    private final String endpoint = "api/v1/poiDensity";

    /**
     * @inheritDoc
     */
    @Override
    public FeatureCollection getDensity(String attractionType) {
        RestTemplate rest = new RestTemplate();

        String url = getPoiDensityEndpoint(attractionType);

        String jsonResult = rest.getForObject(url, String.class);

        return FeatureCollection.fromJson(jsonResult);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<FeatureCollection> getDensities(List<String> attractionTypes) {
        List<FeatureCollection> collections = new ArrayList<>();

        for(String attractionType: attractionTypes){
            collections.add(getDensity(attractionType));
        }

        return collections;
    }

    /**
     * Returns the complete URL for the GET request to
     * @param attractionType Type of attraction that should be considered for the density analysis
     * @return Endpoint URL
     */
    private String getPoiDensityEndpoint(String attractionType){
        return protocol+"://"+host+":"+port+"/"+endpoint+"?attractionType="+attractionType;
    }
}
