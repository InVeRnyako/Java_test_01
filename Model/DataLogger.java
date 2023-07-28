package Model;

import Presenter.Presenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DataLogger {
    private final String LOG_FILE_NAME = "name.log";
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss  ");
    private Date date = new Date();
    private final Logger logger = Logger.getLogger(DataLogger.class.getName());
    private Presenter presenter;

    public DataLogger(Presenter presenter) {
        this.presenter = presenter;
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_NAME, true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.setLevel(Level.INFO);
            logger.addHandler(fileHandler);
        } catch (Exception ex){
             presenter.sentMsg("Ошибка создания фала Логгирования: "+ ex.getMessage());
        }
    }

    public void saveData(String data) {
        logger.info(formatter.format(date) + data);
    }

    public void saveData(ArrayList<String> dataString){
        for (String line: dataString) {
            saveData(line);
        }
    }
}

