package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessFactory;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class TourAddController implements Initializable{

    private final TourAddModel tourAddModel;
    private IBusinessLayer businessLayer = BusinessFactory.getBusiness();

    @FXML
    private TextField tourName;
    private Consumer<TourAddModel> newTourListener;

    public TourAddController(TourAddModel tourAddModel){
        this.tourAddModel = tourAddModel;
    }

    public void addTour(ActionEvent actionEvent) throws SQLException, IOException {
        this.newTourListener.accept(this.tourAddModel);
        TourModel tempTourModel = new TourModel();
        //tempTourModel.setId(tourAddModel.getId());
        tempTourModel.setName(tourAddModel.getName());
        businessLayer.CreateTourItem(tempTourModel);
    }

    public void addListener(Consumer<TourAddModel> listenToNewTour) {
        this.newTourListener = listenToNewTour;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tourName.textProperty().bindBidirectional(this.tourAddModel.nameProperty());
        }

    public void importTour(ActionEvent actionEvent) {
    }

    public void exportTour(ActionEvent actionEvent) {
    }
}
