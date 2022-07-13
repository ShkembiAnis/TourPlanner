import at.technikum_wien.tourplanner_anis_mariel.dataLayer.DataFactory;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.IDataLayer;

import java.io.FileNotFoundException;

public class DataLayerTests {
    IDataLayer dataLayer = DataFactory.getDatabase();

    public DataLayerTests() throws FileNotFoundException {
    }

//    @Test
//    void testCreateConnection() throws SQLException, FileNotFoundException {
//        assertEquals(dataLayer., true);
//    }
}
