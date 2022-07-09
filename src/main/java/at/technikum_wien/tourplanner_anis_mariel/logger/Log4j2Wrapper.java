package at.technikum_wien.tourplanner_anis_mariel.logger;

import org.apache.logging.log4j.LogManager;

import java.util.logging.Logger;

public class Log4j2Wrapper implements ILoggerWrapper{
    //private Logger logger;
    private LoggerStateBase state = new UninitializedState();

    @Override
    public void debug(String message) {
        this.state.debug(message);
    }
    @Override
    public void fatal(String message) {
        this.state.fatal(message);
    }
    @Override
    public void error(String message) {
        this.state.error(message);
    }
    @Override
    public void warn(String message) {
        this.state.warn(message);
    }

    public void initialize() {
        this.state = new InitialzedState(LogManager.getLogger(this.getClass().getName()));
    }
}
