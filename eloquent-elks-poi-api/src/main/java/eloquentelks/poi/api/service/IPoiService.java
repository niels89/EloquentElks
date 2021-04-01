package eloquentelks.poi.api.service;

import eloquentelks.poi.api.model.PoiGetDto;

import java.util.List;

/**
 * Service to retrieve Points of Interest from the database
 */
public interface IPoiService {
    /**
     * Loads all Points of Interest from the database and returns them as Dtos
     * @return A list of @see {@link eloquentelks.poi.api.model.PoiGetDto}, which is the structure that is exposed to the clients of the API
     */
    List<PoiGetDto> getAllPois();
}
