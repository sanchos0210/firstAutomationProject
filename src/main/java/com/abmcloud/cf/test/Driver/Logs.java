package com.abmcloud.cf.test.Driver;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class Logs {

    private Logger logger;
    private static String fileName;
    private static Handler fileHandler;
    private File logsFolder;

    public void setFileName(String name) {
        fileName = "logs\\" + name + ".log";
        try {
            fileHandler = new FileHandler(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileHandler.setFormatter(new LoggerFormat());
    }

    public Logs(String testName) {
        logsFolder = new File("logs");
        if (!logsFolder.exists()) {
            logsFolder.mkdir();
        }
        setFileName(testName);
        logger = Logger.getLogger(testName);
        logger.addHandler(fileHandler);
    }

    public void infoMsg(String msg) {
        logger.log(Level.INFO, msg);
    }

    public void warning(String msg) {
        logger.warning(msg);
    }

    public void errorMsg(RuntimeException ex) {
        logger.log(Level.SEVERE, ex.toString());
        StackTraceElement[] stackTraceElements = ex.getStackTrace();
        for(StackTraceElement s: stackTraceElements) {
            logger.severe(s.toString());
        }
    }

    static class LoggerFormat extends Formatter {

        @Override
        public String format(LogRecord record) {
            return record.getLevel() + ": " + record.getMessage() + "\n";
        }
    }
}
