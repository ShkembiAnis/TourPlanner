package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import at.technikum_wien.tourplanner_anis_mariel.TourLogItemModel;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessFactory;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import at.technikum_wien.tourplanner_anis_mariel.logger.ILoggerWrapper;
import at.technikum_wien.tourplanner_anis_mariel.logger.LoggerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.ResourceBundle;

public class TourLogController implements Initializable {

    private final TourLogModel tourLogModel;
    private final TourModel tourModel;

    private Stage stage = new Stage();

    private final ILoggerWrapper logger = LoggerFactory.getLogger();

    @FXML
    private Label logLabel = new Label();

    @FXML
    public ListView<TourLogItemCellModel> listView = new ListView<>();

    private IBusinessLayer businessLayer;

    public TourLogController(TourLogModel tourLogModel, TourModel tourModel) {
        this.tourLogModel = tourLogModel;
        this.tourModel = tourModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        businessLayer = BusinessFactory.getBusiness();
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

    public void summerizeReport(ActionEvent actionEvent) throws SQLException, IOException, ParseException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("Report_" + tourModel.getName());
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            if (!businessLayer.CreateReportForTour(tourModel,file.getPath())) {
                logger.error("Create report failed. Item ID: " + tourModel.getId());
            }
        }
    }
}
