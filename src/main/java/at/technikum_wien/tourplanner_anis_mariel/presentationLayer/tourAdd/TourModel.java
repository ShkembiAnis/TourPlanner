package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TourModel {

    private Integer id;
    private StringProperty name = new SimpleStringProperty();
    private String from;
    private String to;
    private String description;
    private String detail;


    public TourModel() {
    }

    public TourModel(Integer Id, StringProperty name, String from, String to, String description, String detail) {
        this.id = id;
        this.name = name;
        this.from = from;
        this.to = to;
        this.description = description;
        this.detail = detail;
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

}
