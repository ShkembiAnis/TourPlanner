package at.technikum_wien.tourplanner_anis_mariel.businessLayer;

import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.sql.SQLException;

public interface IBusinessLayer {
    TourModel tourModel(TourModel tourModel) throws SQLException, IOException;

}
