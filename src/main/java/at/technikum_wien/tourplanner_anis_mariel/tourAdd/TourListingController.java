package at.technikum_wien.tourplanner_anis_mariel.tourAdd;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class TourListingController {

    private TourListModel tourListModel;

    @FXML
    public ListView<TourModel> tours = new ListView<>();

    public TourListingController(TourListModel tourListModel) {
        this.tourListModel = tourListModel;
    }
}
