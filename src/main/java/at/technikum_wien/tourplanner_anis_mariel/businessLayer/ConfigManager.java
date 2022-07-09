package at.technikum_wien.tourplanner_anis_mariel.businessLayer;

import at.technikum_wien.tourplanner_anis_mariel.logger.ILoggerWrapper;
import at.technikum_wien.tourplanner_anis_mariel.logger.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private final static ILoggerWrapper logger = LoggerFactory.getLogger();

    public static String GetConfigProperty(String propertyName) throws FileNotFoundException {
        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream stream = new FileInputStream(propFileName);

        try {
            prop.load(stream);
            return prop.getProperty(propertyName);
        } catch (Exception e) {
            logger.error(propertyName + " was not found. " + e.getMessage());
            e.printStackTrace();
        }
        throw new FileNotFoundException(propFileName + " was not found.");
    }
}
