package com.airbnb.eloquentelksbackend.controller;

import com.airbnb.eloquentelksbackend.entity.Property;
import com.airbnb.eloquentelksbackend.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Test for PropertyController @see {@link com.airbnb.eloquentelksbackend.controller.PropertyController}
 */
@WebMvcTest(PropertyController.class)
//@SpringBootTest(classes = EloquentElksBackendApplication.class)
public class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Stub of ProprertyService @see {@link com.airbnb.eloquentelksbackend.service.PropertyService}
     */
    @MockBean
    private PropertyService propertyService;


    @Test
    public void getAllPropertyTest() throws Exception{

        Property testProperty = new Property();
        testProperty.setName("Test Property");
        testProperty.setLatitude(47.376888);
        testProperty.setLongitude(8.541694);
        testProperty.setRoomType("test type");

        List<Property> allProperty = Collections.singletonList(testProperty);
        given(propertyService.getAllProperties("1","999")).willReturn(allProperty);

        MockHttpServletRequestBuilder getRequest = get("/api/v1/airbnb").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(getRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].latitude", is(testProperty.getLatitude())))
                .andExpect(jsonPath("$[0].longitude", is(testProperty.getLongitude())))
                .andExpect(jsonPath("$[0].roomType", is(testProperty.getRoomType())));
    }


}
