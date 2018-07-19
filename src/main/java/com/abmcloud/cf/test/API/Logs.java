package com.abmcloud.cf.test.API;

import java.io.IOException;
import java.util.logging.*;

public class Logs {

    private Logger logger;

    public Logs(String logName) {
        logger = Logger.getLogger(logName);
    }

    public void setFileHandler(FileHandler fileHandler) {
        logger.addHandler(fileHandler);
    }

    public static FileHandler getFileHandler(String fileName) {
        Handler fileHandler = null;
        try {
            fileHandler = new FileHandler(fileName + ".log");
            //fileHandler.setFormatter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileHandler.setFormatter(new LoggerFormat());
        return (FileHandler) fileHandler;
    }

    public void setFileName(String fileName) {
        try {
            Handler fileHandler = new FileHandler(fileName + ".log");
            fileHandler.setFormatter(new LoggerFormat());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void infoMsg(String msg) {
        logger.log(Level.INFO, msg);
    }

    static class LoggerFormat extends Formatter {

        @Override
        public String format(LogRecord record) {
            return record.getLevel() + " :" + record.getMessage() + "\n";
        }
    }
}
