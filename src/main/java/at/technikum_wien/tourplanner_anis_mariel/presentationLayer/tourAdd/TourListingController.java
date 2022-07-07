package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import at.technikum_wien.tourplanner_anis_mariel.TourItemModel;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessFactory;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class TourListingController implements Initializable {

    private TourListModel tourListModel;
    private TourDetailsModel tourDetailsModel;
    private IBusinessLayer businessLayer = BusinessFactory.getBusiness();
    private TourModel tourModel;

    @FXML
    public ListView<TourModel> tours = new ListView<>();

    public TourListingController(TourListModel tourListModel, TourDetailsModel tourDetailsModel) {
        this.tourListModel = tourListModel;
        this.tourDetailsModel = tourDetailsModel;
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


    @FXML
    public void tourItemClicked(javafx.scene.input.MouseEvent mouseEvent) {
        //Select the model that is clicked
        TourModel tourModelList = tours.getSelectionModel().getSelectedItem();
        //Set TourDetail and TourLog
        if(tourModelList != null){
            tourDetailsModel.setTourModel(tourModelList);
//            tourLogModel.setTourModel(tourModelList);
        }
    }

}
