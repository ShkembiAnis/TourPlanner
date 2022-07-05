package at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao;

import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourAddModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.IOException;
import java.sql.SQLException;

public interface ITourDao {
    int addTour(TourAddModel tourAddModel)throws SQLException, IOException;
    int updateTourDetails(TourModel tourModel) throws SQLException;
    int deleteTourDetails(TourModel tourModel) throws SQLException;
}
