import at.technikum_wien.tourplanner_anis_mariel.dataLayer.DataFactory;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.IDataLayer;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataLayerTests {
    IDataLayer dataLayer = DataFactory.getDatabase();

    public DataLayerTests() throws FileNotFoundException {
    }

    @Test
    void testCreateConnection() throws SQLException, FileNotFoundException {
        assertEquals(dataLayer., true);
    }
}
