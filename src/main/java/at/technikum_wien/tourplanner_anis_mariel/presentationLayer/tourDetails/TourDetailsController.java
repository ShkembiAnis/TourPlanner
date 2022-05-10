package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourDetails;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TourDetailsController {

    public TourDetailsModel tourDetailsModel;

    public TourDetailsController (TourDetailsModel tourDetailsModel){
        this.tourDetailsModel = tourDetailsModel;
    }
}
