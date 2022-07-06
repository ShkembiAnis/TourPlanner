package at.technikum_wien.tourplanner_anis_mariel.dataLayer;

import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ITourDao;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourAddModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateTourDao implements ITourDao {

    public final IDataLayer dataLayer;

    public String SQL_FIND_BY_ID = "SELECT * FROM \"tour\" WHERE \"tourid_pk\"=CAST(? AS INTEGER);";
    public String SQL_INSERT_TOUR = "INSERT INTO \"tour\" (\"tourid_pk\", \"tourname\") VALUES (CAST(? AS INTEGER), ?);";
    public String SQL_MAX_ID = "SELECT CAST(max(tourid_pk)+1 as INTEGER) FROM tour";

    public CreateTourDao() throws FileNotFoundException {
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
//            Logger log = LogManager.getLogger(TourItemPostgresDAO.class);
//            log.error("No Item with ID: " + itemId);
            return null;
        }
    }

    @Override
    public TourModel addTour(TourModel tourModel) throws SQLException, IOException {
        ArrayList<Object> parameters = createTourItemParam(tourModel);
        int result = dataLayer.insertTour(SQL_INSERT_TOUR, parameters);

        return findItemByID(result);
    }


    @Override
    public boolean updateTourItem(TourModel tourModel) throws SQLException {
        return false;
    }

    @Override
    public List<TourModel> GetItems() throws SQLException, IOException {
        return null;
    }

    @Override
    public boolean deleteTourItem(Integer deleteId) throws SQLException {
        return false;
    }

    private ArrayList<Object> createTourItemParam(TourModel tourModel) throws SQLException, FileNotFoundException {
        ArrayList<Object> parameters = new ArrayList<>();
        int temp = dataLayer.getNextId(SQL_MAX_ID);
        parameters.add(temp);
        parameters.add(tourModel.getName());

        return parameters;
    }

}
