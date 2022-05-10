package at.technikum_wien.tourplanner_anis_mariel.dataLayer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDataLayer {
    int insertTour (String query, ArrayList<Object> parameters) throws SQLException;
    int updateTour (String query, ArrayList<Object> parameters) throws SQLException;
    int deleteTour (String query, ArrayList<Object> parameters) throws SQLException;
}
