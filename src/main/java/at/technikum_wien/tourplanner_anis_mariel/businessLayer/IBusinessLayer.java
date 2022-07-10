package at.technikum_wien.tourplanner_anis_mariel.businessLayer;

import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogItemCellModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IBusinessLayer {
    // Tours
    List<TourModel> GetItems() throws SQLException, IOException;
    //List<TourModel> Search(String itemName, boolean caseSensitive) throws SQLException, IOException;
    TourModel CreateTourItem(TourModel tourModel) throws SQLException, IOException;
    boolean UpdateTourItem(TourModel tourModel) throws SQLException, FileNotFoundException;
    boolean DeleteTourItem(int id) throws SQLException, FileNotFoundException;

    // Logs
    boolean DeleteTourLog(int id) throws SQLException;
    TourLogModel CreateTourLog(TourLogItemCellModel tourLogItemCellModel) throws SQLException, IOException, ParseException;
    boolean UpdateTourLog(TourLogItemCellModel tourLogItemCellModel) throws SQLException;
    List<TourLogModel> GetLogsForItem(TourModel tourModel) throws SQLException, IOException, ParseException;


}
