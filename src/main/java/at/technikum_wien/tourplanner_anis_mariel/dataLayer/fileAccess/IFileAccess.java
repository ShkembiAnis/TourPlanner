package at.technikum_wien.tourplanner_anis_mariel.dataLayer.fileAccess;

import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogItemCellModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public interface IFileAccess {
    boolean exportTour(TourModel tourModel, List<TourLogItemCellModel> tourLogs, String path);
    File loadFile(String path);
    boolean saveImage(BufferedImage image, int id);
    String getImagePath(int id);
    BufferedImage loadImage(int id);
    boolean deleteImage(int id);
    boolean GenerateReport(TourModel tourModel, String path);
}