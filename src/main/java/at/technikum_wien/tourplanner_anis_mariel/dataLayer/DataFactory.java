package at.technikum_wien.tourplanner_anis_mariel.dataLayer;

import at.technikum_wien.tourplanner_anis_mariel.businessLayer.BusinessImplementation;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.ConfigManager;
import at.technikum_wien.tourplanner_anis_mariel.businessLayer.IBusinessLayer;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ITourDao;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class DataFactory {
    private static IDataLayer data;

    public static IDataLayer getDatabase() throws FileNotFoundException {
        if (data == null) {
            data = CreateDatabase();
        }
        return data;
    }

    private static IDataLayer CreateDatabase() throws FileNotFoundException {
        String databaseConnUrl = ConfigManager.GetConfigProperty("databaseConnUrl");
        return CreateDatabase(databaseConnUrl);
    }

    private static IDataLayer CreateDatabase(String databaseConnUrl) {
        try{
            Class<DatabaseConnection> dbConnClass = (Class<DatabaseConnection>) Class.forName(DatabaseConnection.class.getName());
            return dbConnClass.getConstructor(String.class).newInstance(databaseConnUrl);
        } catch (Exception e){
//            Logger log = LogManager.getLogger(DALFactory.class);
//            log.error("Cant create Database: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static ITourDao CreateTourDao(){
        try{
            Class<CreateTourDao> createDaoClass = (Class<CreateTourDao>) Class.forName(CreateTourDao.class.getName());
            return createDaoClass.getConstructor().newInstance();
        } catch (Exception e){
            //            Logger log = LogManager.getLogger(DALFactory.class);
//            log.error("Cant create TourDao: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


}
