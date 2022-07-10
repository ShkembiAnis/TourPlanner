package at.technikum_wien.tourplanner_anis_mariel;

import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessFactory;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import at.technikum_wien.tourplanner_anis_mariel.logger.ILoggerWrapper;
import at.technikum_wien.tourplanner_anis_mariel.logger.LoggerFactory;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogItemCellModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogItemContoller;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.function.Consumer;

public class TourLogItemModel extends javafx.scene.control.ListCell<TourLogItemCellModel>{

    private Consumer<TourLogItemCellModel> onDeleteProductCallBack;
    private final ILoggerWrapper logger = LoggerFactory.getLogger();

    public TourLogItemModel(Consumer<TourLogItemCellModel> callback) {
        this.onDeleteProductCallBack = callback;
    }

    //Load TourLogItem.fxml to the ListView
    @Override
    public void updateItem(TourLogItemCellModel product, boolean empty)
    {
        super.updateItem(product, empty);
        if (empty || product == null) {
            setText(null);
            setGraphic(null);
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tourLogItem.fxml"));
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

        var controller = (TourLogItemContoller)fxmlLoader.getController();
        controller.setProduct(product);
        controller.addListenerForDeleteTour(this.onDeleteProductCallBack);
        setGraphic(controller.getProductItemBox());
    }

}
