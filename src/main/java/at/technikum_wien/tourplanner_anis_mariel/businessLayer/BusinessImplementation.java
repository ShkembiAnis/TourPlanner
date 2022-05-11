package at.technikum_wien.tourplanner_anis_mariel.businessLayer;

import at.technikum_wien.tourplanner_anis_mariel.dataLayer.DataFactory;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.IDataLayer;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.IOException;
import java.sql.SQLException;

public class BusinessImplementation implements IBusinessLayer {

    private IDataLayer dataLayer = DataFactory.getDatabase();
    @Override
    public TourModel tourModel(TourModel tourModel) throws SQLException, IOException {
        tourModel.nameProperty();
        if(dataLayer == null){
            System.out.println("error");
            return null;
        }
        int result = dataLayer.insertTour(tourModel);

        return result;
    }


}
