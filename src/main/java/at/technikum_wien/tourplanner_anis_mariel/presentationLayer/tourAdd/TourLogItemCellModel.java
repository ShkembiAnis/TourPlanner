package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessFactory;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class TourLogItemCellModel {
    @FXML
    private StringProperty date;
    @FXML
    private StringProperty comment;
    @FXML
    private StringProperty difficulty;
    @FXML
    private StringProperty totalTime;
    @FXML
    private StringProperty weather;
    @FXML
    private StringProperty rating;

    public IBusinessLayer businessLayer = BusinessFactory.getBusiness();

    //private String name;
    //private TourModel tourModel;


//    public TourLogItemCellModel (StringProperty date, StringProperty comment,
//                                 StringProperty difficulty, StringProperty totalTime,
//                                 StringProperty weather, StringProperty rating){
//        this.date = date;
//        this.comment = comment;
//        this.difficulty = difficulty;
//        this. totalTime = totalTime;
//        this.weather = weather;
//        this.rating = rating;
//    }

    public TourLogItemCellModel(){
        date = new SimpleStringProperty("dummy");
        comment = new SimpleStringProperty("dummy");
        difficulty = new SimpleStringProperty("dummy");
        totalTime = new SimpleStringProperty("dummy");
        weather = new SimpleStringProperty("dummy");
        rating = new SimpleStringProperty("dummy");
    }

    private Integer logId;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    private Integer tourId;

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId){
        this.tourId = tourId;
    }


    private BooleanProperty editMode = new SimpleBooleanProperty(false);
    private BooleanProperty workingMode = new SimpleBooleanProperty(true);

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getDifficulty() {
        return difficulty.get();
    }

    public StringProperty difficultyProperty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty.set(difficulty);
    }

    public String getTotalTime() {
        return totalTime.get();
    }

    public StringProperty totalTimeProperty() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime.set(totalTime);
    }

    public String getRating() {
        return rating.get();
    }

    public StringProperty ratingProperty() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }

    public String getWeather() {
        return weather.get();
    }

    public StringProperty weatherProperty() {
        return weather;
    }

    public void setWeather(String rating) {
        this.rating.set(rating);
    }
    //Delete Tour Log Item from the Listview
    public void deleteTourLog(TourLogItemCellModel tourLogCellModel) throws SQLException, FileNotFoundException {
        //businessLayer.DeleteTourItem(tourLogCellModel.getId());
    }

    public boolean getEditMode() {
        return editMode.get();
    }

    public void setEditMode(boolean value) {
        editMode.set(value);
    }

    public void setWorkMode(boolean value) {
        workingMode.set(value);
    }

    public BooleanProperty getWorkingModeProperty() {
        return workingMode;
    }

    public BooleanProperty getEditModeProperty() {
        return editMode;
    }

//    public String getName() {
//        return name;
//    }


}
