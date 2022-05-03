package at.technikum_wien.tourplanner_anis_mariel.tourAdd;

import at.technikum_wien.tourplanner_anis_mariel.TourItemModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TourListingController implements Initializable {

    private TourListModel tourListModel;

    @FXML
    public ListView<TourModel> tours = new ListView<>();

    public TourListingController(TourListModel tourListModel) {
        this.tourListModel = tourListModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tours.setItems(this.tourListModel.getTours());
        this.tours.setCellFactory(
                tourModelListView -> new TourItemModel(p -> this.deleteTour(p)));
    }

    private void deleteTour(TourModel tourModel) {
        this.tourListModel.removeTour(tourModel);
    }
}
