package at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao;

import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogItemCellModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface ITourLogDao {
    TourLogModel FindById(Integer logId) throws SQLException, IOException, ParseException;
    TourLogModel AddNewItemLog(TourLogItemCellModel tourLogItemCellModel) throws SQLException, IOException, ParseException;
    boolean UpdateLog(TourLogItemCellModel tourLogItemCellModel) throws SQLException;
    List<TourLogItemCellModel> GetLogsForItem(TourModel tourModel) throws SQLException, IOException, ParseException;
    boolean DeleteLog(Integer id) throws SQLException;
}
