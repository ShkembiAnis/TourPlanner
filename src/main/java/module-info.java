module at.technikum_wien.tourplanner_anis_mariel {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens at.technikum_wien.tourplanner_anis_mariel to javafx.fxml;
    exports at.technikum_wien.tourplanner_anis_mariel;
    exports at.technikum_wien.tourplanner_anis_mariel.tourAdd;
    opens at.technikum_wien.tourplanner_anis_mariel.tourAdd to javafx.fxml;
}