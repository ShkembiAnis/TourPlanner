package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.Iterator;

public class TourModel {

    private Integer id;
    private StringProperty name = new SimpleStringProperty();
    private String from;
    private String to;
    private String description;
    private String detail;
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

    private ObservableList<TourLogItemCellModel> tourLogs = FXCollections.observableArrayList();


    public TourModel() {
    }

    public TourModel(Integer Id, StringProperty name, String from, String to, String description, String detail, ObjectProperty<Image> image) {
        this.id = id;
        this.name = name;
        this.from = from;
        this.to = to;
        this.description = description;
        this.detail = detail;
        this.imageProperty = image;
    }

    public static TourModel From(TourAddModel source) {
        var newInstance = new TourModel();
        newInstance.name.set(source.getName());
        return newInstance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ObjectProperty<Image> getImageProperty() {
        return imageProperty;
    }

    public void setImage(Image image) {
        this.imageProperty.set(image);
    }

    public ObservableList<TourLogItemCellModel> getTours() {
        return tourLogs;
    }

    protected void clearLogs(){
        this.tourLogs.clear();
    }

    public void setTourLogs(ObservableList<TourLogItemCellModel> tourLogs){
        clearLogs();
        Iterator<TourLogItemCellModel> it = tourLogs.iterator();
        while (it.hasNext()) {
            TourLogItemCellModel value = it.next();
            this.tourLogs.add(value);
        }
    }

}
