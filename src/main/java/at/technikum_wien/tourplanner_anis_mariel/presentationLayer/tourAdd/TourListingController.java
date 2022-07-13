package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import at.technikum_wien.tourplanner_anis_mariel.TourItemModel;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessFactory;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TourListingController implements Initializable {

    private TourListModel tourListModel;
    private TourDetailsModel tourDetailsModel;
    private TourLogModel tourLogModel;

    private IBusinessLayer businessLayer = BusinessFactory.getBusiness();

    @FXML
    public ListView<TourModel> tours = new ListView<>();

    public TourListingController(TourListModel tourListModel, TourDetailsModel tourDetailsModel, TourLogModel tourLogModel) {
        this.tourListModel = tourListModel;
        this.tourDetailsModel = tourDetailsModel;
        this.tourLogModel = tourLogModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tours.setItems(this.tourListModel.getTours());
        this.tours.setCellFactory(
                tourModelListView -> new TourItemModel(p -> {
                    try {
                        this.deleteTour(p);
                    } catch (SQLException | FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                )
        );
    }

    private void deleteTour(TourModel tourModel) throws SQLException, FileNotFoundException {
        this.tourListModel.removeTour(tourModel);
        businessLayer.DeleteTourItem(tourModel.getId());
    }


    @FXML
    public void tourItemClicked(javafx.scene.input.MouseEvent mouseEvent) throws FileNotFoundException {
        //Select the model that is clicked
        TourModel tourModelList = tours.getSelectionModel().getSelectedItem();
        //Set TourDetail and TourLog
        if(tourModelList != null){
            tourDetailsModel.setTourModel(tourModelList);
            tourDetailsModel.setImage(businessLayer.requestRouteImage(tourModelList.getId()));
            tourLogModel.setTourModel(tourModelList);
        }
    }

}
