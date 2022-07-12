package at.technikum_wien.tourplanner_anis_mariel.dataLayer;

import at.technikum_wien.tourplanner_anis_mariel.businessLayer.ConfigManager;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ITourDao;
import at.technikum_wien.tourplanner_anis_mariel.logger.ILoggerWrapper;
import at.technikum_wien.tourplanner_anis_mariel.logger.LoggerFactory;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogItemCellModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourLogModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class DatabaseConnection implements IDataLayer{

    private final String databaseConnString;
    private final ILoggerWrapper logger = LoggerFactory.getLogger();

    public DatabaseConnection(String databaseConnString) {
        this.databaseConnString = databaseConnString;
    }

    public Connection CreateConnection() throws SQLException, FileNotFoundException {
        String name = ConfigManager.GetConfigProperty("name");
        String pass = ConfigManager.GetConfigProperty("pass");
        try {
            return DriverManager.getConnection(databaseConnString, name, pass);
        } catch (SQLException e) {
            logger.error("Establishing connection failed: " + e.getMessage());
            e.printStackTrace();
        }
        throw new SQLException("Establishing connection failed.");
    }

    public int executeStatement(String query, ArrayList<Object> parameters) throws SQLException {
        try(Connection conn = CreateConnection();
            PreparedStatement pre = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < parameters.size(); i++) {
                pre.setString(i+1,parameters.get(i).toString());
            }
            int affectedRows = pre.executeUpdate();

            if (affectedRows > 0){
                try (ResultSet generatedKeys = pre.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException | FileNotFoundException e){
            logger.error("SQL Error: " + query + " - " + e.getMessage());
            e.printStackTrace();
        }
        throw new SQLException("SQL Error " + query);
    }

    @Override
    public int insertTour(String query, ArrayList<Object> parameters) throws SQLException {
        return executeStatement(query, parameters);
    }

    @Override
    public int updateTour(String query, ArrayList<Object> parameters) throws SQLException {
        return executeStatement(query, parameters);
    }

    @Override
    public int deleteTour(String query, ArrayList<Object> parameters) throws SQLException {
        return executeStatement(query, parameters);
    }

    @Override
    public Integer getMaxId(String query) throws SQLException, FileNotFoundException {
        //Get last inserted Tours id

        try(Connection conn = CreateConnection();
            Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            if(result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
        logger.error("Could not get id from last Tour inserted!");
        logger.error(e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public <T> List<T> TourReader(String query, Class<T> tourType) throws SQLException{
        try(Connection conn = CreateConnection();
            Statement statement = conn.createStatement()) {

            ResultSet result = statement.executeQuery(query);
            if(tourType.getTypeName().equals(TourModel.class.getName())) {
                return (List<T>) QueryTourItemDataFromResultSet(result);
            }
            if(tourType.getTypeName().equals(TourLogModel.class.getName())) {
                return (List<T>) QueryDataLogDataFromResultSet(result);
            }
        } catch (SQLException | ParseException | IOException e){
            logger.error("Reading data failed: " + query + " - " + e.getMessage());
            e.printStackTrace();
        }
        throw new SQLException("Reading data failed. " + query);
    }


    @Override
    public <T> List<T> TourReader(String query, ArrayList<Object> parameters, Class<T> tourType) throws SQLException, IOException{
        try(Connection conn = CreateConnection();
            PreparedStatement pre = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < parameters.size(); i++) {
                pre.setString(i+1,parameters.get(i).toString());
            }
            ResultSet result = pre.executeQuery();
            if(tourType.getTypeName().equals(TourModel.class.getName())) {
                return (List<T>) QueryTourItemDataFromResultSet(result);
            }
            if(tourType.getTypeName().equals(TourLogItemCellModel.class.getName())) {
                return (List<T>) QueryDataLogDataFromResultSet(result);
            }
        } catch (SQLException | ParseException | FileNotFoundException e){
            logger.error("Creating data failed: " + query + " - " + e.getMessage());
            e.printStackTrace();
        }
        throw new SQLException("Creating data failed: " + query);
    }

    private Object QueryTourItemDataFromResultSet(ResultSet result) throws SQLException {
        List<TourModel> tourItemList = new ArrayList<>();

        while (result.next()) {
            TourModel temp = new TourModel();
            temp.setId(result.getInt("tourid_pk"));
            temp.setName(result.getString("tourname"));
            temp.setFrom(result.getString("from"));
            temp.setTo(result.getString("to"));
            temp.setDescription(result.getString("description"));
            temp.setDetail(result.getString("details"));

            tourItemList.add(temp);

            //tourItemList.add(new TourModel(
              //      result.getInt("tourid_pk"),
                //    result.getString("tourname")
//                    result.getString("Start"),
//                    result.getString("End"),
//                    result.getString("Description"),
//                    result.getFloat("Distance")
           // ));
        }
        return tourItemList;
    }

    private List<TourLogItemCellModel> QueryDataLogDataFromResultSet(ResultSet result) throws SQLException, ParseException, IOException {
        List<TourLogItemCellModel> tourItemList = new ArrayList<>();
        ITourDao tourItemDAO = DataFactory.ManageTourDao();

        while (result.next()) {
            assert tourItemDAO != null;
            TourLogItemCellModel temp = new TourLogItemCellModel();
            temp.setLogId(result.getInt("logId"));
            temp.setTourId(result.getInt("tourId"));
            temp.setDate(result.getString("date"));
            temp.setComment(result.getString("comment"));
            temp.setDifficulty(result.getString("difficulty"));
            temp.setTotalTime(result.getString("time"));
            temp.setWeather(result.getString("weather"));
            temp.setRating(result.getString("rating"));

            tourItemList.add(temp);
        }
        return tourItemList;
    }
}
