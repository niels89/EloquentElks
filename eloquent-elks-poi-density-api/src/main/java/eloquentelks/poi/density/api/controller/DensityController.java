package eloquentelks.poi.density.api.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class DensityController {


    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("density")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<DensityGetDto> getDensity(@RequestParam("attractionType") String attractionType){
        List<DensityGetDto> poiList = new ArrayList<>;



        return Arrays.of(new DensityGetDto());
    }
}
