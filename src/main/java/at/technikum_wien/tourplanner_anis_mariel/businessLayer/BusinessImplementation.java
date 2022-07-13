package at.technikum_wien.tourplanner_anis_mariel.businessLayer;

import at.technikum_wien.tourplanner_anis_mariel.dataLayer.DataFactory;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.fileAccess.IFileAccess;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ITourDao;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ITourLogDao;
import at.technikum_wien.tourplanner_anis_mariel.logger.ILoggerWrapper;
import at.technikum_wien.tourplanner_anis_mariel.logger.LoggerFactory;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogItemCellModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        if (tourDAO == null){
            logger.error("Cant access TourItemDao");
            return null;
        }
        TourModel result = tourDAO.addTour(tourModel);
        return result;
    }

    @Override
    public boolean UpdateTourItem(TourModel tourModel) throws SQLException, FileNotFoundException {
        logger.debug("Update tour item");
        ITourDao tourItemDAO = DataFactory.ManageTourDao();
        IFileAccess fileAccess = DataFactory.GetFileAccess();
        if(tourModel.getId() != null){
            fileAccess.saveImage(MapManager.requestRouteImage(tourModel.getFrom(),tourModel.getTo()),tourModel.getId());
        }
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
        IFileAccess fileAccess = DataFactory.GetFileAccess();
        fileAccess.deleteImage(id);
        if (tourDAO == null){
            logger.error("Cant access TourItemDao");
            return false;
        }
        return tourDAO.deleteTourItem(id);
    }


    // Log
    @Override
    public List<TourLogItemCellModel> GetLogsForItem(TourModel tourModel) throws SQLException, IOException, ParseException {
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
        return false; // not implemented
    }

    @Override
    public boolean DeleteTourLog(int id) throws SQLException {
        return false; // not implemented
    }

    // Pdf
    @Override
    public boolean CreateReportForTour(TourModel tourModel, String path) throws SQLException, IOException, ParseException {
        logger.debug("Create PDF report for item");
        ITourLogDao tourLogDao = DataFactory.ManageTourLogDao();
        if (tourLogDao == null){
            logger.error("Cant access TourLogDao");
            return false;
        }
        IFileAccess fileAccess = DataFactory.GetFileAccess();
        if (fileAccess == null){
            logger.error("Cant access FileAccess");
            return false;
        }
        return fileAccess.GenerateReport(tourModel, path);
    }

    // Map
    @Override
    public boolean hasValidRoute(String start, String end) {
        logger.debug("Has valid route");
        String jsonString = MapManager.requestRoute(start,end);
        if (jsonString == null){
            logger.error("Cant access JsonString");
            return false;
        }
        JSONObject obj = new JSONObject(jsonString);
        return obj.getJSONObject("route").has("distance");
    }

    @Override
    public Image requestRouteImage(int id) throws FileNotFoundException {
        logger.debug("Request route image");
        IFileAccess fileAccess = DataFactory.GetFileAccess();
        BufferedImage img = fileAccess.loadImage(id);
        if (img != null){
            return SwingFXUtils.toFXImage(img, null);
        }
        logger.error("Cant access route image");
        return null;
    }

    @Override
    public String requestRouteDistance(String start, String end) {
        String distance = MapManager.requestRouteDistance(start, end);
        return distance;
    }


    @Override
    public TourModel ImportTour(String path) throws SQLException, IOException, ParseException {
        logger.debug("Import tour");
        IFileAccess fileAccess = DataFactory.GetFileAccess();
        File file =  fileAccess.loadFile(path);
        if (file == null){
            logger.error("Cant access File");
            return null;
        }
        List<String> fileLines = Files.readAllLines(Path.of(file.getAbsolutePath()));
        StringProperty temp = new SimpleStringProperty(fileLines.get(0) + " (Imported)");
        TourModel newTour = new TourModel(
                -1,
                temp,
                fileLines.get(1),
                fileLines.get(2),
                fileLines.get(3),
                fileLines.get(4)
        );
        TourModel importedTour = CreateTourItem(newTour);
        return importedTour;
    }


    @Override
    public boolean ExportTour(TourModel tourModel, String path) throws SQLException, IOException, ParseException {
        logger.debug("Export tour");
        IFileAccess fileAccess = DataFactory.GetFileAccess();
        return fileAccess.exportTour(tourModel, path);
    }
}
