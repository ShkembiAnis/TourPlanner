package at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourLogModel {

    private StringProperty tourLog;
    private ObservableList<TourLogItemCellModel> tourLogs = FXCollections.observableArrayList();
    private TourModel tourModel = new TourModel();

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

    public TourLogModel(){
        tourLog = new SimpleStringProperty("Log view");
    }

    public StringProperty getTourLogProperty() {
        return tourLog;
    }

    public ObservableList<TourLogItemCellModel> getTourLogs() {
        return tourLogs;
    }

    public String getTourModelName(){
        return this.tourModel.getName();
    }

    private void clearLogs(){
        tourLogs.clear();
    }

    public ObservableList<TourLogItemCellModel> getTours() {
        return tourLogs;
    }

    public void removeTour(TourLogItemCellModel product) {
        this.tourLogs.remove(product);
    }

    public void setTourModel(TourModel tourModelList) {
        //clear listview
        clearLogs();
        //save tourLogs from Listview to LogListView
        setLogModel(tourModelList);
    }

    //set ListView to LogListView
    private void setLogModel(TourModel tourModel) {
        setTourId(tourModel.getId());
    }

    //save ListView to be the Same as the modified LogListView
    public void saveTourModel() {
        if(this.tourModel != null){
            this.tourModel.setTourLogs(tourModel.getTours());
        }
    }
}
