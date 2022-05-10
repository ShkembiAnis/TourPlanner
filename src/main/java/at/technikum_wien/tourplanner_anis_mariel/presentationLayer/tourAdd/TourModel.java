package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TourModel {
    private StringProperty name = new SimpleStringProperty();

    public static TourModel From(TourAddModel source) {
        var newInstance = new TourModel();
        newInstance.name.set(source.getName());
        return newInstance;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }


}
