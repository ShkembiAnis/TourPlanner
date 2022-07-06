package at.technikum_wien.tourplanner_anis_mariel.dataLayer;

import at.technikum_wien.tourplanner_anis_mariel.businessLayer.ConfigManager;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection implements IDataLayer{

    private final String databaseConnString;

    public DatabaseConnection(String databaseConnString) {
        this.databaseConnString = databaseConnString;
    }

    public Connection CreateConnection() throws SQLException, FileNotFoundException {
        String name = ConfigManager.GetConfigProperty("name");
        String pass = ConfigManager.GetConfigProperty("pass");
        try {
            return DriverManager.getConnection(databaseConnString, name, pass);
        } catch (SQLException e) {
//            Logger log = LogManager.getLogger(Database.class);
//            log.error("Establishing connection failed: " + e.getMessage());
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        throw new SQLException("Establishing connection failed.");
    }
//
//
//    private int executeStatement(String query, ArrayList<Object> parameters) throws SQLException {
//        try {
//            Connection connection = DatabaseConnection();
//            PreparedStatement preSt = connection.prepareStatement(query);
//
//            for (int i = 0; i < parameters.size(); i++) {
//                preSt.setString(i+1,parameters.get(i).toString());
//            }
//
//        } catch (SQLException | FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        throw new SQLException("SQL Error: " + query);
//    }
//
//    @Override
//    public int insertTour(String query, ArrayList<Object> parameters) throws SQLException {
//        return executeStatement(query, parameters);
//    }
//
//    @Override
//    public int updateTour(String query, ArrayList<Object> parameters) throws SQLException {
//        return executeStatement(query, parameters);
//    }
//
//    @Override
//    public int deleteTour(String query, ArrayList<Object> parameters) throws SQLException {
//        return executeStatement(query, parameters);
//    }
}
