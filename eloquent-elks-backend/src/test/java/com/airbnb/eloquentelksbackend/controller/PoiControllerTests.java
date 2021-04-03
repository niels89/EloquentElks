package com.airbnb.eloquentelksbackend.controller;


import com.airbnb.eloquentelksbackend.entity.PoiGetDto;
import com.airbnb.eloquentelksbackend.service.IPoiService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit tests for @see{@link com.airbnb.eloquentelksbackend.controller.PoiController}
 */
@ExtendWith(MockitoExtension.class)
public class PoiControllerTests {

    /**
     * Stub of @see{@link com.airbnb.eloquentelksbackend.service.IPoiService}
     */
    @Mock
    private IPoiService poiServiceMock;

    /**
     * List of @see{@link com.airbnb.eloquentelksbackend.entity.PoiGetDto} used by the @see{@link com.airbnb.eloquentelksbackend.service.IPoiService} stub
     */
    private static List<PoiGetDto> poiList;

    /**
     * Initialization of the @see{@link com.airbnb.eloquentelksbackend.service.IPoiService} stub
     */
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

    /**
     * Tests the retrieval of Point of Interests via the HTTP GET endpoint
     */
    @Test
    public void testGetAllPoi(){
        // arrange
        when(poiServiceMock.getAllPois(1.323d, 42.1d)).thenReturn(poiList);
        PoiController controller = new PoiController(poiServiceMock);

        // act
        List<PoiGetDto> result = controller.getAllPoi(1.323d, 42.1d);

        // assert
        assertEquals(2, result.size());
    }
}
