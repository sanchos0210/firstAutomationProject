package com.abmcloud.cf.test.API;

import java.io.IOException;
import java.util.logging.*;

public class Logs {

    private Logger logger;
    private static String fileName;

    public static void setFileName(String name) {
        fileName = name + ".log";
    }

    public Logs(String logName) {
        logger = Logger.getLogger(logName);
        logger.addHandler(getFileHandler());
    }

    private FileHandler getFileHandler() {
        Handler fileHandler = null;
        try {
            fileHandler = new FileHandler(fileName + ".log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileHandler.setFormatter(new LoggerFormat());
        return (FileHandler) fileHandler;
    }

    public void infoMsg(String msg) {
        logger.log(Level.INFO, msg);
    }

    public void errorMsg(String msg) {
        logger.log(Level.SEVERE, msg);
    }

    static class LoggerFormat extends Formatter {

        @Override
        public String format(LogRecord record) {
            return record.getLoggerName() + ". " + record.getLevel() + " :" + record.getMessage() + "\n";
        }
    }
}
