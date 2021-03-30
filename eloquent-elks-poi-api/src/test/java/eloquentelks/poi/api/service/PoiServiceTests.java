package eloquentelks.poi.api.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class PoiServiceTests {
    @Test
    public void TestGetAllPois(){
        IPoiService service = new PoiService();

        assertNull(service.getAllPois());
    }
}
