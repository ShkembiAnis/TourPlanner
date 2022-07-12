package at.technikum_wien.tourplanner_anis_mariel.dataLayer.fileAccess;

import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.awt.image.BufferedImage;
import java.io.File;

public interface IFileAccess {
    boolean exportTour(TourModel tourModel, String path);
    File loadFile(String path);
    boolean saveImage(BufferedImage image, int id);
    String getImagePath(int id);
    BufferedImage loadImage(int id);
    boolean deleteImage(int id);
    boolean GenerateReport(TourModel tourModel, String path);
}
