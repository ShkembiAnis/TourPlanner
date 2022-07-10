module at.technikum_wien.tourplanner_anis_mariel {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.apache.logging.log4j;
    requires java.desktop;
    requires org.json;
    requires itextpdf;
    requires javafx.swing;

    opens at.technikum_wien.tourplanner_anis_mariel to javafx.fxml;
    exports at.technikum_wien.tourplanner_anis_mariel;
    exports at.technikum_wien.tourplanner_anis_mariel.presentationLayer;
    opens at.technikum_wien.tourplanner_anis_mariel.presentationLayer to javafx.fxml;
    exports at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;
    opens at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd to javafx.fxml;
}