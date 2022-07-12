package at.technikum_wien.tourplanner_anis_mariel.presentationLayer;

import at.technikum_wien.tourplanner_anis_mariel.MainViewController;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.*;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourDetailsController;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourDetailsModel;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerFactory {
    private final TourListModel tourListModel;
    private final TourModel tourModel;
    private final TourAddModel tourAddModel;
    private final TourDetailsModel tourDetailsModel;
    private final TourLogModel tourLogModel;


    public ControllerFactory() throws SQLException, IOException {
        this.tourListModel = new TourListModel();
        this.tourModel = new TourModel();
        this.tourAddModel = new TourAddModel(tourModel);
        this.tourDetailsModel = new TourDetailsModel();
        this.tourLogModel = new TourLogModel();
    }

    public Object create(Class controllerClass) throws Exception {
        if (controllerClass == TourAddController.class) {
            return new TourAddController(this.tourAddModel, this.tourModel, this.tourListModel);
        } else if (controllerClass == MainViewController.class) {
            return new MainViewController(this.tourListModel);
        } else if (controllerClass == TourListingController.class) {
            return new TourListingController(this.tourListModel, this.tourDetailsModel, this.tourLogModel);
        } else if (controllerClass == TourDetailsController.class) {
            return new TourDetailsController(this.tourDetailsModel);
        }else if (controllerClass == TourLogController.class){
            return new TourLogController(this.tourLogModel, this.tourModel);
        }else {
            throw new Exception("Controller not supported " + controllerClass.getName());
        }
    }
}
