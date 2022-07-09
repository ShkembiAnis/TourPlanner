package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessFactory;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.ConfigManager;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TourDetailsController implements Initializable {

    private final TourDetailsModel tourDetailsModel;
    private IBusinessLayer businessLayer;

    @FXML
    private ImageView imageView;
    @FXML
    private Button editButton;
    @FXML
    private Button saveButton;

    @FXML
    private TextField tourName;
    @FXML
    private TextField tourFrom;
    @FXML
    private TextField tourTo;
    @FXML
    private TextField tourDesc;
    @FXML
    private TextField tourDist;

    @FXML
    private Label tourNameLabel;
    @FXML
    private Label tourFromLabel;
    @FXML
    private Label tourToLabel;
    @FXML
    private Label tourDescLabel;
    @FXML
    private Label tourDistLabel;

    //private IBusinessLayer manager = BusinessLayerFactory.GetManager();

    public TourDetailsController(TourDetailsModel tourDetailsModel) {
        this.tourDetailsModel = tourDetailsModel;
    }

    //Logger log = LogManager.getLogger(TourDetailsController.class);

    public void editMode(ActionEvent actionEvent) {
        //Change to edit mode when the button is clicked
        if(this.tourDetailsModel.getEditMode() == true){
            this.tourDetailsModel.setEditMode(false);
            this.tourDetailsModel.setEditButton("Edit");
            this.tourDetailsModel.setWorkMode(true);
            //this.tourDetailsModel.resetTourModel();
        }else{
            this.tourDetailsModel.setEditMode(true);
            this.tourDetailsModel.setEditButton("Cancel");
            this.tourDetailsModel.setWorkMode(false);
        }
        //log.info("State of Tour Details is flipped");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Binding text value
        businessLayer = BusinessFactory.getBusiness();
        this.tourName.textProperty().bindBidirectional(this.tourDetailsModel.getTourNameProperty());
        this.tourDesc.textProperty().bindBidirectional(this.tourDetailsModel.getTourDescProperty());
        this.tourFrom.textProperty().bindBidirectional(this.tourDetailsModel.getTourFromProperty());
        this.tourTo.textProperty().bindBidirectional(this.tourDetailsModel.getTourToProperty());
        this.tourDist.textProperty().bindBidirectional(this.tourDetailsModel.getTourDistanceProperty());

        //Binding visibility
        this.tourName.visibleProperty().bind(this.tourDetailsModel.getEditModeProperty());
        this.tourDesc.visibleProperty().bind(this.tourDetailsModel.getEditModeProperty());
        this.tourFrom.visibleProperty().bind(this.tourDetailsModel.getEditModeProperty());
        this.tourTo.visibleProperty().bind(this.tourDetailsModel.getEditModeProperty());
        this.tourDist.visibleProperty().bindBidirectional(this.tourDetailsModel.getEditModeProperty());

        //Binding text value
        this.tourNameLabel.textProperty().bind(this.tourDetailsModel.getTourNameProperty());
        this.tourDescLabel.textProperty().bind(this.tourDetailsModel.getTourDescProperty());
        this.tourFromLabel.textProperty().bind(this.tourDetailsModel.getTourFromProperty());
        this.tourToLabel.textProperty().bind(this.tourDetailsModel.getTourToProperty());
        this.tourDistLabel.textProperty().bindBidirectional(this.tourDetailsModel.getTourDistanceProperty());

        //Binding visibility
        this.tourNameLabel.visibleProperty().bind(this.tourDetailsModel.getWorkingModeProperty());
        this.tourDescLabel.visibleProperty().bind(this.tourDetailsModel.getWorkingModeProperty());
        this.tourFromLabel.visibleProperty().bind(this.tourDetailsModel.getWorkingModeProperty());
        this.tourToLabel.visibleProperty().bind(this.tourDetailsModel.getWorkingModeProperty());
        this.tourDistLabel.visibleProperty().bindBidirectional(this.tourDetailsModel.getWorkingModeProperty());

        //Binding image for each tour
        this.editButton.textProperty().bindBidirectional(this.tourDetailsModel.getEditButtonProperty());
        this.imageView.imageProperty().bindBidirectional(this.tourDetailsModel.getImageProperty());
    }

    // onSave click bajm update listen
    public void saveTour(ActionEvent actionEvent) throws SQLException, IOException {
        //marrim modelin e elementit te listes
        TourModel tempTourModel = tourDetailsModel.getTourModel();

        //updetojm elementint e listes
        //tempTourModel.setId(tourDetailsModel.getTourModel().getId());
        tempTourModel.setName(tourDetailsModel.getTourName());
        tempTourModel.setFrom(tourDetailsModel.getTourFrom());
        tempTourModel.setTo(tourDetailsModel.getTourTo());
        tempTourModel.setDescription(tourDetailsModel.getTourDesc());
        tempTourModel.setDetail(tourDetailsModel.getTourDistance());

        //save to database
        businessLayer.UpdateTourItem(tempTourModel);
    }
}
