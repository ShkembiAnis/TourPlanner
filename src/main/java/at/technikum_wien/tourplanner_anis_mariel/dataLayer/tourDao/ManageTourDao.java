package at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao;

import at.technikum_wien.tourplanner_anis_mariel.dataLayer.DataFactory;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.IDataLayer;
import at.technikum_wien.tourplanner_anis_mariel.logger.ILoggerWrapper;
import at.technikum_wien.tourplanner_anis_mariel.logger.LoggerFactory;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogItemCellModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageTourDao implements ITourDao {

    public final IDataLayer dataLayer;
    private final ILoggerWrapper logger = LoggerFactory.getLogger();

    private final String SQL_FIND_BY_ID = "SELECT * FROM \"tour\" WHERE \"tourid_pk\"=CAST(? AS INTEGER);";
    private final String SQL_INSERT_TOUR = "INSERT INTO \"tour\" (\"tourid_pk\", \"tourname\") VALUES (CAST(? AS INTEGER), ?);";
    private final String SQL_NEXT_ID = "SELECT CAST(max(tourid_pk)+1 as INTEGER) FROM tour";
    private final String SQL_GET_ALL_ITEMS = "SELECT * FROM \"tour\";";
    private final String SQL_DELETE_TOUR = "DELETE FROM \"tour\" WHERE \"tourid_pk\"=CAST(? AS INTEGER);";
    private final String SQL_UPDATE_TOUR = "UPDATE \"tour\" SET \"tourname\" = ?,\"from\" = ?,\"to\" = ?,\"description\" = ?,\"details\" = ? WHERE \"tourid_pk\"=CAST(? AS INTEGER);";

    public ManageTourDao() throws FileNotFoundException {
        dataLayer = DataFactory.getDatabase();
    }

    @Override
    public TourModel findItemByID(Integer itemId) throws SQLException, IOException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(itemId);
        List<TourModel> tourItems = dataLayer.TourReader(SQL_FIND_BY_ID, parameters, TourModel.class);

        if (tourItems.stream().findFirst().isPresent()){
            return tourItems.stream().findFirst().get();
        } else {
            logger.error("No Item with ID: " + itemId);
            return null;
        }
    }

    @Override
    public TourModel addTour(TourModel tourModel) throws SQLException, IOException {
        ArrayList<Object> parameters = createInitialTourParam(tourModel);
        int result = dataLayer.insertTour(SQL_INSERT_TOUR, parameters);

        return findItemByID(result);
    }


    @Override
    public boolean updateTourItem(TourModel tourModel) throws SQLException, FileNotFoundException {
        ArrayList<Object> parameters = createTourModelParam(tourModel);
        int check = dataLayer.updateTour(SQL_UPDATE_TOUR, parameters);

        return check > 0;
    }

    @Override
    public List<TourModel> GetItems() throws SQLException, IOException {
        return dataLayer.TourReader(SQL_GET_ALL_ITEMS, TourModel.class);
    }

    @Override
    public boolean deleteTourItem(Integer deleteId) throws SQLException {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(deleteId);
        dataLayer.deleteTour(SQL_DELETE_TOUR, parameters);
        return true;
    }

    private ArrayList<Object> createInitialTourParam(TourModel tourModel) throws SQLException, FileNotFoundException {
        ArrayList<Object> parameters = new ArrayList<>();

        parameters.add(dataLayer.getMaxId(SQL_NEXT_ID));
        parameters.add(tourModel.getName());

        return parameters;
    }

    private ArrayList<Object> createTourModelParam(TourModel tourModel) throws SQLException, FileNotFoundException {
        ArrayList<Object> parameters = new ArrayList<>();

        parameters.add(tourModel.getName());
        parameters.add(tourModel.getFrom());
        parameters.add(tourModel.getTo());
        parameters.add(tourModel.getDescription());
        parameters.add(tourModel.getDetail());
        parameters.add(tourModel.getId());

        return parameters;
    }
}
