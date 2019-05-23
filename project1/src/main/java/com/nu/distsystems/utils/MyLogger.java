package com.nu.distsystems.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Formatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
  private static FileHandler txtHandler;
  private static SimpleFormatter txtFormatter;

  private static FileHandler htmlHandler;
  private static HTMLFormatter htmlFormatter;

  public static void setUp(String logFileName) throws IOException {
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // suppress the logging output to the console
    Logger rootLogger = Logger.getLogger("");
    Handler[] handlers = rootLogger.getHandlers();
    if (handlers[0] instanceof ConsoleHandler) {
      rootLogger.removeHandler(handlers[0]);
    }

    logger.setLevel(Level.INFO);
    txtHandler = new FileHandler(logFileName + ".txt");
    htmlHandler = new FileHandler(logFileName + ".html");

    // create a TXT formatter
    txtFormatter = new SimpleFormatter();
    txtHandler.setFormatter(txtFormatter);
    logger.addHandler(txtHandler);

    // create an HTML formatter
    htmlFormatter = new HTMLFormatter();
    htmlHandler.setFormatter(htmlFormatter);
    logger.addHandler(htmlHandler);

  }
}
