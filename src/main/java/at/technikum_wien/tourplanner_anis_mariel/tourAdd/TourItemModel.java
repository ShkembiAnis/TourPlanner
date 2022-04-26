package at.technikum_wien.tourplanner_anis_mariel.tourAdd;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.function.Consumer;

public class TourItemModel extends javafx.scene.control.ListCell<TourModel> {

    private Consumer<TourModel> onDeleteTourCallBack;

    TourItemModel(Consumer<TourModel> callback) {
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
            fxmlLoader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        var controller = (TourItemController)fxmlLoader.getController();
        controller.setTour(tour);
        controller.addListenerForDeleteProduct(this.onDeleteTourCallBack);
        setGraphic(controller.getProductItemBox());
    }

}
