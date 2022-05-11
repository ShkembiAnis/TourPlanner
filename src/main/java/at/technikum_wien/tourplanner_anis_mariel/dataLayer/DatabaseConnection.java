package at.technikum_wien.tourplanner_anis_mariel.dataLayer;

import at.technikum_wien.tourplanner_anis_mariel.presentationLayer.tourAdd.TourModel;
import javafx.scene.chart.PieChart;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseConnection implements IDataLayer{

    public Connection DatabaseConnection() throws SQLException, FileNotFoundException {
        String url, name, pass;
        url = "jdbc:postgresql://localhost:5432/tourPlannerDB";
        name = "postgres";
        pass = "hazard<3";
        try {
            return DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        throw new SQLException("Establishing connection failed.");
    } //config file


    private int executeStatement(String query, TourModel tourModel) throws SQLException {
        try {
            Connection connection = DatabaseConnection();
            PreparedStatement preSt = connection.prepareStatement(query);

            preSt.setString(1, tourModel.getName());

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new SQLException("SQL Error: " + query);
    }

    @Override
    public int insertTour(String query, TourModel tourModel) throws SQLException {
        return executeStatement(query, tourModel);
    }

    @Override
    public int updateTour(TourModel tourModel) throws SQLException {
        return 0;
    }

    @Override
    public int deleteTour(TourModel tourModel) throws SQLException {
        return 0;
    }
}
