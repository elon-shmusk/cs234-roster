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

    // Creating Players and Stats Tables
    public void createTables(Connection conn) {
        String playerTableSQL = "CREATE TABLE IF NOT EXISTS Players (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " firstName VARCHAR(20) NOT NULL,\n"
                + " lastName VARCHAR(20) NOT NULL,\n"
                + " year VARCHAR(10) NOT NULL,\n"
                + " number INT NOT NULL\n"
                + ");";

        String statsTableSQL = "CREATE TABLE IF NOT EXISTS Stats (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " player_id INT NOT NULL,\n"
                + " freeThrowsMade INT NOT NULL,\n"
                + " freeThrowsPercentage REAL,\n"
                + " threePointsMade INT NOT NULL,\n"
                + " threePointsPercentage REAL,\n"
                + " FOREIGN KEY (player_id) REFERENCES Players(id)\n"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            // Execute the player table creation query
            stmt.execute(playerTableSQL);

            // Execute the stats table creation query
            stmt.execute(statsTableSQL);

            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}