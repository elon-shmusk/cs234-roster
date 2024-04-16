package src.main.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerDatabase {
    private static final String DB_URL = "jdbc:sqlite:app/data/players.db";
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


    public void dropTables() {
        String dropPlayersTableSQL = "DROP TABLE IF EXISTS Players;";
        String dropStatsTableSQL = "DROP TABLE IF EXISTS Stats;";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            // Drop the Players table
            stmt.execute(dropPlayersTableSQL);
            System.out.println("Players table dropped successfully.");

            // Drop the Stats table
            stmt.execute(dropStatsTableSQL);
            System.out.println("Stats table dropped successfully.");
        } catch (SQLException e) {
            System.out.println("Error dropping tables: " + e.getMessage());
        }
    }

    /**
     * Creates the Players and Stats tables in the database if they do not exist.
     * Kaleb Missmer
     */
    private void createTables() {
        String playerTableSQL = "CREATE TABLE IF NOT EXISTS Players (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " player_Num INT NOT NULL,\n"
                + " firstName VARCHAR(20) NOT NULL,\n"
                + " lastName VARCHAR(20) NOT NULL,\n"
                + " position VARCHAR(20) NOT NULL,\n"
                + " year VARCHAR(10) NOT NULL\n"
                + ");";

        String statsTableSQL = "CREATE TABLE IF NOT EXISTS Stats (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " date DATE NOT NULL,\n"
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

    /**
     * Retrieves a list of all players in the database, including both archived and active players.
     * @return a list of all players
     */
    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM Players";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String position = rs.getString("position");
                int number = rs.getInt("player_Num");
                String year = rs.getString("year");
                boolean archived = rs.getBoolean("archived"); // Assuming there's a column named 'archived'
                Player player = new Player(id, firstName, lastName, position, number, year);
                players.add(player);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching players: " + e.getMessage());
        }

        return players;
    }

    public List<Player> getPlayersNotInArchived() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM Players WHERE id NOT IN (SELECT player_id FROM archived)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String position = rs.getString("position");
                int number = rs.getInt("player_Num");
                String year = rs.getString("year");
                Player player = new Player(id, firstName, lastName, position, number, year);
                players.add(player);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching players: " + e.getMessage());
        }

        return players;
    }

    /**
     * Retrieves a list of archived players from the database.
     * @return a list of archived players
     */
    public List<Player> getArchivedPlayers() {
        List<Player> archivedPlayers = new ArrayList<>();
        String sql = "SELECT * FROM archived";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String position = rs.getString("position");
                String year = rs.getString("year");
                int number = rs.getInt("player_Num");

                // Assuming Player class constructor doesn't include the 'archived' parameter
                Player player = new Player(id, firstName, lastName, position, number, year);
                archivedPlayers.add(player);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving archived players: " + e.getMessage());
        }

        return archivedPlayers;
    }

}
