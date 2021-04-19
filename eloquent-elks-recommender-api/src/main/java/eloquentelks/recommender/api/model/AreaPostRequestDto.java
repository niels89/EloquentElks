package eloquentelks.recommender.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents a user's request for an area recommendation
 */
@Getter
@Setter
public class AreaPostRequestDto {

    /**
     * Defines the type of attraction to be considered for the recommendation of an area
     */
    private List<String> attractionTypes;
}
