package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import at.technikum_wien.tourplanner_anis_mariel.TourLogItemModel;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessFactory;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.ResourceBundle;

public class TourLogController implements Initializable {

    private final TourLogModel tourLogModel;
    @FXML
    private Label logLabel = new Label();

    @FXML
    public ListView<TourLogItemCellModel> listView = new ListView<>();

    private IBusinessLayer businessLayer = BusinessFactory.getBusiness();

    public TourLogController(TourLogModel tourLogModel) {
        this.tourLogModel = tourLogModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Bind text value
        this.logLabel.textProperty().bindBidirectional(this.tourLogModel.getTourLogProperty());

        //Set up Listview of Logs
        this.listView.setItems(this.tourLogModel.getTours());
        System.out.println(this.tourLogModel.getTours());
        this.listView.setCellFactory(
                ToursListView -> new TourLogItemModel(p -> this.deleteProduct(p)));
    }

    private void deleteProduct(TourLogItemCellModel model) {
        this.tourLogModel.removeTour(model);
    }

    //Add log button clicked
    public void addLog(ActionEvent actionEvent) throws SQLException, IOException, ParseException {
        TourLogItemCellModel temp = new TourLogItemCellModel();
        listView.getItems().add(temp);
        //businessLayer.CreateTourLog(temp);
    }

    //Save log button clicked
    //Saves the new logs and changed logs in DB
    public void saveLog(ActionEvent actionEvent) throws SQLException, IOException, ParseException {
        Iterator<TourLogItemCellModel> item = this.tourLogModel.getTourLogs().iterator();
        System.out.println(this.tourLogModel.getTourId());
        while (item.hasNext()) {
            TourLogItemCellModel temp = (TourLogItemCellModel) item;
//            temp.setTourId(tourLogModel.getTourId());
//            temp.setLogId(tourLogModel.getLogId());
            System.out.println(temp.getTourId());
            System.out.println(temp.getLogId());
            businessLayer.CreateTourLog(temp);
        }
        this.tourLogModel.saveTourModel();
    }
}
