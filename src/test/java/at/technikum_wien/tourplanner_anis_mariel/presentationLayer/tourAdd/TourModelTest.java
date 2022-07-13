package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TourModelTest {

    TourModel tourModel;
    StringProperty name = new SimpleStringProperty("Test");

    @BeforeEach
    void setUpTourInput() {
        tourModel = new TourModel(
                1,
                name,
                "Wien",
                "Berlin",
                "",
                ""
        );
    }

    @Test
    public void tourInputAccept() {
        Assertions.assertNotEquals(tourModel.getDescription(), "Lorem Ipsum");
        tourModel.setDescription("Lorem Ipsum");
        Assertions.assertEquals(tourModel.getDescription(), "Lorem Ipsum");
    }

}