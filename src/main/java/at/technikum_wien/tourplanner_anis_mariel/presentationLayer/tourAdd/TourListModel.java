package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessFactory;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class TourListModel {

    private IBusinessLayer businessLayer = BusinessFactory.getBusiness();
    private ObservableList<TourModel> tours = FXCollections.observableArrayList(businessLayer.GetItems());

    public TourListModel() throws SQLException, IOException {
    }

    public ObservableList<TourModel> getTours() {
        return tours;
    }

//    public void setTours(ObservableList<TourModel> tours){
//        this.tours = tours;
//    }

    public void addTour(TourModel tour) {
        this.tours.add(tour);
    }

    public void removeTour(TourModel tour) {
        this.tours.remove(tour);
    }
}
