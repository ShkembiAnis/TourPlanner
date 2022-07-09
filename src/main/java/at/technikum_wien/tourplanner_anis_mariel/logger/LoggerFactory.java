package at.technikum_wien.tourplanner_anis_mariel.logger;

public class LoggerFactory {
    public static ILoggerWrapper getLogger() {
        var logger = new Log4j2Wrapper();
        logger.initialize();
        return logger;
    }
}
