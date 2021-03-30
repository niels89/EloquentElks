package eloquentelks.poi.api.service;

import eloquentelks.poi.api.model.PoiGetDto;

import java.util.List;

public interface IPoiService {
    List<PoiGetDto> getAllPois();
}
