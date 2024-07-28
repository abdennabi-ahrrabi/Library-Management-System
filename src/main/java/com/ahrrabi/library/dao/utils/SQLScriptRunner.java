package com.ahrrabi.library.dao.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class SQLScriptRunner {

    public static void runScript(Connection connection, String scriptPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(scriptPath))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            executeScript(connection, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void executeScript(Connection connection, String script) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(script);
            System.out.println("Script executed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
