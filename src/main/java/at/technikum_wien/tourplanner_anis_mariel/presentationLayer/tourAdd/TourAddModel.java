package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessFactory;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TourAddModel {

    private StringProperty name = new SimpleStringProperty();
    //IBusinessLayer businessLayer = BusinessFactory.getBusiness();

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

//    public void addTour() {
//        businessLayer.tourModel();
//    }
}
