package at.technikum_wien.tourplanner_anis_mariel.tourAdd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class TourAddController  implements Initializable{


    private final TourAddModel tourAddModel;
    @FXML
    private TextField tourName;
    private Consumer<TourAddModel> newTourListener;

    public TourAddController(TourAddModel tourAddModel) {
        this.tourAddModel = tourAddModel;
    }

    public void addTour(ActionEvent actionEvent) {
        this.newTourListener.accept(this.tourAddModel);
    }

    public void addListener(Consumer<TourAddModel> listenToNewTour) {
        this.newTourListener = listenToNewTour;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tourName.textProperty().bindBidirectional(this.tourAddModel.nameProperty());
        }
}
