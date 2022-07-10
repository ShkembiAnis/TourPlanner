package at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao;

import at.technikum_wien.tourplanner_anis_mariel.dataLayer.DataFactory;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.IDataLayer;
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

public class ManageTourLogDao implements ITourLogDao{
    private final IDataLayer dataLayer;
    private final ILoggerWrapper logger = LoggerFactory.getLogger();

    private final String SQL_FIND_BY_LOG_ID = "SELECT * FROM \"tourlog\" WHERE \"logId\"=CAST(? AS INTEGER);";
    private final String SQL_FIND_BY_TOUR_ID = "SELECT * FROM \"tourlog\" WHERE \"tourId\"=CAST(? AS INTEGER);";
    private final String SQL_INSERT_LOG = "INSERT INTO \"tourlog\" (\"logId\",\"tourId\",\"date\",\"comment\",\"difficulty\",\"time\",\"weather\",\"rating\") VALUES (CAST(? AS INTEGER), CAST(? AS INTEGER), ?, ?, ?, ?, ?, ?);";
    private final String SQL_UPDATE_LOG = "UPDATE \"tourlog\" SET \"date\" = ?,\"comment\" = ?,\"difficulty\" = ?,\"time\" = ?,\"weather\" = ?,\"rating\" = ? WHERE \"tourId\" = CAST(? AS INTEGER);";
    private final String SQL_DELETE_LOG = "DELETE FROM \"tourlog\" WHERE \"logId\"=CAST(? AS INTEGER);";

    public ManageTourLogDao() throws FileNotFoundException{
        this.dataLayer = DataFactory.getDatabase();
    }

    @Override
    public TourLogModel FindById(Integer logId) throws SQLException, IOException, ParseException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(logId);
        List<TourLogModel> tourItems = dataLayer.TourReader(SQL_FIND_BY_LOG_ID, parameters, TourLogModel.class);

        if (tourItems.stream().findFirst().isPresent()){
            return tourItems.stream().findFirst().get();
        } else {
            logger.error("No Item with ID: " + logId);
            return null;
        }
    }

    @Override
    public TourLogModel AddNewItemLog(TourLogItemCellModel tourLogItemCellModel) throws SQLException, IOException, ParseException {
        ArrayList<Object> parameters = createTourLogParam(tourLogItemCellModel);
        parameters.add(tourLogItemCellModel.getLogId());

        int resultID = dataLayer.insertTour(SQL_INSERT_LOG, parameters);
        return FindById(resultID);
    }

    @Override
    public boolean UpdateLog(TourLogItemCellModel tourLogItemCellModel) throws SQLException {
        return false;
    }

    @Override
    public List<TourLogModel> GetLogsForItem(TourModel tourModel) throws SQLException, IOException, ParseException {
        return null;
    }

    @Override
    public boolean DeleteLog(Integer id) throws SQLException {
        return false;
    }

    private ArrayList<Object> createTourLogParam(TourLogItemCellModel tourLogItemCellModel) {
        ArrayList<Object> parameters = new ArrayList<>();

        parameters.add(tourLogItemCellModel.getTourId());
        parameters.add(tourLogItemCellModel.getDate());
        parameters.add(tourLogItemCellModel.getComment());
        parameters.add(tourLogItemCellModel.getDifficulty());
        parameters.add(tourLogItemCellModel.getTotalTime());
        parameters.add(tourLogItemCellModel.getWeather());
        parameters.add(tourLogItemCellModel.getRating());

        return parameters;
    }
}
