package at.technikum_wien.tourplanner_anis_mariel.businessLayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    public static String GetConfigProperty(String propertyName) throws FileNotFoundException {
        //Logger log = LogManager.getLogger(ConfigurationManager.class);
        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream stream = new FileInputStream(propFileName);

        try {
            prop.load(stream);
            return prop.getProperty(propertyName);
        } catch (Exception e) {
            //log.error(propertyName + " was not found. " + e.getMessage());
            e.printStackTrace();
        }
        throw new FileNotFoundException(propFileName + " was not found.");
    }
}
