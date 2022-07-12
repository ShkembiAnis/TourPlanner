import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;
import org.junit.jupiter.api.BeforeEach;

public class PresentationLayerTests {
    TourModel tourModel;

    @BeforeEach
    void setUp() {
        tourModel = new TourModel(
                1,
                "Test",
                "Wien",
                "Berlin",
                "",
                ""
        );
    }
}
