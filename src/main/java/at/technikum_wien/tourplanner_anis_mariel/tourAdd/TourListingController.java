package at.technikum_wien.tourplanner_anis_mariel.tourAdd;

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
                productModelListView -> new TourItemModel(p -> this.deleteProduct(p)));
    }

    private void deleteProduct(TourModel tourModel) {
        this.tourListModel.removeProduct(tourModel);
    }
}
