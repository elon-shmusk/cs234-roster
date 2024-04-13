package src.main.model;

import src.main.controller.RosterController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerDatabase {
    private static final String DB_URL = "jdbc:sqlite:data/players.db";
    private Connection connection;

    public PlayerDatabase() {
        // Initialize database and create necessary tables
        try {
            connection = DriverManager.getConnection(DB_URL);
            createTables();
        } catch (SQLException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
}

    /**
     * Creates the Players and Stats tables in the database if they do not exist.
     * Kaleb Missmer
     */
    private void createTables() {
        String playerTableSQL = "CREATE TABLE IF NOT EXISTS Players (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " firstName VARCHAR(20) NOT NULL,\n"
                + " lastName VARCHAR(20) NOT NULL,\n"
                + " position VARCHAR(20) NOT NULL,\n"
                + " year VARCHAR(10) NOT NULL,\n"
                + ");";

        String statsTableSQL = "CREATE TABLE IF NOT EXISTS Stats (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " date String NOT NULL,\n"
                + " player_id INT NOT NULL,\n"
                + " freeThrowsMade INT,\n"
                + " freeThrowsAttempted INT,\n"
                + " freeThrowsPercentage REAL,\n"
                + " threePointsPercentage REAL,\n"
                + " threePointsMade INT,\n"
                + " threePointsAttempted INT,\n"
                + " FOREIGN KEY (player_id) REFERENCES Players(id)\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(playerTableSQL);
            stmt.execute(statsTableSQL);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    /**
     * Adds a new player to the database.
     * @param player the player object to be added
     */
    public void addPlayer(Player player) {
        String sql = "INSERT INTO Players(firstName, lastName, position, player_Num, year) VALUES(?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player.getFirstName());
            pstmt.setString(2, player.getLastName());
            pstmt.setString(3, player.getPosition());
            pstmt.setInt(4, player.getNumber());
            pstmt.setString(5, player.getYear());
            pstmt.executeUpdate();
            System.out.println("Player added to the database.");
        } catch (SQLException e) {
            System.out.println("Error adding player: " + e.getMessage());
        }
    }

    /**
     * Removes a player from the database based on player ID.
     * @param playerId the unique ID of the player to be removed
     */
    public void removePlayer(int playerId) {
        String sql = "DELETE FROM Players WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player removed from the database.");
            } else {
                System.out.println("No player found with ID: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error removing player: " + e.getMessage());
        }
    }






    /**
     * Archives a player by moving them to the 'archived' table in the database.
     * @author Fernando Peralta castro
     */
    private void createTablesIfNotExist() {
        try (Statement statement = connection.createStatement()) {
            // Create 'archived' table if it does not exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS archived (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "firstName TEXT," +
                    "lastName TEXT," +
                    "position TEXT," +
                    "year INTEGER," +
                    "player_Num INTEGER" +
                    ")";
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle table creation error
        }
    }

}
