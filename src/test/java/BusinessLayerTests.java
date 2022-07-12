import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessFactory;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.DataFactory;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ManageTourDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static javax.print.attribute.TextSyntax.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusinessLayerTests {

    IBusinessLayer businessLayer;

    @BeforeEach
    void setUp(){
        businessLayer = BusinessFactory.getBusiness();
    }


    @Test
    public void GetItemsTest throws SQLException{
        ManageTourDao tourItemPostgresDAO = mock(ManageTourDao.class);
        when(tourItemPostgresDAO.GetItems())
                .thenReturn(new ArrayList<>());
        try (MockedStatic<DataFactory> mb = Mockito.mockStatic(DALFactory.class)) {
            mb.when(DALFactory::CreateTourItemDAO)
                    .thenReturn(tourItemPostgresDAO);
            assertEquals(0,businessLayer.GetItems().size());
            verify(tourItemPostgresDAO).GetItems();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
