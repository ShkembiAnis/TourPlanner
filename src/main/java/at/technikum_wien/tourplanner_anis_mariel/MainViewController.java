package at.technikum_wien.tourplanner_anis_mariel;

import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessFactory;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    private TourListModel tourListModel;
    private IBusinessLayer businessLayer;

    @FXML
    private TourAddController tourAddController;

    @FXML
    private TourListingController tourListingController;

    @FXML
    TourLogController tourLogController;

    public MainViewController(TourListModel tourListModel) {
        this.tourListModel = tourListModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        businessLayer = BusinessFactory.getBusiness();
        this.tourAddController.addListener(
                p -> this.tourListModel.addTour(TourModel.From(p)));
    }

}