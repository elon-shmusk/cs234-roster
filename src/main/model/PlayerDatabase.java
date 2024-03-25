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
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Players (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " firstName VARCHAR(20) NOT NULL,\n"
                + " lastName VARCHAR(20) NOT NULL,\n"
                + " position VARCHAR(30) NOT NULL,\n"
                + " player_Num INT NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public void addPlayer(Player player) {
        String sql = "INSERT INTO Players(firstName, lastName, position, player_Num) VALUES(?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player.getFirstName());
            pstmt.setString(2, player.getLastName());
            pstmt.setString(3, player.getPosition());
            pstmt.setInt(4, player.getNumber());
            pstmt.executeUpdate();
            System.out.println("Player added to the database.");
        } catch (SQLException e) {
            System.out.println("Error adding player: " + e.getMessage());
        }
    }

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
                Player player = new Player(id, firstName, lastName, position, number);
                players.add(player);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching players: " + e.getMessage());
        }

        return players;
    }

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
}
