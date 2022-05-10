package at.technikum_wien.tourplanner_anis_mariel.presentationLayer;

import at.technikum_wien.tourplanner_anis_mariel.MainViewController;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.*;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourDetails.TourDetailsController;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourDetails.TourDetailsModel;

public class ControllerFactory {
    private final TourListModel tourListModel;
    private final TourModel tourModel;
    private final TourAddModel tourAddModel;
    private final TourDetailsModel tourDetailsModel ;


    public ControllerFactory() {
        this.tourListModel = new TourListModel();
        this.tourModel = new TourModel();
        this.tourAddModel = new TourAddModel();
        this.tourDetailsModel = new TourDetailsModel();
    }

    public Object create(Class controllerClass) throws Exception {
        if (controllerClass == TourAddController.class) {
            return new TourAddController(this.tourAddModel);
        } else if (controllerClass == MainViewController.class) {
            return new MainViewController(this.tourListModel);
        } else if (controllerClass == TourListingController.class) {
            return new TourListingController(this.tourListModel);
        } else if (controllerClass == TourDetailsController.class) {
            return new TourDetailsController(this.tourDetailsModel);
        }else {
            throw new Exception("Controller not supported " + controllerClass.getName());
        }
    }
}
