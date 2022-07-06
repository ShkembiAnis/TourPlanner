package at.technikum_wien.tourplanner_anis_mariel.dataLayer;

import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ITourDao;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourAddModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateTourDao implements ITourDao {

    public final IDataLayer dataLayer;

    public String insertTourQuery = "INSERT INTO tour(tourid_pk, tourname) VALUES (1, ?)";

    public CreateTourDao() throws FileNotFoundException {
        dataLayer = DataFactory.getDatabase();
    }

    @Override
    public TourModel findItemByID(Integer itemId) throws SQLException {
        return null;
    }

    @Override
    public TourModel addTour(TourModel tourModel) throws SQLException, IOException {
        ArrayList<Object> parameters = createTourItemParam(tourModel);

        return null;
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

    private ArrayList<Object> createTourItemParam(TourModel tourModel) {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(tourModel.getName());

        return parameters;
    }

}
