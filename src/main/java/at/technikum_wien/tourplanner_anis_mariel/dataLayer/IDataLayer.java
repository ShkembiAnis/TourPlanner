package at.technikum_wien.tourplanner_anis_mariel.dataLayer;

import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDataLayer {
    int insertTour (String query, TourModel tourModel) throws SQLException;
    int updateTour (TourModel tourModel) throws SQLException;
    int deleteTour (TourModel tourModel) throws SQLException;
}
