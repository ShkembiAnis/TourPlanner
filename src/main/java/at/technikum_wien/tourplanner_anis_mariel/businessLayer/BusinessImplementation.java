package at.technikum_wien.tourplanner_anis_mariel.businessLayer;

import at.technikum_wien.tourplanner_anis_mariel.dataLayer.DataFactory;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.IDataLayer;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ITourDao;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourAddModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessImplementation implements IBusinessLayer {

    @Override
    public List<TourModel> GetItems() throws SQLException, IOException {
//        Logger log = LogManager.getLogger(AppManagerImpl.class);
//        log.info("Get tour items");

        ITourDao tourItemDAO = DataFactory.CreateTourDao();
        if (tourItemDAO == null){
            //log.error("Cant access TourItemDAO");
            return new ArrayList<>();
        }
        return tourItemDAO.GetItems();
    }

    @Override
    public TourModel CreateTourItem(TourModel tourModel) throws SQLException, IOException {
//        Logger log = LogManager.getLogger(AppManagerImpl.class);
//        log.info("Create tour item");
        ITourDao tourItemDAO = DataFactory.CreateTourDao();
        //IFileAccess fileAccess = DALFactory.GetFileAccess();
        //tourItem.setDistance(requestRouteDistance(tourItem.getStart(),tourItem.getEnd()));
        if (tourItemDAO == null){
            //log.error("Cant access TourItemDAO");
            System.out.println("TourItemDao null");
            return null;
        }
        TourModel result = tourItemDAO.addTour(tourModel);
        //fileAccess.saveImage(MapQuestManager.requestRouteImage(tourItem.getStart(),tourItem.getEnd()),result.getId());
        return result;
    }

    @Override
    public boolean UpdateTourItem(TourModel tourModel) throws SQLException, FileNotFoundException {
        return false;
    }

    @Override
    public boolean DeleteTourItem(int id) throws SQLException, FileNotFoundException {
        return false;
    }
}
