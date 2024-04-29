/**
 * Controller class for managing the roster.
 * This class handles the operations related to adding and removing players
 * from the roster, as well as retrieving the list of all players.
 */

package src.main.controller;

import src.main.model.*;
import src.main.view.ArchiveTab;
import src.main.view.PracticeStats;
import src.main.view.RosterTab;
import src.main.view.StatsTab;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class RosterController {
    private static final String DB_URL = "jdbc:sqlite:app/data/players.db";

    private PlayerDatabase playerDatabase;
    private RosterTab rosterTab;
    private StatsTab statsTab;
    private PracticeStats practiceStats;
    private ArchiveTab archiveTab;

    /**
     * Constructs a new RosterController with the specified player database and roster tab.
     * @param playerDatabase the database of player information
     */
    public RosterController(PlayerDatabase playerDatabase) {
        this.playerDatabase = playerDatabase;
    }


    public void addPlayer(String firstName, String lastName, String position, int number, String year) {
        String playerInsertSQL = "INSERT INTO Players(player_Num, firstName, lastName, position, year) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement playerPstmt = conn.prepareStatement(playerInsertSQL, Statement.RETURN_GENERATED_KEYS)) {

            // Insert player
            playerPstmt.setInt(1, number);
            playerPstmt.setString(2, firstName);
            playerPstmt.setString(3, lastName);
            playerPstmt.setString(4, position);
            playerPstmt.setString(5, year);
            playerPstmt.executeUpdate();

            // Get the generated player ID
            ResultSet generatedKeys = playerPstmt.getGeneratedKeys();
            int playerId = -1;
            if (generatedKeys.next()) {
                playerId = generatedKeys.getInt(1);
            } else {
                // Handle error
            }

            System.out.println("Player added to the database.");
        } catch (SQLException e) {
            System.out.println("Error adding player: " + e.getMessage());
        }
        statsTab.refreshStats();
        practiceStats.refreshStats();
        archiveTab.refreshArchive();
    }


    /**
     * Removes a player from the roster based on player ID.
     * @param id the unique ID of the player to be removed
     */
    public void removePlayer(int id) {
        playerDatabase.removePlayer(id);
        rosterTab.refreshRoster();
        statsTab.refreshStats();
        practiceStats.refreshStats();
        archiveTab.refreshArchive();
    }

    /**
     * Archives a player in the roster based on player ID.
     * @param id the unique ID of the player to be archived
     * @author Fernando Peralta Castro
     */
    /**
     * Archives a player by moving them to the 'archived' table in the database.
     * @param id the unique ID of the player to be archived
     * @author Fernando Peralta castro
     */
    public void archivePlayer(int id) {
        String sql = "INSERT INTO archived(firstName, lastName, position, year, player_Num) " +
                "SELECT firstName, lastName, position, year, player_Num FROM players WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Player archived successfully.");

                // Delete the player from the Players table
                sql = "DELETE FROM players WHERE id = ?";
                try (PreparedStatement pstmtDelete = conn.prepareStatement(sql)) {
                    pstmtDelete.setInt(1, id);
                    pstmtDelete.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Error deleting player: " + e.getMessage());
                }

                // Refresh the archive tab
                archiveTab.refreshArchive();

            } else {
                System.out.println("Failed to archive player.");
            }
        } catch (SQLException e) {
            System.out.println("Error archiving player: " + e.getMessage());
        }
    }



    /**
     * Updates the first name of a player in the database.
     * @param id the unique ID of the player to be updated
     * @param firstName the new first name of the player
     */
    public void setPlayerFirstName(int id, String firstName) {
        String sql = "UPDATE Players SET firstName = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player's first name updated in the database.");
            } else {
                System.out.println("No player found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error updating player name: " + e.getMessage());
        }
    }

    /**
     * Updates the first name of a player in the roster.
     * @param id the unique ID of the player
     * @param firstName the new first name of the player
     */
    public void updatePlayerFirstName(int id, String firstName)
    {
        setPlayerFirstName(id, firstName);
        rosterTab.refreshRoster();
        statsTab.refreshStats();
        practiceStats.refreshStats();
        archiveTab.refreshArchive();
    }




    /**
     * Sets the last name of a player in the database.
     * @param id the unique ID of the player to be updated
     * @param lastName the new last name of the player
     */
    public void setPlayerLastName(int id, String lastName) {
        String sql = "UPDATE Players SET lastName = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, lastName);
            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player's last name updated in the database.");
            } else {
                System.out.println("No player found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error updating player name: " + e.getMessage());
        }
    }

    /**
     * Updates the last name of a player in the roster.
     * @param id the unique ID of the player
     * @param lastName the new last name of the player
     */
    public void updatePlayerLastName(int id, String lastName)
    {
        setPlayerLastName(id, lastName);
        rosterTab.refreshRoster();
        statsTab.refreshStats();
        practiceStats.refreshStats();
        archiveTab.refreshArchive();
    }

    /**
     * Updates the full name of a player in the roster.
     * @param id the unique ID of the player
     * @param firstName the new first name of the player
     * @param lastName the new last name of the player
     */
    public void updatePlayerFullName(int id, String firstName, String lastName)
    {
        setPlayerFirstName(id, firstName);
        setPlayerLastName(id, lastName);
        rosterTab.refreshRoster();
        statsTab.refreshStats();
        practiceStats.refreshStats();
        archiveTab.refreshArchive();
    }




    /**
     * Updates the position of a player in the database.
     * @param id the unique ID of the player to be updated
     * @param position the new position of the player
     */
    public void setPlayerPosition(int id, String position) {
        String sql = "UPDATE Players SET position = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, position);
            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player position updated in the database.");
            } else {
                System.out.println("No player found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error updating player position: " + e.getMessage());
        }
    }

    /**
     * Updates the position of a player in the roster.
     * @param id the unique ID of the player
     * @param position the new position of the player
     */
    public void updatePlayerPosition(int id, String position)
    {
        setPlayerPosition(id, position);
        rosterTab.refreshRoster();
        archiveTab.refreshArchive();

    }


    /**
     * Updates the year of a player in the database.
     * @param id the unique ID of the player to be updated
     * @param year the new year of the player
     */
    public void setPlayerYear(int id, String year) {
        String sql = "UPDATE Players SET year = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, year);
            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player year updated in the database.");
            } else {
                System.out.println("No player found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error updating player year: " + e.getMessage());
        }
    }

    /**
     * Updates the year of a player in the roster.
     * @param id the unique ID of the player
     * @param year the new year of the player
     */
    public void updatePlayerYear(int id, String year)
    {
        setPlayerYear(id, year);
        rosterTab.refreshRoster();
        statsTab.refreshStats();
    }



    /**
     * Updates the number of a player in the database.
     * @param id the unique ID of the player to be updated
     * @param number the new number of the player
     */
    public void setPlayerNumber(int id, int number) {
        String sql = "UPDATE Players SET player_Num = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, number);
            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player number updated in the database.");
            } else {
                System.out.println("No player found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error updating player number: " + e.getMessage());
        }
    }
    /**
     * Updates the number of a player in the roster.
     * @param id the unique ID of the player
     * @param number the new number of the player
     */
    public void updatePlayerNumber(int id, int number)
    {
        setPlayerNumber(id, number);
        rosterTab.refreshRoster();
        statsTab.refreshStats();
        practiceStats.refreshStats();
        archiveTab.refreshArchive();
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
     * @param id the unique ID of the player
     * @return the full name of the player
     */
    public String getPlayerFullName(int id) {
        String sql = "SELECT firstName, lastName FROM Players WHERE id = ?";
        String playerFullName = "";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
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
     * @param id the unique ID of the player
     * @return the number of the player
     */

    public int getPlayerNumber(int id) {
        String sql = "SELECT player_Num FROM Players WHERE id = ?";
        int number = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
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
     * @param id the unique ID of the player
     * @return the number of the player
     */
    public String getPlayerPosition(int id) {
        String sql = "SELECT position FROM Players WHERE id = ?";
        String position = "";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
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
     * @param id the unique ID of the player
     * @return the year of the player
     */
    public String getPlayerYear(int id) {
        String sql = "SELECT year FROM Players WHERE id = ?";
        String year = "";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
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


    public void addPlayerStats(int playerId, Date date, int freeThrowsMade, int freeThrowsAttempted, int threePointsMade, int threePointsAttempted) {
        // Check if the date exists in the Dates table, and insert it if not
        int dateId = getDateId(date);

        // Insert player stats with the retrieved or newly inserted date_id
        String sql = "INSERT INTO PlayerStats(player_id, date_id, freeThrowsMade, freeThrowsAttempted, threePointsMade, threePointsAttempted) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            pstmt.setInt(2, dateId);
            pstmt.setInt(3, freeThrowsMade);
            pstmt.setInt(4, freeThrowsAttempted);
            pstmt.setInt(5, threePointsMade);
            pstmt.setInt(6, threePointsAttempted);
            pstmt.executeUpdate();
            System.out.println("Player stats added to the database.");
        } catch (SQLException e) {
            System.out.println("Error adding player stats: " + e.getMessage());
        }
    }

    public ArrayList<Integer> getStat(int playerId) {
        String sql = "SELECT freeThrowsMade, freeThrowsAttempted, threePointsMade, threePointsAttempted FROM PlayerStats WHERE player_id = ?";
        ArrayList<Integer> stats = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                stats.add(rs.getInt("freeThrowsMade"));
                stats.add(rs.getInt("freeThrowsAttempted"));
                stats.add(rs.getInt("threePointsMade"));
                stats.add(rs.getInt("threePointsAttempted"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching player stats: " + e.getMessage());
        }

        return stats;
    }

    public ArrayList<ArrayList<Integer>> getAllStats() {
        String sql = "SELECT player_id, date_id, freeThrowsMade, freeThrowsAttempted, threePointsMade, threePointsAttempted FROM PlayerStats";
        ArrayList<ArrayList<Integer>> allStats = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ArrayList<Integer> stats = new ArrayList<>();
                stats.add(rs.getInt("player_id"));
                stats.add(rs.getInt("date_id"));
                stats.add(rs.getInt("freeThrowsMade"));
                stats.add(rs.getInt("freeThrowsAttempted"));
                stats.add(rs.getInt("threePointsMade"));
                stats.add(rs.getInt("threePointsAttempted"));
                allStats.add(stats);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all player stats: " + e.getMessage());
        }

        return allStats;
    }

    /**
     * Retrieves the total number of free throws made by a player.
     * @param playerId the unique ID of the player
     * @return the total number of free throws made
     */

    public int getTotalFreeThrowsMade(int playerId) {
        String sql = "SELECT SUM(freeThrowsMade) AS totalFreeThrowsMade FROM PlayerStats WHERE player_id = ?";
        int totalFreeThrowsMade = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                totalFreeThrowsMade = rs.getInt("totalFreeThrowsMade");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching total free throws made: " + e.getMessage());
        }

        return totalFreeThrowsMade;
    }


    /**
     * Retrieves the total number of three-pointers made by a player.
     * @param playerId the unique ID of the player
     * @return the total number of three-pointers made
     */
    public int getTotalThreePointsMade(int playerId) {
        String sql = "SELECT SUM(threePointsMade) AS totalThreePointsMade FROM PlayerStats WHERE player_id = ?";
        int totalThreePointsMade = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                totalThreePointsMade = rs.getInt("totalThreePointsMade");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching total three points made: " + e.getMessage());
        }

        return totalThreePointsMade;
    }


    /**
     * Sets the total number of free throws Made by a player.
     * @param playerId the unique ID of the player
     * @return the total number of free throws Made
     */

    public void setTotalFreeThrowsMade(int playerId, int totalFreeThrowsMade) {
        String sql = "UPDATE OverallStats SET total_freeThrowsMade = ? WHERE player_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, totalFreeThrowsMade);
            pstmt.setInt(2, playerId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No rows updated for player id: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating total free throws made: " + e.getMessage());
        }
    }

    /**
     * Sets the total number of three-pointers made by a player.
     * @param playerId the unique ID of the player
     * @return the total number of three-pointers made
     */
    public void setTotalThreePointsMade(int playerId, int totalThreePointsMade) {
        String sql = "UPDATE OverallStats SET total_threePointsMade = ? WHERE player_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, totalThreePointsMade);
            pstmt.setInt(2, playerId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No rows updated for player id: " + playerId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating total three points made: " + e.getMessage());
        }
    }


    /**
     * Retrieves the total number of free throws attempted by a player.
     * @param playerId the unique ID of the player
     * @return the total number of free throws attempted
     */
    public int getTotalFreeThrowsAttempted(int playerId) {
        String sql = "SELECT SUM(freeThrowsAttempted) AS totalFreeThrowsAttempted FROM PlayerStats WHERE player_id = ?";
        int totalFreeThrowsAttempted = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                totalFreeThrowsAttempted = rs.getInt("totalFreeThrowsAttempted");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching total free throws attempted: " + e.getMessage());
        }

        return totalFreeThrowsAttempted;
    }


    /**
     * Retrieves the total number of three-pointers attempted by a player.
     * @param playerId the unique ID of the player
     * @return the total number of three-pointers attempted
     */
    public int getTotalThreePointsAttempted(int playerId) {
        String sql = "SELECT SUM(threePointsAttempted) AS totalThreePointsAttempted FROM PlayerStats WHERE player_id = ?";
        int totalThreePointsAttempted = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                totalThreePointsAttempted = rs.getInt("totalThreePointsAttempted");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching total three points attempted: " + e.getMessage());
        }

        return totalThreePointsAttempted;
    }

/**
     * Retrieves the total number of free throws attempted by a player.
     * @return the total number of free throws attempted
     */
    public int getDateId(Date date) {
        // Check if the date exists in the Dates table
        String sql = "SELECT date_id FROM Dates WHERE date = ?";
        int dateId = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, date);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                dateId = rs.getInt("date_id");
            } else {
                // Insert the date into the Dates table
                dateId = insertDate(date);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching or inserting date: " + e.getMessage());
        }

        return dateId;
    }


    /**
     * Retrieves the total number of three-pointers attempted by a player.
     * @return the total number of three-pointers attempted
     */
    public ArrayList<Date> getAllDates() {
        String sql = "SELECT date FROM Dates";
        ArrayList<Date> dates = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                dates.add(rs.getDate("date"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching dates: " + e.getMessage());
        }

        return dates;
    }


    /**
     * Inserts a new date into the Dates table.
     * @param date the date to insert
     * @return the ID of the inserted date
     */
    public int insertDate(Date date) {
        String sql = "INSERT INTO Dates(date) VALUES(?)";
        int dateId = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDate(1, date);
            pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                dateId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error inserting date: " + e.getMessage());
        }

        return dateId;
    }







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



    public void updatePlayerStatsForDate(int playerId, int dateId, int freeThrowsMade, int freeThrowsAttempted, int threePointsMade, int threePointsAttempted) {
        String sql = "UPDATE PlayerStats SET freeThrowsMade = ?, freeThrowsAttempted = ?, threePointsMade = ?, threePointsAttempted = ? WHERE player_id = ? AND date_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, freeThrowsMade);
            pstmt.setInt(2, freeThrowsAttempted);
            pstmt.setInt(3, threePointsMade);
            pstmt.setInt(4, threePointsAttempted);
            pstmt.setInt(5, playerId);
            pstmt.setInt(6, dateId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player stats updated for player ID: " + playerId + " on date ID: " + dateId);
            } else {
                System.out.println("No player stats found for player ID: " + playerId + " on date ID: " + dateId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating player stats: " + e.getMessage());
        }
    }




    public int getFreeThrowsMade(int playerId, Date dateID) {
        String sql = "SELECT ps.freeThrowsMade " +
                "FROM PlayerStats ps " +
                "JOIN Players p ON ps.player_id = p.id " +
                "JOIN Dates d ON ps.date_id = d.date_id " +
                "WHERE p.id = ? AND d.date = ?";
        int freeThrowsMade = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            pstmt.setDate(2, dateID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                freeThrowsMade = rs.getInt("freeThrowsMade");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching free throws made: " + e.getMessage());
        }

        return freeThrowsMade;
    }





    public int getFreeThrowsAttempted(int playerId, int dateId) {
        String sql = "SELECT freeThrowsAttempted " +
                "FROM PlayerStats " +
                "WHERE player_id = ? AND date_id = ?";
        int freeThrowsAttempted = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            pstmt.setInt(2, dateId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                freeThrowsAttempted = rs.getInt("freeThrowsAttempted");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching free throws attempted: " + e.getMessage());
        }

        return freeThrowsAttempted;
    }






    public int getThreePointsMade(int playerId, Date date) {
        String sql = "SELECT ps.threePointsMade " +
                "FROM PlayerStats ps " +
                "JOIN Players p ON ps.player_id = p.id " +
                "JOIN Dates d ON ps.date_id = d.date_id " +
                "WHERE p.id = ? AND d.date = ?";
        int threePointsMade = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            pstmt.setDate(2, date);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                threePointsMade = rs.getInt("threePointsMade");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching three points made: " + e.getMessage());
        }

        return threePointsMade;
    }

    public int getThreePointsAttempted(int playerId, int dateId) {
        String sql = "SELECT threePointsAttempted " +
                "FROM PlayerStats " +
                "WHERE player_id = ? AND date_id = ?";
        int threePointsAttempted = 0;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            pstmt.setInt(2, dateId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                threePointsAttempted = rs.getInt("threePointsAttempted");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching three points attempted: " + e.getMessage());
        }

        return threePointsAttempted;
    }






    public void setFreeThrowsMade(int playerId, int freeThrowsMade, int dateId) {
        String sql = "UPDATE PlayerStats SET freeThrowsMade = ? WHERE player_id = ? AND date_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, freeThrowsMade);
            pstmt.setInt(2, playerId);
            pstmt.setInt(3, dateId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Free throws made updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId + " for the given date ID: " + dateId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating free throws made: " + e.getMessage());
        }
    }

    public void setFreeThrowsAttempted(int playerId, int freeThrowsAttempted, int dateId) {
        String sql = "UPDATE PlayerStats SET freeThrowsAttempted = ? WHERE player_id = ? AND date_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, freeThrowsAttempted);
            pstmt.setInt(2, playerId);
            pstmt.setInt(3, dateId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Free throws attempted updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId + " for the given date ID: " + dateId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating free throws attempted: " + e.getMessage());
        }
    }

    public void setThreePointsMade(int playerId, int threePointsMade, int dateId) {
        String sql = "UPDATE PlayerStats SET threePointsMade = ? WHERE player_id = ? AND date_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, threePointsMade);
            pstmt.setInt(2, playerId);
            pstmt.setInt(3, dateId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Three points made updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId + " for the given date ID: " + dateId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating three points made: " + e.getMessage());
        }
    }



    /**
     * Fetches the date of the player's stats based on the player ID.
     * @param id the unique ID of the player
     * @return the date of the player's stats
     */
    /**
     * Retrieves the date associated with a specific statistic entry based on its ID.
     * @param id the ID of the statistic entry
     * @return the date associated with the statistic entry
     */
    public Date getDate(int id) {
        String sql = "SELECT d.date " +
                "FROM PlayerStats ps " +
                "JOIN Dates d ON ps.date_id = d.date_id " +
                "WHERE ps.stat_id = ?";
        Date date = null;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
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
     * Updates the number of three point throws attempted by a player based on the player ID and date.
     * @param playerId the unique ID of the player
     * @param dateId the ID of the date for which the statistics are updated
     * @param threePointsAttempted the number of three point throws attempted
     */
    public void setThreePointsAttempted(int playerId, int dateId, int threePointsAttempted) {
        String sql = "UPDATE PlayerStats SET threePointsAttempted = ? WHERE player_id = ? AND date_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, threePointsAttempted);
            pstmt.setInt(2, playerId);
            pstmt.setInt(3, dateId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Three points attempted updated in the database.");
            } else {
                System.out.println("No player found with ID: " + playerId + " or no statistics found for the date ID: " + dateId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating three points attempted: " + e.getMessage());
        }
    }


    /**
     * Archives a player in the database.
     * @param id the unique ID of the player to archive
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
     * Updates the first name of a player in the roster.
     *
     * @param 'playerI'd' the unique ID of the player
     * @param 'firstName' the new first name of the player
     */
    public void unarchivePlayer(int playerId) {
        String sql = "INSERT INTO players(firstName, lastName, position, year, player_Num) " +
                "SELECT firstName, lastName, position, year, player_Num FROM archived WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, playerId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Player unarchived successfully.");

                // Delete the player from the archived table
                sql = "DELETE FROM archived WHERE id = ?";
                try (PreparedStatement pstmtDelete = conn.prepareStatement(sql)) {
                    pstmtDelete.setInt(1, playerId);
                    pstmtDelete.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Error deleting player from archived: " + e.getMessage());
                }

            } else {
                System.out.println("Failed to unarchive player.");
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
     * Sets the PracticeStats for this controller.
     * @param practiceStats the PracticeStats to set
     */
    public void setPracticeStats(PracticeStats practiceStats) {
        this.practiceStats = practiceStats;
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



    public List<Player> getActivePlayers() {
        return playerDatabase.getPlayersNotInArchived();
    }

    /**
     * Retrieves a list of all archived players.
     * @return a list of all archived players
     */
    public List<Player> getArchivedPlayers() {
        return playerDatabase.getArchivedPlayers();
    }
}

