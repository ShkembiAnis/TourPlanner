package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.function.Consumer;

public class TourLogItemContoller {
    private TourLogItemCellModel tourLogItemCellModel;
    private Consumer<TourLogItemCellModel> onDeleteProductConsumer;


    @FXML
    private Label date;
    @FXML
    private Label comment;
    @FXML
    private Label difficulty;
    @FXML
    private Label totalTime;
    @FXML
    private Label weather;
    @FXML
    private Label rating;
    @FXML
    private TextField commentInput;
    @FXML
    private TextField difficultyInput;
    @FXML
    private TextField totalTimeInput;
    @FXML
    private TextField weatherInput;
    @FXML
    private TextField ratingInput;

    @FXML
    private Node box = new HBox();

    public Node getProductItemBox() {
        return box;
    }

    public void setProduct(TourLogItemCellModel tourLogItemCellModel) {
        this.tourLogItemCellModel = tourLogItemCellModel;

        //Bind text value
        this.date.textProperty().bindBidirectional(this.tourLogItemCellModel.dateProperty());
        this.comment.textProperty().bindBidirectional(this.tourLogItemCellModel.commentProperty());
        this.difficulty.textProperty().bindBidirectional(this.tourLogItemCellModel.difficultyProperty());
        this.totalTime.textProperty().bindBidirectional(this.tourLogItemCellModel.totalTimeProperty());
        this.weather.textProperty().bindBidirectional(this.tourLogItemCellModel.weatherProperty());
        this.rating.textProperty().bindBidirectional(this.tourLogItemCellModel.ratingProperty());

        //Bind visibility
        this.comment.visibleProperty().bind(this.tourLogItemCellModel.getWorkingModeProperty());
        this.difficulty.visibleProperty().bind(this.tourLogItemCellModel.getWorkingModeProperty());
        this.totalTime.visibleProperty().bind(this.tourLogItemCellModel.getWorkingModeProperty());
        this.weather.visibleProperty().bindBidirectional(this.tourLogItemCellModel.getWorkingModeProperty());
        this.rating.visibleProperty().bind(this.tourLogItemCellModel.getWorkingModeProperty());

        //Bind text value
        this.commentInput.textProperty().bindBidirectional(this.tourLogItemCellModel.commentProperty());
        this.difficultyInput.textProperty().bindBidirectional(this.tourLogItemCellModel.difficultyProperty());
        this.totalTimeInput.textProperty().bindBidirectional(this.tourLogItemCellModel.totalTimeProperty());
        this.weatherInput.textProperty().bindBidirectional(this.tourLogItemCellModel.weatherProperty());
        this.ratingInput.textProperty().bindBidirectional(this.tourLogItemCellModel.ratingProperty());

        //Bind visibility
        this.commentInput.visibleProperty().bind(this.tourLogItemCellModel.getEditModeProperty());
        this.difficultyInput.visibleProperty().bind(this.tourLogItemCellModel.getEditModeProperty());
        this.totalTimeInput.visibleProperty().bind(this.tourLogItemCellModel.getEditModeProperty());
        this.weatherInput.visibleProperty().bind(this.tourLogItemCellModel.getEditModeProperty());
        this.ratingInput.visibleProperty().bind(this.tourLogItemCellModel.getEditModeProperty());
    }

    public void addListenerForDeleteTour(Consumer<TourLogItemCellModel> listener) {
        this.onDeleteProductConsumer = listener;
    }

    //Button delete
    public void onDeleteTourLog(ActionEvent actionEvent) throws SQLException, IOException {
        this.onDeleteProductConsumer.accept(this.tourLogItemCellModel);
        tourLogItemCellModel.deleteTourLog(this.tourLogItemCellModel);
    }

    //Button edit
    public void onEditTour(ActionEvent actionEvent) {
        if(this.tourLogItemCellModel.getEditMode() == true){
            this.tourLogItemCellModel.setEditMode(false);
            this.tourLogItemCellModel.setWorkMode(true);
            //this.tourLogCellModel.resetTourModel();
        }else{
            this.tourLogItemCellModel.setEditMode(true);
            this.tourLogItemCellModel.setWorkMode(false);
        }
    }
}
