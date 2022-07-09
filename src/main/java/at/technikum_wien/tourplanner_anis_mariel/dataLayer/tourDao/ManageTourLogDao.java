package at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao;

import at.technikum_wien.tourplanner_anis_mariel.dataLayer.IDataLayer;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogItemCellModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ManageTourLogDao implements ITourLogDao{
    private final IDataLayer dataLayer;

    public ManageTourLogDao(IDataLayer dataLayer) throws FileNotFoundException{
        this.dataLayer = dataLayer;
    }

    @Override
    public TourLogModel FindById(Integer logId) throws SQLException, IOException, ParseException {
        return null;
    }

    @Override
    public TourLogModel AddNewItemLog(TourLogItemCellModel tourLogItemCellModel) throws SQLException, IOException, ParseException {
        return null;
    }

    @Override
    public boolean UpdateLog(TourLogItemCellModel tourLogItemCellModel) throws SQLException {
        return false;
    }

    @Override
    public List<TourLogModel> GetLogsForItem(TourModel tourModel) throws SQLException, IOException, ParseException {
        return null;
    }

    @Override
    public boolean DeleteLog(Integer id) throws SQLException {
        return false;
    }

    private ArrayList<Object> createTourLogParam(TourLogItemCellModel tourLogItemCellModel) {
        ArrayList<Object> parameters = new ArrayList<>();

        parameters.add(tourLogItemCellModel.getTourId());
        parameters.add(tourLogItemCellModel.getDate());
        parameters.add(tourLogItemCellModel.getComment());
        parameters.add(tourLogItemCellModel.getDifficulty());
        parameters.add(tourLogItemCellModel.getTotalTime());
        parameters.add(tourLogItemCellModel.getWeather());
        parameters.add(tourLogItemCellModel.getRating());

        return parameters;
    }
}
