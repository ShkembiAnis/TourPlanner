package at.technikum_wien.tourplanner_anis_mariel;

import at.technikum_wien.tourplanner_anis_mariel.logger.ILoggerWrapper;
import at.technikum_wien.tourplanner_anis_mariel.logger.LoggerFactory;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourItemController;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.function.Consumer;

public class TourItemModel extends javafx.scene.control.ListCell<TourModel> {

    private Consumer<TourModel> onDeleteTourCallBack;
    private final ILoggerWrapper logger = LoggerFactory.getLogger();


    public TourItemModel(Consumer<TourModel> callback) {
        this.onDeleteTourCallBack = callback;
    }

    @Override
    public void updateItem(TourModel tour, boolean empty)
    {
        super.updateItem(tour, empty);
        if (empty || tour == null) {
            setText(null);
            setGraphic(null);
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tourItem.fxml"));
        try
        {
            logger.debug("Item is added to the ListView of Tour");
            fxmlLoader.load();
        }
        catch (IOException e) {
            logger.error("Could not create Item tourLogItem.fxml");
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

        var controller = (TourItemController)fxmlLoader.getController();
        controller.setTour(tour);
        controller.addListenerForDeleteTour(this.onDeleteTourCallBack);
        setGraphic(controller.getTourItemBox());
    }

}
