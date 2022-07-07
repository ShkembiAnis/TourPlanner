package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TourModel {

    private StringProperty name = new SimpleStringProperty();



    private Integer id;

    public TourModel() {
    }

    public TourModel(Integer Id, StringProperty name) {
        this.id = id;
        this.name = name;
    }

    public static TourModel From(TourAddModel source) {
        var newInstance = new TourModel();
        newInstance.name.set(source.getName());
        return newInstance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
