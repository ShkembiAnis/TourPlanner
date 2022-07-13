package at.technikum_wien.tourplanner_anis_mariel.businessLayer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MapManagerTest {

    @Test
    void testGetRouteDistance(){
        Assertions.assertEquals("81.566", MapManager.requestRouteDistance("koplik", "shkoder"));
        Assertions.assertNotEquals("smth else", MapManager.requestRouteDistance("koplik", "shkoder"));
    }

    @Test
    void testEscapeCharacters(){
        Assertions.assertEquals("Itshouldlooklikethis", MapManager.escapeCharacters("It should look like this"));
    }
}