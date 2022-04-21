package at.technikum_wien.tourplanner_anis_mariel.tourAdd;

import javafx.fxml.FXML;

public class TourListController {

    private TourListModel tourListModel;

    @FXML
    private TourAddController tourAddController;

    @FXML
    private TourListingController tourListingController;

    public TourListController(TourListModel tourListModel) {
        this.tourListModel = tourListModel;
    }
}
