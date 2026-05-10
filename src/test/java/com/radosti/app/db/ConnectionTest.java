package com.radosti.app.db;

import java.sql.Connection;
public class ConnectionTest {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("SUCCESS: Connected to PostgreSQL!");
        } catch (Exception e) {
            System.out.println("ERROR: Connection failed");
            e.printStackTrace();
        }
    }
}
