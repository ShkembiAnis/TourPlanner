package at.technikum_wien.tourplanner_anis_mariel.tourAdd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;

import java.util.function.Consumer;

public class TourItemController {

    private TourModel tour;

    private Consumer<TourModel> onDeleteTourConsumer;

    @FXML
    public Label name;

    @FXML
    private Node box = new HBox();

    public TourItemController() { }

    public Node getProductItemBox() {
        return box;
    }

    public void setTour(TourModel tour) {
        this.tour = tour;
        this.name.textProperty().bindBidirectional(this.tour.nameProperty());
    }

    public void onDeleteTour(ActionEvent actionEvent) {
        this.onDeleteTourConsumer.accept(this.tour);
    }

    public void addListenerForDeleteProduct(Consumer<TourModel> listener) {
        this.onDeleteTourConsumer = listener;
    }


}
