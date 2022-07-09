package at.technikum_wien.tourplanner_anis_mariel.dataLayer;

import at.technikum_wien.tourplanner_anis_mariel.businessLayer.ConfigManager;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ITourLogDao;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ManageTourDao;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ITourDao;
import at.technikum_wien.tourplanner_anis_mariel.dataLayer.tourDao.ManageTourLogDao;
import at.technikum_wien.tourplanner_anis_mariel.logger.ILoggerWrapper;
import at.technikum_wien.tourplanner_anis_mariel.logger.LoggerFactory;

import java.io.FileNotFoundException;

public class DataFactory {
    private static IDataLayer data;
    private final static ILoggerWrapper logger = LoggerFactory.getLogger();

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

    public static ITourDao ManageTourDao(){
        try{
            Class<ManageTourDao> createDaoClass = (Class<ManageTourDao>) Class.forName(ManageTourDao.class.getName());
            return createDaoClass.getConstructor().newInstance();
        } catch (Exception e){
            logger.error("Cant create TourDao: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static ITourLogDao ManageTourLogDao() {
        try {
            Class<ManageTourLogDao> cls = (Class<ManageTourLogDao>) Class.forName(ManageTourLogDao.class.getName());
            return cls.getConstructor().newInstance();
        } catch (Exception e) {
            logger.error("Cant create TourLogDAO: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


}
