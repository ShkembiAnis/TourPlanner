package at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao;

import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourAddModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ITourDao {
    TourModel findItemByID(Integer itemId) throws SQLException, IOException;
    TourModel addTour(TourModel tourModel)throws SQLException, IOException;
    boolean updateTourItem(TourModel tourModel) throws SQLException;
    List<TourModel> GetItems() throws SQLException, IOException;
    boolean deleteTourItem (Integer deleteId) throws SQLException;
}
