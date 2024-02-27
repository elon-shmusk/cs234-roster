package database;

import myproject.src.main.java.database.Database;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        // Connect to the SQLite database
        try (Connection conn = Database.connect()) {
            if (conn != null) {
                System.out.println("Connected to the database.");

                // Creates Players table if it doesn't exist
                Database.createTable(conn);

            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}