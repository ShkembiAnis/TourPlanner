package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

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
import java.time.LocalDateTime;
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
        this.listView.setCellFactory(
                ToursListView -> new TourLogItemModel(p -> this.deleteProduct(p)));
    }

    private void deleteProduct(TourLogItemCellModel model) {
        this.tourLogModel.removeTour(model);
    }


    //Add log button clicked
    public void addLog(ActionEvent actionEvent) {
        TourLogItemCellModel t = new TourLogItemCellModel();
        LocalDateTime now = LocalDateTime.now();
        t.setDate(String.valueOf(now));
        listView.getItems().add(t);
    }

    //Save log button clicked
    //Saves the new logs and changed logs in DB
    public void saveLog(ActionEvent actionEvent) throws SQLException, IOException, ParseException {
        Iterator<TourLogItemCellModel> item = this.tourLogModel.getTourLogs().iterator();
        System.out.println(this.tourLogModel.getTourId());
        while (item.hasNext()) {
            TourLogItemCellModel temp = (TourLogItemCellModel) item;
            temp.setTourId(tourLogModel.getTourId());
            System.out.println(temp.getTourId());
            businessLayer.CreateTourLog(temp);
        }
        this.tourLogModel.saveTourModel();
    }
}
