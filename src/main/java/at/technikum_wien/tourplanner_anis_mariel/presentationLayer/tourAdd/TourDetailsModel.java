package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;
import javafx.beans.property.*;
import javafx.scene.image.Image;

public class TourDetailsModel {

    private StringProperty tourName;
    private StringProperty tourFrom;
    private StringProperty tourTo;
    private StringProperty tourDesc;
    private StringProperty tourDistance;

    private StringProperty tourNameLabel;
    private StringProperty editButton;
    private BooleanProperty editMode;
    private BooleanProperty workingMode;
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

    private TourModel tourModel;

    // can use lombok for getter and setter for all UI classes

    public TourDetailsModel(){
        tourName = new SimpleStringProperty("");
        tourDesc = new SimpleStringProperty("");
        tourFrom = new SimpleStringProperty("");
        tourTo = new SimpleStringProperty("");
        tourDistance = new SimpleStringProperty("");

        tourNameLabel = new SimpleStringProperty("Dummy");

        editButton = new SimpleStringProperty("Edit");
        editMode = new SimpleBooleanProperty(false);
        workingMode = new SimpleBooleanProperty(true);
    }

    public String getTourDistance() {
        return tourDistance.get();
    }

    public String getTourName(){
        return  tourName.get();
    }

    public StringProperty getTourNameProperty() {
        return tourName;
    }

    public String getTourDesc() {
        return tourDesc.get();
    }

    public StringProperty getTourDescProperty() {
        return tourDesc;
    }

    public String getTourFrom() {
        return tourFrom.get();
    }

    public StringProperty getTourFromProperty() {
        return tourFrom;
    }

    public void setTourDistance(String tourDistance) {
        this.tourDistance.set(tourDistance);
    }

    public String getTourTo() {
        return tourTo.get();
    }

    public StringProperty getTourToProperty() {
        return tourTo;
    }

    public boolean getEditMode() {
        return editMode.get();
    }

    public BooleanProperty getEditModeProperty() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode.set(editMode);
    }

    public BooleanProperty getWorkingModeProperty() {
        return workingMode;
    }

    public void setWorkMode(boolean workMode) {
        this.workingMode.set(workMode);
    }

    public StringProperty getEditButtonProperty() {
        return editButton;
    }

    public void setEditButton(String editButton) {
        this.editButton.set(editButton);
    }

    public void setTourDetailName(String tourName) {
        this.tourName.set(tourName);
    }

    public void setTourDetailDesc(String tourDesc) {
        this.tourDesc.set(tourDesc);
    }


    private void setTourDetailDistance(String tourDistance) {
        this.tourDistance.set(tourDistance);
    }

    public void setTourDetailTo(String tourTo) {
        this.tourTo.set(tourTo);
    }

    public void setTourDetailFrom(String tourFrom) {
        this.tourFrom.set(tourFrom);
    }

    public Property<Image> getImageProperty() {
        return imageProperty;
    }

    public void setTourDetailImg(Image image) {
        this.imageProperty.set(image);
    }

    public StringProperty getTourDistanceProperty() {
        return tourDistance;
    }

    //prej majtas vlerat ruhet djathtas
    public void setTourModel(TourModel tourModelList) {
        setTourDetailName(tourModelList.getName());
        this.tourModel = tourModelList;
    }

    //marrim emrin prej descriptionit
    public TourModel getTourModel(){
        return tourModel;
    }
}