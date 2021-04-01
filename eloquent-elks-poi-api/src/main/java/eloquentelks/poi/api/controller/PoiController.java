package eloquentelks.poi.api.controller;

import eloquentelks.poi.api.model.PoiGetDto;
import eloquentelks.poi.api.service.IPoiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest controller which serves Points of Interest
 */
@RestController
public class PoiController {

    /**
     * Service that provides the Points of Interests from the database
     */
    private final IPoiService poiService;

    /**
     * Logger
     */
    private final Logger log = LoggerFactory.getLogger(PoiController.class);

    /**
     * @param poiService Service that provides the Points of Interests from the database
     */
    @Autowired
    public PoiController(IPoiService poiService){
        this.poiService = poiService;
    }

    /**
     * Returns all points of interest
     * @return A list of points of interest in New York city
     */
    @GetMapping("poi")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PoiGetDto> getAllPoi(){
        List<PoiGetDto> poiList = poiService.getAllPois();

        return poiList;
    }
}
