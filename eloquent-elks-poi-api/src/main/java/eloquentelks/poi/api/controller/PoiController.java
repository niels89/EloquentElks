package eloquentelks.poi.api.controller;

import eloquentelks.poi.api.model.PoiGetDto;
import eloquentelks.poi.api.service.IPoiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller which serves Points of Interest
 */
@RestController
@RequestMapping("/api/v1/poi")
public class PoiController {

    /**
     * Service that provides the Points of Interests from the database
     */
    private final IPoiService poiService;

    /**
     * @param poiService Service that provides the Points of Interests from the database
     */
    @Autowired
    public PoiController(IPoiService poiService){
        this.poiService = poiService;
    }

    /**
     * Returns all points of interest
     * @param longitude Geographical longitude of the center
     * @param latitude Geographical latitude of the center
     * @return A list of points of interest in New York city
     */
    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("radius")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PoiGetDto> getAllPoi(@RequestParam("longitude") double longitude,
                                     @RequestParam("latitude") double latitude){
        return poiService.getAllPois(longitude, latitude);
    }

    /**
     * Returns the points of interest of a specific attraction type
     * @param attractionType Type of attraction to be returned
     * @return A list of points of interest in New York city
     */
    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("attraction")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PoiGetDto> getPoiByAttractionType(@RequestParam("attractionType") String attractionType){
        return poiService.getPoisByAttractionType(attractionType);
    }

    /**
     * Returns the famous points of interest together with their distances (in kilometers) to the specified point
     * @param longitude Geographical longitude of the reference point
     * @param latitude Geographical latitude of the reference point
     * @return A list of famous attractions of New York City with the distances to the specified point
     */
    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("famous/distance")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PoiGetDto> getFamousPoiWithDistance(@RequestParam("longitude") double longitude,
                                                    @RequestParam("latitude") double latitude){
        return poiService.getFamousPoisWithDistance(longitude, latitude);
    }
}
