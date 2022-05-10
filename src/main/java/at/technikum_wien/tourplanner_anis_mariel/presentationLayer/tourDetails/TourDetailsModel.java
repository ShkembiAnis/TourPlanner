package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourDetails;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TourDetailsModel {

    public StringProperty tourName = new SimpleStringProperty();

    public String getName() {
        return tourName.get();
    }

    public StringProperty nameProperty() {
        return tourName;
    }
}
