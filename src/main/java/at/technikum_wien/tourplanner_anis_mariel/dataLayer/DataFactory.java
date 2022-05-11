package at.technikum_wien.tourplanner_anis_mariel.dataLayer;

import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessImplementation;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;

public class DataFactory {
    private static IDataLayer data;

    public static IDataLayer getDatabase() {
        if (data == null) {
            data = new DatabaseConnection();
        }
        return data;
    }

}
