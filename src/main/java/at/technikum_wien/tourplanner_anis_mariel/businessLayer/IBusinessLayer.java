package at.technikum_wien.tourplanner_anis_mariel.businessLayer;

import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourAddModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;
import javafx.beans.property.StringProperty;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IBusinessLayer {
    List<TourModel> GetItems() throws SQLException, IOException;
    //List<TourModel> Search(String itemName, boolean caseSensitive) throws SQLException, IOException;
    TourModel CreateTourItem(TourModel tourModel) throws SQLException, IOException;
    boolean UpdateTourItem(TourModel tourModel) throws SQLException, FileNotFoundException;
    boolean DeleteTourItem(int id) throws SQLException, FileNotFoundException;


}
