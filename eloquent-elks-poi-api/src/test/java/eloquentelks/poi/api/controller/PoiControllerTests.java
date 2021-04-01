package eloquentelks.poi.api.controller;

import eloquentelks.poi.api.model.PoiGetDto;
import eloquentelks.poi.api.service.IPoiService;
import eloquentelks.poi.api.service.PoiService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PoiControllerTests {
    @Mock
    private IPoiService poiServiceMock;

    private static List<PoiGetDto> poiList;

    @BeforeAll
    public static void setUp(){
        PoiGetDto poi1 = new PoiGetDto();
        poi1.setLatitude(42.1d);
        poi1.setLongitude(1.323d);

        PoiGetDto poi2 = new PoiGetDto();
        poi2.setLatitude(-25.49d);
        poi2.setLongitude(173.82d);

        poiList = new ArrayList<>();
        poiList.add(new PoiGetDto());
        poiList.add(new PoiGetDto());
    }

    @Test
    public void testGetPoi(){
        // arrange
        when(poiServiceMock.getAllPois()).thenReturn(poiList);
        PoiController controller = new PoiController(poiServiceMock);

        // act
        List<PoiGetDto> result = controller.getPoi();


        // assert
        assertEquals(2, result.size());
    }
}
