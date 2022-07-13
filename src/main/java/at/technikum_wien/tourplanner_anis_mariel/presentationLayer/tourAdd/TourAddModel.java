package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TourAddModel {
    private TourModel tourModel;

    private StringProperty name = new SimpleStringProperty();
    private int id;

    public TourAddModel(TourModel tourModel) {
        this.tourModel = tourModel;
    }
    //IBusinessLayer businessLayer = BusinessFactory.getBusiness();

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    private void setTourName(StringProperty name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
