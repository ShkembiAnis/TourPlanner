package at.technikum_wien.tourplanner_anis_mariel.tourAdd;

import at.technikum_wien.tourplanner_anis_mariel.MainViewController;

public class ControllerFactory {
    private final TourListModel tourListModel;
    private final TourModel tourModel;
    private final TourAddModel tourAddModel;
    //private final TourItemModel tourItemModel;

    public ControllerFactory() {
        this.tourListModel = new TourListModel();
        this.tourModel = new TourModel();
        this.tourAddModel = new TourAddModel();
        //this.tourItemModel = new TourItemModel();
    }

    public Object create(Class controllerClass) throws Exception {
        if (controllerClass == TourAddController.class) {
            return new TourAddController(this.tourAddModel);
        } else if (controllerClass == MainViewController.class) {
            return new MainViewController(this.tourListModel);
        } else if (controllerClass == TourListingController.class) {
            return new TourListingController(this.tourListModel);
//        } else if (controllerClass == TourItemController.class) {
//            return new TourItemController(this.tourItemModel);
        }else {
            throw new Exception("Controller not supported " + controllerClass.getName());
        }
    }
}
