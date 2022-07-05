package at.technikum_wien.tourplanner_anis_mariel.dataLayer;

import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourAddModel;
import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseConnection implements IDataLayer{

//    public Connection DatabaseConnection() throws SQLException, FileNotFoundException {
//        String url, name, pass;
//        url = "jdbc:postgresql://localhost:5432/tourPlannerDB";
//        name = "postgres";
//        pass = "hazard<3";
//        try {
//            return DriverManager.getConnection(url, name, pass);
//        } catch (SQLException e) {
//            System.out.println("Connection failure.");
//            e.printStackTrace();
//        }
//        throw new SQLException("Establishing connection failed.");
//    } //config file
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
