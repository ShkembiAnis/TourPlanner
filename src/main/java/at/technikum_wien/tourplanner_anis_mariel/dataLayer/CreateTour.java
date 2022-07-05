package at.technikum_wien.tourplanner_anis_mariel.dataLayer;

import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ITourDao;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourAddModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateTour  {

//    public final IDataLayer dataLayer;
//
//    public String insertTourQuery = "INSERT INTO tour(tourid_pk, tourname) VALUES (1, ?)";
//
//    public CreateTour() throws FileNotFoundException {
//        dataLayer = DataFactory.getDatabase();
//    }
//
//
//    private ArrayList<Object> createTourDetails(TourAddModel tourAddModel){
//        ArrayList<Object> parameters = new ArrayList<>();
//        parameters.add(tourAddModel.getName());
//        return parameters;
//    }
//
//    @Override
//    public int addTour(TourAddModel tourAddModel) throws SQLException, IOException {
//        ArrayList<Object> parameters = createTourDetails(tourAddModel);
//        int result = dataLayer.insertTour(insertTourQuery, parameters);
//        return result;
//    }
//
//    @Override
//    public int updateTourDetails(TourModel tourModel) throws SQLException {
//        return 0;
//    }
//
//    @Override
//    public int deleteTourDetails(TourModel tourModel) throws SQLException {
//        return 0;
//    }
}
