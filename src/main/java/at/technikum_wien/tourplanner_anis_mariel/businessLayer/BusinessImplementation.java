package at.technikum_wien.tourplanner_anis_mariel.businessLayer;

import at.technikum_wien.tourplanner_anis_mariel.dataLayer.DataFactory;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ITourDao;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ITourLogDao;
import at.technikum_wien.tourplanner_anis_mariel.logger.ILoggerWrapper;
import at.technikum_wien.tourplanner_anis_mariel.logger.LoggerFactory;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogItemCellModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BusinessImplementation implements IBusinessLayer {
    private final ILoggerWrapper logger = LoggerFactory.getLogger();


    // Tours
    @Override
    public List<TourModel> GetItems() throws SQLException, IOException {
        logger.debug("Get tour items");
        ITourDao tourItemDAO = DataFactory.ManageTourDao();
        if (tourItemDAO == null){
            logger.error("Cant access TourItemDao");
            return new ArrayList<>();
        }
        return tourItemDAO.GetItems();
    }

    @Override
    public TourModel CreateTourItem(TourModel tourModel) throws SQLException, IOException {
        logger.debug("Create tour item");
        ITourDao tourDAO = DataFactory.ManageTourDao();
        //IFileAccess fileAccess = DALFactory.GetFileAccess();
        //tourItem.setDistance(requestRouteDistance(tourItem.getStart(),tourItem.getEnd()));
        if (tourDAO == null){
            logger.error("Cant access TourItemDao");
            return null;
        }
        TourModel result = tourDAO.addTour(tourModel);
        //fileAccess.saveImage(MapQuestManager.requestRouteImage(tourItem.getStart(),tourItem.getEnd()),result.getId());
        return result;
    }

    @Override
    public boolean UpdateTourItem(TourModel tourModel) throws SQLException, FileNotFoundException {
        logger.debug("Update tour item");
        ITourDao tourItemDAO = DataFactory.ManageTourDao();
//        IFileAccess fileAccess = DALFactory.GetFileAccess();
//        fileAccess.saveImage(MapQuestManager.requestRouteImage(tourItem.getStart(),tourItem.getEnd()),tourItem.getId());
        //tourItem.setDistance(requestRouteDistance(tourItem.getStart(),tourItem.getEnd()));
        if (tourItemDAO == null){
            logger.error("Cant access TourItemDao");
            return false;
        }
        return tourItemDAO.updateTourItem(tourModel);
    }

    @Override
    public boolean DeleteTourItem(int id) throws SQLException, FileNotFoundException {
        logger.debug("Delete tour items");
        ITourDao tourDAO = DataFactory.ManageTourDao();
        //IFileAccess fileAccess = DALFactory.GetFileAccess();
        //fileAccess.deleteImage(id);
        if (tourDAO == null){
            logger.error("Cant access TourItemDao");
            return false;
        }
        return tourDAO.deleteTourItem(id);
    }


    // Log
    @Override
    public List<TourLogModel> GetLogsForItem(TourModel tourModel) throws SQLException, IOException, ParseException {
        logger.debug("Get logs for items");
        ITourLogDao tourLogDao = DataFactory.ManageTourLogDao();
        if (tourLogDao == null){
            logger.error("Cant access TourLogDao");
            return null;
        }
        return tourLogDao.GetLogsForItem(tourModel);
    }



    @Override
    public TourLogModel CreateTourLog(TourLogItemCellModel tourLogItemCellModel) throws SQLException, IOException, ParseException {
        logger.debug("Create Tour Log");
        ITourLogDao tourLogDao = DataFactory.ManageTourLogDao();
        if (tourLogDao == null){
            logger.error("Cant access TourLogDao");
            return null;
        }
        return tourLogDao.AddNewItemLog(tourLogItemCellModel);
    }

    @Override
    public boolean UpdateTourLog(TourLogItemCellModel tourLogItemCellModel) throws SQLException {
        return false;
    }

    @Override
    public boolean DeleteTourLog(int id) throws SQLException {
        return false;
    }
}
