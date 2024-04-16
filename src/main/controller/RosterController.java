package src.main.controller;

import src.main.model.*;
import src.main.view.ArchiveTab;
import src.main.view.GUI;
import src.main.view.RosterTab;
import src.main.view.StatsTab;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller class for managing the roster.
 * This class handles the operations related to adding and removing players
 * from the roster, as well as retrieving the list of all players.
 */
public class RosterController {
    private static final String DB_URL = "jdbc:sqlite:data/players.db";

    private PlayerDatabase playerDatabase;
    private RosterTab rosterTab;
    private StatsTab statsTab;
    private ArchiveTab archiveTab;

    /**
     * Constructs a new RosterController with the specified player database and roster tab.
     * @param playerDatabase the database of player information
     */
    public RosterController(PlayerDatabase playerDatabase) {
        this.playerDatabase = playerDatabase;
    }

    /**
     * Adds a new player to the roster.
     * @param firstName the first name of the player
     * @param lastName the last name of the player
     * @param position the playing position of the player
     * @param number the jersey number of the player
     * @param year the year of the player
     */
    public void addPlayer(String firstName, String lastName, String position, int number, String year) {
        Player newPlayer = new Player(0, firstName, lastName, position, number, year);
        playerDatabase.addPlayer(newPlayer);
        rosterTab.refreshRoster();
    }

    /**
     * Removes a player from the roster based on player ID.
     * @param playerId the unique ID of the player to be removed
     */
    public void removePlayer(int playerId) {
        playerDatabase.removePlayer(playerId);
        rosterTab.refreshRoster();
    }

    /**
     * Archives a player in the roster based on player ID.
     * @param playerId the unique ID of the player to be archived
     * @author Fernando Peralta Castro
     */
    /**
     * Archives a player by moving them to the 'archived' table in the database.
     * @param playerId the unique ID of the player to be archived
     * @author Fernando Peralta castro
     */
    public void archivePlayer(int playerId) {
        String sql = "INSERT INTO archived(firstName, lastName, position, year, player_Num) " +
                "SELECT firstName, lastName, position, year, player_Num FROM players WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, playerId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Player archived successfully.");

                // Delete the player from the Players table
                sql = "DELETE FROM players WHERE id = ?";
                try (PreparedStatement pstmtDelete = conn.prepareStatement(sql)) {
                    pstmtDelete.setInt(1, playerId);
                    pstmtDelete.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Error deleting player: " + e.getMessage());
                }

            } else {
                System.out.println("Failed to archive player.");
            }
        } catch (SQLException e) {
            System.out.println("Error archiving player: " + e.getMessage());
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
     * Updates the first name of a player in the roster.
     * @param playerId the unique ID of the player
     * @param firstName the new first name of the player
     */
    public void updatePlayerFirstName(int playerId, String firstName)
    {
        setPlayerFirstName(playerId, firstName);
        rosterTab.refreshRoster();
    }

    /**
     * Updates the last name of a player in the roster.
     * @param playerId the unique ID of the player
     * @param lastName the new last name of the player
     */


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

    public void updatePlayerLastName(int playerId, String lastName)
    {
        setPlayerLastName(playerId, lastName);
        rosterTab.refreshRoster();
    }

    /**
     * Updates the full name of a player in the roster.
     * @param playerId the unique ID of the player
     * @param firstName the new first name of the player
     * @param lastName the new last name of the player
     */
    public void updatePlayerFullName(int playerId, String firstName, String lastName)
    {
        setPlayerFirstName(playerId, firstName);
        setPlayerLastName(playerId, lastName);
        rosterTab.refreshRoster();
    }

    /**
     * Updates the playing position of a player in the roster.
     * @param playerId the unique ID of the player
     * @param position the new playing position of the player
     */


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

    public void updatePlayerPosition(int playerId, String position)
    {
        setPlayerPosition(playerId, position);
        rosterTab.refreshRoster();
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
     * Updates the year of a player in the roster.
     * @param playerId the unique ID of the player
     * @param year the new year of the player
     */
    public void updatePlayerYear(int playerId, String year)
    {
        setPlayerYear(playerId, year);
        rosterTab.refreshRoster();
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
     * Updates the number of a player in the roster.
     * @param playerId the unique ID of the player
     * @param number the new number of the player
     */
    public void updatePlayerNumber(int playerId, int number)
    {
        setPlayerNumber(playerId, number);
        rosterTab.refreshRoster();
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
     * Retrieves the number of a player in the roster.
     * @param playerId the unique ID of the player
     * @return the number of the player
     */

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
     * Retrieves a list of all players in the roster.
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
                Player player = new Player(id, firstName, lastName, position, number, year);
                players.add(player);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching players: " + e.getMessage());
        }

        return players;
    }

    /**
     * Retrieves the number of players in the roster.
     * @return the number of players
     */
    /**
     * Gets the number of players in the database.
     * @return the number of players in the database
     */
    public int getNumberOfPlayers() {
        String sql = "SELECT COUNT(*) FROM Players";
        int count = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            count = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("Error fetching number of players: " + e.getMessage());
        }

        return count;
    }


    /**
     * Adds player statistics to the database.
     * @param playerId the unique ID of the player
     * @param freeThrowsMade number of free throws made
     * @param freeThrowsPercentage percentage of free throws made
     * @param threePointsMade number of three points made
     * @param threePointsPercentage percentage of three points made
     */
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

    /**
     * Adds practice statistics for a player to the database.
     * @param playerId the unique ID of the player
     * @param freeThrowsMade the number of free throws made
     * @param freeThrowsAttempted the number of free throws attempted
     * @param threePointsMade the number of three points made
     * @param threePointsAttempted the number of three points attempted
     */
    public void addPracticeStats(int playerId, int freeThrowsMade, int freeThrowsAttempted, int threePointsMade, int threePointsAttempted) {
        String sql = "INSERT INTO Stats(player_id, freeThrowsMade, freeThrowsAttempted, threePointsMade, threePointsAttempted) VALUES(?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            pstmt.setInt(2, freeThrowsMade);
            pstmt.setInt(3, freeThrowsAttempted);
            pstmt.setInt(4, threePointsMade);
            pstmt.setInt(5, threePointsAttempted);
            pstmt.executeUpdate();
            System.out.println("Player stats added to the database.");
        } catch (SQLException e) {
            System.out.println("Error adding player stats: " + e.getMessage());
        }
    }

    public void addDefaultPracticeStats(int playerId, int freeThrowsMade,int FreeThrowsAttempted)
    {
        String sql = "INSERT INTO Stats(player_id, freeThrowsMade, freeThrowsAttempted) VALUES(?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            pstmt.setInt(2, freeThrowsMade);
            pstmt.setInt(3, FreeThrowsAttempted);
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

    public int getFreeThrowsMade(int playerId) {
        String sql = "SELECT freeThrowsMade FROM Stats WHERE player_id = ?";
        int freeThrowsMade = 0;

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

    /**
     * Fetches the number of free throws attempted by a player based on the player ID.
     * @param playerId the unique ID of the player
     * @return the number of free throws attempted by the player
     */
    public int getFreeThrowsAttempted(int playerId) {
        String sql = "SELECT freeThrowsAttempted FROM Stats WHERE player_id = ?";
        int freeThrowsAttempted = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                freeThrowsAttempted = rs.getInt("freeThrowsAttempted");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching free throws attempted: " + e.getMessage());
        }

        return freeThrowsAttempted;
    }


    public double getFreeThrowsPercentage(int playerId) {
        String sql = "SELECT freeThrowsPercentage FROM Stats WHERE player_id = ?";
        double freeThrowsPercentage = 0;

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
        int threePointsMade = 0;

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

    /**
     * Fetches the number of three point throws attempted by a player based on the player ID.
     * @param playerId the unique ID of the player
     * @return the number of three point throws attempted by the player
     */
    public int getThreePointsAttempted(int playerId) {
        String sql = "SELECT threePointsAttempted FROM Stats WHERE player_id = ?";
        int threePointsAttempted = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                threePointsAttempted = rs.getInt("threePointsAttempted");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching three points attempted: " + e.getMessage());
        }

        return threePointsAttempted;
    }



    public double getThreePointsPercentage(int playerId) {
        String sql = "SELECT threePointsPercentage FROM Stats WHERE player_id = ?";
        double threePointsPercentage = 0;

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

    public void setFreeThrowsAttempted(int playerId, int freeThrowsAttempted) {
        String sql = "UPDATE Stats SET freeThrowsAttempted = ? WHERE player_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, freeThrowsAttempted);
            pstmt.setInt(2, playerId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Free throws attempted updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating free throws attempted: " + e.getMessage());
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

    /**
     * Sets the number of three points made by the player in the database.
     * @param playerId the unique ID of the player
     * @param threePointsMade the number of three points made by the player
     * @author Samuel Cadiz
     */
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

    /**
     * Sets the number of three points made by the player in the database.
     * @param playerId the unique ID of the player
     * @param threePointsPercentage the number of three points made by the player
     * @author Samuel Cadiz, Kaleb Missmer
     */
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

    /**
     * Fetches the date of the player's stats based on the player ID.
     * @param playerId the unique ID of the player
     * @return the date of the player's stats
     */
    public Date getDate(int playerId)
    {
        String sql = "SELECT date FROM Stats WHERE player_id = ?";
        Date date = null;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                date = rs.getDate("date"); // Retrieve the date as a java.sql.Date object
            }
        } catch (SQLException e) {
            System.out.println("Error fetching date: " + e.getMessage());
        }

        return date;
    }

    /**
     * Updates the number of three point throws attempted by a player based on the player ID.
     * @param playerId the unique ID of the player
     * @param threePointsAttempted the number of three point throws attempted
     */
    public void setThreePointsAttempted(int playerId, int threePointsAttempted) {
        String sql = "UPDATE Stats SET threePointsAttempted = ? WHERE player_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, threePointsAttempted);
            pstmt.setInt(2, playerId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Three points attempted updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating three points attempted: " + e.getMessage());
        }
    }


    /**
     * Archives a player in the database.
     * @param playerId the unique ID of the player to archive
     */
    /**
     * Checks if a player is archived based on the player ID.
     * @param id the unique ID of the player
     * @return true if the player is archived, false otherwise
     */
    public boolean isArchived(int id)
    {
        String sql = "SELECT * FROM archived WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching player ID: " + e.getMessage());
        }
        return false;
    }



    /**
     * Unarchives a player based on the player ID.
     * @param id the unique ID of the player to be unarchived
     */
    public void unarchivePlayer(int id)
    {
        String sql = "DELETE FROM archived WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player unarchived successfully.");
            } else {
                System.out.println("No player found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error unarchiving player: " + e.getMessage());
        }
    }

    /**
     * Gets the player ID of an archived player.
     * @param firstName the first name of the player
     * @param lastName the last name of the player
     * @return the player ID of the archived player
     */
    /**
     * Fetches all archived players from the database.
     * @return a list of all archived players in the database
     */
    public int getArchivedPlayerId(String firstName, String lastName) {
        String sql = "SELECT id FROM archived WHERE firstName = ? AND lastName = ?";
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
            System.out.println("Error fetching archived player ID: " + e.getMessage());
        }

        return playerId;
    }


    /**
     * Sets the RosterTab for this controller.
     * @param rosterTab the RosterTab to set
     */
    public void setRosterTab(RosterTab rosterTab) {
        this.rosterTab = rosterTab;
    }

    /**
     * Sets the StatsTab for this controller.
     * @param statsTab the StatsTab to set
     * @author Samuel Cadiz
     */
    public void setStatsTab(StatsTab statsTab) {
        this.statsTab = statsTab;
    }

    /**
     * Sets the ArchiveTab for this controller.
     * @param archiveTab the ArchiveTab to set
     */
    public void setArchiveTab(ArchiveTab archiveTab) {
        this.archiveTab = archiveTab;
    }



    /**
     * Archives a player by moving them to the 'archived' table in the database.
     * @author Fernando Peralta castro
     */
    public List<Player> getPlayersNotInArchived() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM Players WHERE id NOT IN (SELECT id FROM archived)";


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




    public double calculateThreePointsPercentage(int playerId) {
        int threePointsMade = getThreePointsMade(playerId);
        int threePointsAttempted = getThreePointsAttempted(playerId);

        if (threePointsAttempted == 0) {
            return 0; // Avoid division by zero
        }

        return (double) threePointsMade / threePointsAttempted * 100.0;
    }
}

