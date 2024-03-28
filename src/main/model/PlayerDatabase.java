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
    private static final String DB_URL = "jdbc:sqlite:data/players.db";

    public PlayerDatabase() {
        // Initialize database and create necessary tables
        createTables();
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
     * Fetches all players from the database.
     * @return a list of all players in the database
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
                Player player = new Player(id, firstName, lastName, position, number, year);
                players.add(player);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching players: " + e.getMessage());
        }

        return players;
    }

    /**
     * Updates the number of a player in the database.
     * @param playerId the unique ID of the player to be updated
     * @param number the new number of the player
     */
    public void setPlayerNumber(int playerId, int number) {
        String sql = "UPDATE Players SET player_Num = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, number);
            pstmt.setInt(2, playerId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player number updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating player number: " + e.getMessage());
        }
    }

    /**
     * Updates the position of a player in the database.
     * @param playerId the unique ID of the player to be updated
     * @param position the new position of the player
     */
    public void setPlayerPosition(int playerId, String position) {
        String sql = "UPDATE Players SET position = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, position);
            pstmt.setInt(2, playerId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player position updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating player position: " + e.getMessage());
        }
    }

    /**
     * Updates the year of a player in the database.
     * @param playerId the unique ID of the player to be updated
     * @param year the new year of the player
     */
    public void setPlayerYear(int playerId, String year) {
        String sql = "UPDATE Players SET year = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, year);
            pstmt.setInt(2, playerId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player year updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating player year: " + e.getMessage());
        }
    }

    /**
     * Updates the first name of a player in the database.
     * @param playerId the unique ID of the player to be updated
     * @param firstName the new first name of the player
     */
    public void setPlayerFirstName(int playerId, String firstName) {
        String sql = "UPDATE Players SET firstName = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setInt(2, playerId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player's first name updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating player name: " + e.getMessage());
        }
    }

    /**
     * Updates the last name of a player in the database.
     * @param playerId the unique ID of the player to be updated
     * @param lastName the new last name of the player
     */
    public void setPlayerLastName(int playerId, String lastName) {
        String sql = "UPDATE Players SET lastName = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, lastName);
            pstmt.setInt(2, playerId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player's last name updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating player name: " + e.getMessage());
        }
    }

    /**
     * Fetches the player ID from the database based on the first and last name.
     * @param firstName the first name of the player
     * @param lastName the last name of the player
     * @return the unique ID of the player
     */
    public int getPlayerId(String firstName, String lastName) {
        String sql = "SELECT id FROM Players WHERE firstName = ? AND lastName = ?";
        int playerId = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                playerId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching player ID: " + e.getMessage());
        }

        return playerId;
    }

    /**
     * Fetches the player first name from the database based on the player ID.
     * @param playerId the unique ID of the player
     * @return the first name of the player
     */
    public String getPlayerFirstName(int playerId) {
        String sql = "SELECT firstName, lastName FROM Players WHERE id = ?";
        String playerFirstName = "";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("firstName");
                playerFirstName = firstName;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching player name: " + e.getMessage());
        }

        return playerFirstName;
    }

    /**
     * Fetches the player last name from the database based on the player ID.
     * @param playerId the unique ID of the player
     * @return the last name of the player
     */
    public String getPlayerLastName(int playerId) {
        String sql = "SELECT firstName, lastName FROM Players WHERE id = ?";
        String playerLastName = "";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String lastName = rs.getString("lastName");
                playerLastName = lastName;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching player name: " + e.getMessage());
        }

        return playerLastName;
    }

    /**
     * Fetches the player full name from the database based on the player ID.
     * @param playerId the unique ID of the player
     * @return the full name of the player
     */
    public String getPlayerFullName(int playerId) {
        String sql = "SELECT firstName, lastName FROM Players WHERE id = ?";
        String playerFullName = "";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                playerFullName = firstName + " " + lastName;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching player name: " + e.getMessage());
        }

        return playerFullName;
    }

    /**
     * Fetches the player number from the database based on the player ID.
     * @param playerId the unique ID of the player
     * @return the number of the player
     */
    public String getPlayerPosition(int playerId) {
        String sql = "SELECT position FROM Players WHERE id = ?";
        String position = "";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                position = rs.getString("position");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching player position: " + e.getMessage());
        }

        return position;
    }

    /**
     * Fetches the player year from the database based on the player ID.
     * @param playerId the unique ID of the player
     * @return the year of the player
     */
    public String getPlayerYear(int playerId) {
        String sql = "SELECT year FROM Players WHERE id = ?";
        String year = "";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                year = rs.getString("year");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching player year: " + e.getMessage());
        }

        return year;
    }

    /**
     * Fetches the player number from the database based on the player ID.
     * @param playerId the unique ID of the player
     * @return the number of the player
     */

    public int getPlayerNumber(int playerId) {
        String sql = "SELECT player_Num FROM Players WHERE id = ?";
        int number = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                number = rs.getInt("player_Num");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching player number: " + e.getMessage());
        }

        return number;

    }

    public void addPlayerStats(int playerId, int freeThrowsMade, double freeThrowsPercentage, int threePointsMade, double threePointsPercentage) {
        String sql = "INSERT INTO Stats(player_id, freeThrowsMade, freeThrowsPercentage, threePointsMade, threePointsPercentage) VALUES(?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            pstmt.setInt(2, freeThrowsMade);
            pstmt.setDouble(3, freeThrowsPercentage);
            pstmt.setInt(4, threePointsMade);
            pstmt.setDouble(5, threePointsPercentage);
            pstmt.executeUpdate();
            System.out.println("Player stats added to the database.");
        } catch (SQLException e) {
            System.out.println("Error adding player stats: " + e.getMessage());
        }
    }

    public void updatePlayerStats(int playerId, int freeThrowsMade, double freeThrowsPercentage, int threePointsMade, double threePointsPercentage) {
        String sql = "SELECT * FROM Stats WHERE player_id = ? UPDATE Stats SET freeThrowsMade = ?, freeThrowsPercentage = ?, threePointsMade = ?, threePointsPercentage = ? WHERE player_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, freeThrowsMade);
            pstmt.setDouble(2, freeThrowsPercentage);
            pstmt.setInt(3, threePointsMade);
            pstmt.setDouble(4, threePointsPercentage);
            pstmt.setInt(5, playerId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player stats updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating player stats: " + e.getMessage());
        }
    }
    public void setFreeThrowsMade(int playerId, int freeThrowsMade) {
        String sql = "UPDATE Stats SET freeThrowsMade = ? WHERE player_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, freeThrowsMade);
            pstmt.setInt(2, playerId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Free throws made updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating free throws made: " + e.getMessage());
        }
    }

    public void setFreeThrowsPercentage(int playerId, double freeThrowsPercentage) {
        String sql = "UPDATE Stats SET freeThrowsPercentage = ? WHERE player_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, freeThrowsPercentage);
            pstmt.setInt(2, playerId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Free throws percentage updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating free throws percentage: " + e.getMessage());
        }
    }

    public void setThreePointsMade(int playerId, int threePointsMade) {
        String sql = "UPDATE Stats SET threePointsMade = ? WHERE player_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, threePointsMade);
            pstmt.setInt(2, playerId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Three points made updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating three points made: " + e.getMessage());
        }
    }

    public void setThreePointsPercentage(int playerId, double threePointsPercentage) {
        String sql = "UPDATE Stats SET threePointsPercentage = ? WHERE player_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, threePointsPercentage);
            pstmt.setInt(2, playerId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Three points percentage updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating three points percentage: " + e.getMessage());
        }
    }

    public int getFreeThrowsMade(int playerId) {
        String sql = "SELECT freeThrowsMade FROM Stats WHERE player_id = ?";
        int freeThrowsMade = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                freeThrowsMade = rs.getInt("freeThrowsMade");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching free throws made: " + e.getMessage());
        }

        return freeThrowsMade;
    }

    public double getFreeThrowsPercentage(int playerId) {
        String sql = "SELECT freeThrowsPercentage FROM Stats WHERE player_id = ?";
        double freeThrowsPercentage = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                freeThrowsPercentage = rs.getDouble("freeThrowsPercentage");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching free throws percentage: " + e.getMessage());
        }

        return freeThrowsPercentage;
    }


    public int getThreePointsMade(int playerId) {
        String sql = "SELECT threePointsMade FROM Stats WHERE player_id = ?";
        int threePointsMade = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                threePointsMade = rs.getInt("threePointsMade");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching three points made: " + e.getMessage());
        }

        return threePointsMade;
    }


    public double getThreePointsPercentage(int playerId) {
        String sql = "SELECT threePointsPercentage FROM Stats WHERE player_id = ?";
        double threePointsPercentage = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                threePointsPercentage = rs.getDouble("threePointsPercentage");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching three points percentage: " + e.getMessage());
        }

        return threePointsPercentage;
    }


}
