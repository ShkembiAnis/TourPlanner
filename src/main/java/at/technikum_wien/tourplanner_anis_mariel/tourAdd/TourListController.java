package at.technikum_wien.tourplanner_anis_mariel.tourAdd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TourListController implements Initializable {

    private TourListModel tourListModel;

    @FXML
    private TourAddController tourAddController;

    @FXML
    private TourListingController tourListingController;

    public TourListController(TourListModel tourListModel) {
        this.tourListModel = tourListModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tourAddController.addListener(
                p -> this.tourListModel.addProduct(TourModel.From(p)));
    }
}
