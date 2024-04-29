package src.main.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
    * PlayerDatabase class is responsible for creating and managing the database that stores player information.
    * It creates the tables for Players, Dates, PlayerStats, and OverallStats.
    * It also provides methods to add, remove, and retrieve player information from the database.
    * Author: Kaleb Missmer
 */
public class PlayerDatabase {
    private static final String DB_URL = "jdbc:sqlite:app/data/players.db";
    private Connection connection;

    /**
        * Constructor establishes a connection to the database and creates the necessary tables.
        * If the database does not exist, it will be created.
        * If the tables do not exist, they will be created.
     */
    public PlayerDatabase() {
        try {
            this.connection = DriverManager.getConnection(DB_URL);
            this.createTables();
        } catch (SQLException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
    }

    /**
        * Method to create the tables for the database.
        * Tables include Players, Dates, PlayerStats, and OverallStats.
     */

    private void createTables() {
        String playerTableSQL = "CREATE TABLE IF NOT EXISTS Players (\n" +
                " id INTEGER PRIMARY KEY,\n" +
                " player_Num INT NOT NULL,\n" +
                " firstName VARCHAR(20) NOT NULL,\n" +
                " lastName VARCHAR(20) NOT NULL,\n" +
                " position VARCHAR(20) NOT NULL,\n" +
                " year VARCHAR(10) NOT NULL\n" +
                ");";

        String datesTableSQL = "CREATE TABLE IF NOT EXISTS Dates (\n" +
                " date_id INTEGER PRIMARY KEY,\n" +
                " date DATE NOT NULL\n" +
                ");";

        String statsTableSQL = "CREATE TABLE IF NOT EXISTS PlayerStats (\n" +
                " stat_id INTEGER PRIMARY KEY,\n" +
                " player_id INTEGER,\n" +
                " date_id INTEGER,\n" +
                " freeThrowsMade INT,\n" +
                " freeThrowsAttempted INT,\n" +
                " threePointsMade INT,\n" +
                " threePointsAttempted INT,\n" +
                " FOREIGN KEY (player_id) REFERENCES Players(id),\n" +
                " FOREIGN KEY (date_id) REFERENCES Dates(date_id)\n" +
                ");";

        String overallStatsTableSQL = "CREATE TABLE IF NOT EXISTS OverallStats (\n" +
                " overall_id INTEGER PRIMARY KEY,\n" +
                " total_threePointsMade INT,\n" +
                " total_threePointsAttempted INT,\n" +
                " total_players INT,\n" +
                " overall_threePointsPercentage REAL\n" +
                ");";

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(playerTableSQL);
            stmt.execute(datesTableSQL);
            stmt.execute(statsTableSQL);
            stmt.execute(overallStatsTableSQL);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    /**
        * Method to drop the tables from the database.
        * This method is used for testing purposes.
        * It will drop the Players, Dates, PlayerStats, and OverallStats tables.
        * author Kaleb Missmer
     */
    public void dropTables() {

        String statsTableSQL1 = "DROP TABLE IF EXISTS Stats;";

        try {
            Statement stmt = connection.createStatement();

            stmt.execute(statsTableSQL1);
            System.out.println("Tables dropped successfully.");
        } catch (SQLException e) {
            System.out.println("Error dropping tables: " + e.getMessage());
        }
    }


    /**
        * Method to add a player to the database.
        * Player information is inserted into the Players table.
        * @param player - the player to be added to the database
        * author Kaleb Missmer
     */
    public void addPlayer(Player player) {
        String sql = "INSERT INTO Players(firstName, lastName, position, player_Num, year) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
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
        * Method to retrieve all players from the database.
        * Players are retrieved from the Players table.
        * @return a list of all players in the database
        * author Kaleb Missmer
     */

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM Players";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

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

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error fetching players: " + e.getMessage());
        }

        return players;
    }


    /**
        * Method to remove a player from the database by ID.
        * Player information is removed from the Players table.
        * @param playerId - the ID of the player to remove
        * @return the player with the specified ID
        * author Kaleb Missmer
     */

    public void removePlayer(int playerId) {
        String sql = "DELETE FROM Players WHERE id = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, playerId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Player removed from the database.");
            } else {
                System.out.println("No player found with ID: " + playerId);
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error removing player: " + e.getMessage());
        }
    }

    /*
        * Method to add a player to the archived table.
        * Player information is inserted into the archived table.
        * @param player - the player to be added to the archived table
        * author Fernando
     */

    public List<Player> getPlayersNotInArchived() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM Players WHERE id NOT IN (SELECT id FROM archived)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

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

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error fetching players: " + e.getMessage());
        }

        return players;
    }

    /*
        * Method to add a player to the archived table.
        * Player information is inserted into the archived table.
        * @param player - the player to be added to the archived table
        * author Fernando
     */
    public List<Player> getArchivedPlayers() {
        List<Player> archivedPlayers = new ArrayList<>();
        String sql = "SELECT * FROM archived";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String position = rs.getString("position");
                String year = rs.getString("year");
                int number = rs.getInt("player_Num");
                Player player = new Player(id, firstName, lastName, position, number, year);
                archivedPlayers.add(player);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving archived players: " + e.getMessage());
        }

        return archivedPlayers;
    }
}
