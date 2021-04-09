package eloquentelks.recommender.api.controller;

import eloquentelks.recommender.api.model.AreaPostRequestDto;
import eloquentelks.recommender.api.model.AreaPostResponseDto;
import eloquentelks.recommender.api.model.BoundingBoxDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller which serves area recommendations
 */
@RestController
@RequestMapping("/api/v1/recommendation")
public class AreaController {

    /**
     * Recommends an area for a specific type of tourist attraction
     * @param requestDto Specifies the type of tourist attraction the user is interested in
     * @return An area as a bounding box as well as a caption
     */
    @PostMapping("area")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<AreaPostResponseDto> postArea(AreaPostRequestDto requestDto){
        List<AreaPostResponseDto> responseList = new ArrayList<>();
        AreaPostResponseDto response1 = new AreaPostResponseDto();
        response1.setName("Brooklyn");
        response1.setBoundingBox(new BoundingBoxDto(-74.05245595802104, 40.57111193313693, -73.89804379847452, 40.679089810215316));
        responseList.add(response1);

        return responseList;
    }
}
