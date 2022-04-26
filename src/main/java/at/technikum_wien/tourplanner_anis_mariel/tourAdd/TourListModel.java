package at.technikum_wien.tourplanner_anis_mariel.tourAdd;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourListModel {

    private ObservableList<TourModel> tours = FXCollections.observableArrayList();

    public ObservableList<TourModel> getTours() {
        return tours;
    }

    public void addProduct(TourModel product) {
        this.tours.add(product);
    }

    public void removeProduct(TourModel tour) {
        this.tours.remove(tour);
    }
}
