
// Created by Kaleb Missmer


package src.main.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:data/players.db";
    private static Database instance;

    // Private constructor to prevent direct instantiation
    private Database() {}

    // Method to get the singleton instance of Database
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    // Connect to SQLite Database
    public Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Creating Players Table
    public void createTable(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS Players (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " firstName VARCHAR(20) NOT NULL,\n"
                + " lastName VARCHAR(20) NOT NULL,\n"
                + " position VARCHAR(30) NOT NULL,\n"
                + " player_Num INT NOT NULL\n"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
