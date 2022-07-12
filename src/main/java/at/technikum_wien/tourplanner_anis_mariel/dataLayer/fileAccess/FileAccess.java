package at.technikum_wien.tourplanner_anis_mariel.dataLayer.fileAccess;

import at.technikum_wien.tourplanner_anis_mariel.logger.ILoggerWrapper;
import at.technikum_wien.tourplanner_anis_mariel.logger.LoggerFactory;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileAccess implements IFileAccess{
    private ILoggerWrapper logger = LoggerFactory.getLogger();

    private final String defaultStorage;

    public FileAccess(String defaultStorage){
        this.defaultStorage = defaultStorage;
    }

    @Override
    public boolean exportTour(TourModel tourModel, String filePath) {
        String path = Paths.get(filePath).toString();
        try (FileWriter fileWriter = new FileWriter(path);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(tourModel.getName());
            writer.newLine();
            writer.write(tourModel.getFrom());
            writer.newLine();
            writer.write(tourModel.getTo());
            writer.newLine();
            writer.write(tourModel.getDescription());
            writer.newLine();
            writer.write(tourModel.getDetail());
            writer.newLine();
//            for (TourLogItemCellModel tourLogModel : tourLogs){
//                writer.write(tourLogModel.getDate());
//                writer.newLine();
//                writer.write(tourLogModel.getComment());
//                writer.newLine();
//                writer.write(tourLogModel.getDifficulty());
//                writer.newLine();
//                writer.write(tourLogModel.getTotalTime());
//                writer.newLine();
//                writer.write(tourLogModel.getWeather());
//                writer.newLine();
//                writer.write(tourLogModel.getRating());
//                writer.newLine();
//            }
        } catch (IOException e) {
            logger.error("Cant write File: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public File loadFile(String path) {
        return new File(path);
    }

    @Override
    public boolean saveImage(BufferedImage image, int id) {
        try {
            File directory = new File(defaultStorage);
            if (! directory.exists()){
                if (!directory.mkdir()){
                    logger.error("Cant create dir: " + directory);
                    return false;
                }
            }
            ImageIO.write(image, "jpg", new File(defaultStorage + id + ".jpg"));
        } catch (IOException e) {
            logger.error("Exception occured :" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public String getImagePath(int id) {
        String path = defaultStorage + id + ".jpg";
        return path;
    }

    @Override
    public BufferedImage loadImage(int id) {
        BufferedImage bImage;
        try {
            File initialImage = new File(defaultStorage + id + ".jpg");
            //File initialImage = new File(defaultStorage + "gokuu.jpg");
            bImage = ImageIO.read(initialImage);

        } catch (IOException e) {
            logger.error("Exception occured :" + e.getMessage());
            return null;
        }
        return bImage;
    }

    @Override
    public boolean deleteImage(int id) {
        try {
            File file = new File(defaultStorage + id + ".jpg");
            return Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            logger.error("Exception occured :" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean GenerateReport(TourModel tourModel, String path) {
        String tourInfo = getTourInfo(tourModel);
        //StringBuilder logContent = new StringBuilder();

//        for (int i = 0; i < logs.size(); i++){
//            logContent.append("\nLog ").append(i + 1).append(":\n");
//            logContent.append("Date: ").append(logs.get(i).getDate()).append("\n");
//            logContent.append("Comment: ").append(logs.get(i).getComment()).append("\n");
//            logContent.append("Difficulty: ").append(logs.get(i).getDifficulty()).append("\n");
//            logContent.append("Time: ").append(logs.get(i).getTotalTime()).append("\n");
//            logContent.append("Weather: ").append(logs.get(i).getWeather()).append("\n");
//            logContent.append("Rating: ").append(logs.get(i).getRating()).append("\n");
//            }
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            document.add(getTourImage(getImagePath(tourModel.getId())));
            document.add(new Paragraph(tourInfo));
//            document.add(new Paragraph("Logs:"));
//            document.add(new Paragraph(logContent.toString()));
            document.close();
            return true;
        } catch (Exception e) {
            logger.error("Cant create PDF: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private String getTourInfo(TourModel tourModel) {
        String tourInfo = "Tour Info:\n";
        tourInfo += "Name: " + tourModel.getName() + "\n";
        tourInfo += "Start: " + tourModel.getFrom() + "\n";
        tourInfo += "End: " + tourModel.getTo() + "\n";
        tourInfo += "Description: " + tourModel.getDescription() + "\n";
        tourInfo += "Detail: " + tourModel.getDetail() + "\n\n";
        return  tourInfo;
    }

    private Image getTourImage(String imagePath) throws IOException,  BadElementException {
        Image image = Image.getInstance(imagePath);
        image.scaleToFit(400f,400f);
        return image;
    }
}
