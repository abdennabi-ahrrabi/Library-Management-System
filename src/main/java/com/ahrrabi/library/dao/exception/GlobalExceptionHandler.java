package com.ahrrabi.library.dao.jdbc;

import com.ahrrabi.library.dao.logging.AppLogger;

public class GlobalExceptionHandler {

    public static void handleException(Exception e) {
        AppLogger.error("Unexpected error: " + e.getMessage(), e);
    }
}
