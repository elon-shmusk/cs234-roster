package src.main.database;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        // Connect to the SQLite database
        try (Connection conn = Database.getInstance().connect()) {
            if (conn != null) {
                System.out.println("Connected to the database.");

                // Creates Players table if it doesn't exist
                Database.getInstance().createTables(conn);

            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
