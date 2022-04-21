package at.technikum_wien.tourplanner_anis_mariel.tourAdd;

public class ControllerFactory {
    private final TourListModel tourListModel;
    private final TourModel tourModel;
    private final TourAddModel tourAddModel;

    public ControllerFactory() {
        this.tourListModel = new TourListModel();
        this.tourModel = new TourModel();
        this.tourAddModel = new TourAddModel();
    }

    public Object create(Class controllerClass) throws Exception {
        if (controllerClass == TourAddController.class) {
            return new TourAddController(this.tourAddModel);
        } else if (controllerClass == TourListController.class) {
            return new TourListController(this.tourListModel);
        } else if (controllerClass == TourListingController.class) {
            return new TourListingController(this.tourListModel);
        }else {
            throw new Exception("Controller not supported " + controllerClass.getName());
        }
    }
}
