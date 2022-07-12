package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessFactory;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import at.technikum_wien.tourplanner_anis_mariel.logger.ILoggerWrapper;
import at.technikum_wien.tourplanner_anis_mariel.logger.LoggerFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class TourAddController implements Initializable{

    private TourAddModel tourAddModel;
    private TourModel tourModel;
    private TourListModel tourListModel;
    private IBusinessLayer businessLayer = BusinessFactory.getBusiness();
    private final ILoggerWrapper logger = LoggerFactory.getLogger();
    private Stage stage = new Stage();

    private final StringProperty tourNameField = new SimpleStringProperty("");
    private final StringProperty tourFromField = new SimpleStringProperty("");
    private final StringProperty tourDetailsField = new SimpleStringProperty("");
    private final ObjectProperty<Image> tourImageView = new SimpleObjectProperty<>();

    @FXML
    private TextField tourName;
    private Consumer<TourAddModel> newTourListener;

    public TourAddController(TourAddModel tourAddModel, TourModel tourModel, TourListModel tourListModel){
        this.tourAddModel = tourAddModel;
        this.tourModel = tourModel;
        this.tourListModel = tourListModel;
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

    public void importTour(ActionEvent actionEvent) throws SQLException, IOException, ParseException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            TourModel tourModel = businessLayer.ImportTour(file.getPath());
            if (tourModel == null) {
                logger.error("Cant Import Tour. Path: " + file.getPath());
            } else {
                tourListModel.addTour(tourModel);
                setTourItem(tourModel);
            }
        }
    }

    private void setTourItem(TourModel tempTourModel) throws FileNotFoundException {
        tourModel = tempTourModel;
        tourNameField.setValue(tempTourModel.getName());
        tourImageView.setValue(businessLayer.requestRouteImage(tempTourModel.getId()));
        tourDetailsField.setValue(
                "Start: " + tempTourModel.getFrom() + "\n"
                + "End: " + tempTourModel.getTo() + "\n"
                + "Distance: " + tempTourModel.getDescription() + "\n"
                + "Description: " + tempTourModel.getDetail()
        );
}
}
