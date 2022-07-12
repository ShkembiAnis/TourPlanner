package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessFactory;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import at.technikum_wien.tourplanner_anis_mariel.logger.ILoggerWrapper;
import at.technikum_wien.tourplanner_anis_mariel.logger.LoggerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.function.Consumer;

public class TourItemController {

    private TourModel tour;
    private Consumer<TourModel> onDeleteTourConsumer;
    private Stage stage = new Stage();
    private ILoggerWrapper logger = LoggerFactory.getLogger();
    private IBusinessLayer businessLayer = BusinessFactory.getBusiness();

    @FXML
    public Label name;

    @FXML
    private Node box = new HBox();

    public TourItemController(){}

    public Node getTourItemBox() {
        return box;
    }

    public void setTour(TourModel tour) {
        this.tour = tour;
        this.name.textProperty().bindBidirectional(this.tour.nameProperty());
    }

    public void onDeleteTour(ActionEvent actionEvent) {
        this.onDeleteTourConsumer.accept(this.tour);
    }

    public void summerizeReport(ActionEvent actionEvent) throws SQLException, IOException, ParseException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("Report_" + tour.getName());
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            if (!businessLayer.CreateReportForTour(tour,file.getPath())) {
                logger.error("Create report failed. Item ID: " + tour.getId());
            }
        }
    }

    public void addListenerForDeleteTour(Consumer<TourModel> listener) {
        this.onDeleteTourConsumer = listener;
    }
}
