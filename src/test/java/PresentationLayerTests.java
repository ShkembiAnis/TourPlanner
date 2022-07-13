import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;

public class PresentationLayerTests {
    TourModel tourModel;
    StringProperty name = new SimpleStringProperty("Test");

    @BeforeEach
    void setUp() {
        tourModel = new TourModel(
                1,
                name,
                "Wien",
                "Berlin",
                "",
                ""
        );
    }

//    @Test
//    public void tourInputAccept() {
//        TourDetailsModel tourDetailModel = new TourDetailsModel();
//        assertEquals(tourDetailModel.getTourDesc(), tourDetailModel.setTourDetailDesc("Lorem Ipsum"));
//    }
//
//    @Test
//    public void tourInputEmptyName() {
//        TourDetailModel tourDetailModel = new TourDetailModel();
//        tourDetailModel.Init(tourItem);
//        assertNull(tourDetailModel.validateData());
//        tourDetailModel.name.setValue("");
//        assertNotNull(tourDetailModel.validateData());
//    }
//
//    @Test
//    public void tourInputEmptyStart() {
//        TourDetailModel tourDetailModel = new TourDetailModel();
//        tourDetailModel.Init(tourItem);
//        assertNull(tourDetailModel.validateData());
//        tourDetailModel.start.setValue("");
//        assertNotNull(tourDetailModel.validateData());
//    }
//
//    @Test
//    public void tourInputEmptyEnd() {
//        TourDetailModel tourDetailModel = new TourDetailModel();
//        tourDetailModel.Init(tourItem);
//        assertNull(tourDetailModel.validateData());
//        tourDetailModel.end.setValue("");
//        assertNotNull(tourDetailModel.validateData());
//    }
//
//    @Test
//    public void tourInputWrongStart() {
//        TourDetailModel tourDetailModel = new TourDetailModel();
//        tourDetailModel.Init(tourItem);
//        assertNull(tourDetailModel.validateData());
//        tourDetailModel.start.setValue("XYZ");
//        assertNotNull(tourDetailModel.validateData());
//    }
//
//    @Test
//    public void tourInputWrongEnd() {
//        TourDetailModel tourDetailModel = new TourDetailModel();
//        tourDetailModel.Init(tourItem);
//        assertNull(tourDetailModel.validateData());
//        tourDetailModel.end.setValue("XYZ");
//        assertNotNull(tourDetailModel.validateData());
//    }
}
