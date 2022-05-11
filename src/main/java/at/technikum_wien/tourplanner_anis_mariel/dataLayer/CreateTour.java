package at.technikum_wien.tourplanner_anis_mariel.dataLayer;

import at.technikum_wien.tourplanner_anis_mariel.TourItemModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourAddModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateTour implements IDataLayer{

    public final IDataLayer dataLayer;

    public String insertTour = "INSERT INTO tour(tourid_pk, tourname) VALUES (1, ?)";

    public CreateTour() throws FileNotFoundException {
        dataLayer = DataFactory.getDatabase();
    }


    private ArrayList<Object> createTourItem(TourModel tourModel) {
        ArrayList<Object> parametes = new ArrayList<>();
        parametes.add(tourModel.getName());
        return parametes;
    }


    @Override
    public int insertTour(String query, TourModel tourModel) throws SQLException {
        ArrayList<Object> parameteres = createTourItem(tourModel);
        int result = dataLayer.insertTour(insertTour, tourModel);
        return result;
    }

    @Override
    public int updateTour(TourModel tourModel) throws SQLException {
        return 0;
    }

    @Override
    public int deleteTour(TourModel tourModel) throws SQLException {
        return 0;
    }
}
