import at.technikum_wien.tourplanner_anis_mariel.businessLayer.MapManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BusinessLayerTests {

    //IBusinessLayer businessLayer = BusinessFactory.getBusiness();



    @Test
    void testGetRouteDistance(){
        assertEquals("81.566", MapManager.requestRouteDistance("koplik", "shkoder"));
        assertNotEquals("smth else", MapManager.requestRouteDistance("koplik", "shkoder"));
    }
}
