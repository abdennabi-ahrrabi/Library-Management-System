package com.ahrrabi.library.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {
    public static final Logger logger = LoggerFactory.getLogger(AppLogger.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
