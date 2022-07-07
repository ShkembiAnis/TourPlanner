package at.technikum_wien.tourplanner_anis_mariel.dataLayer;

import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourAddModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IDataLayer {
    int insertTour (String query, ArrayList<Object> parameters) throws SQLException;
    int updateTour (String query, ArrayList<Object> parameters) throws SQLException;
    int deleteTour (String query, ArrayList<Object> parameters) throws SQLException;
    Integer getMaxId(String query) throws SQLException, FileNotFoundException;
    <T>List<T> TourReader(String query, Class<T> tourType) throws SQLException;
    <T>List<T> TourReader(String query, ArrayList<Object> parameters, Class<T> tourType) throws SQLException, IOException;
}
