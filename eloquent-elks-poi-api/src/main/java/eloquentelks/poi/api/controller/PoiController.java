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

@RestController
public class PoiController {

    private final IPoiService poiService;
    private final Logger log = LoggerFactory.getLogger(PoiController.class);

    @Autowired
    public PoiController(IPoiService poiService){
        this.poiService = poiService;
    }

    @GetMapping("poi")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PoiGetDto> getPoi(){
        List<PoiGetDto> poiList = poiService.getAllPois();

        return poiList;
    }
}
