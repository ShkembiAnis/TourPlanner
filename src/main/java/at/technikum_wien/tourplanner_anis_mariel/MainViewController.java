package at.technikum_wien.tourplanner_anis_mariel;

import at.technikum_wien.tourplanner_anis_mariel.tourAdd.TourAddController;
import at.technikum_wien.tourplanner_anis_mariel.tourAdd.TourListModel;
import at.technikum_wien.tourplanner_anis_mariel.tourAdd.TourListingController;
import at.technikum_wien.tourplanner_anis_mariel.tourAdd.TourModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    private TourListModel tourListModel;

    @FXML
    private TourAddController tourAddController;

    @FXML
    private TourListingController tourListingController;

    public MainViewController(TourListModel tourListModel) {
        this.tourListModel = tourListModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tourAddController.addListener(
                p -> this.tourListModel.addProduct(TourModel.From(p)));
    }

}