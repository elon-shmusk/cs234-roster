package src.main.view;

import src.main.controller.RosterController;
import src.main.model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ArchivePlayerDialog {
    private Connection connection;

    private RosterTab rosterTab;
    private RosterController rosterController;
    private final String databaseUrl = "jdbc:sqlite:data/players.db"; // Update with your database name

    public ArchivePlayerDialog(RosterTab rosterTab, RosterController rosterController) {
        super();
        this.rosterTab = rosterTab;
        this.rosterController = rosterController;

        try {
            // Establish connection to the SQLite database
            connection = DriverManager.getConnection(databaseUrl);
            createTablesIfNotExist(); // Create tables if they don't exist
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle connection error
        }

        // Create and configure your Swing components
        JButton archiveButton = new JButton("Archive");
        archiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle archiving logic here
                archivePlayer();
            }
        });

//       // Create a JFrame to hold your components
//        JFrame frame = new JFrame("Archive Player");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(archiveButton);
//        frame.pack();
//        frame.setVisible(true);
    }

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

    private void archivePlayer() {
        // Assume getSelectedPlayer() returns the player ID or unique identifier
        int playerId = getSelectedPlayer();


        try {
            // Create a prepared statement to move the player to the archive table
            String sql = "INSERT INTO archived(firstName, lastName, position, year, player_Num) " +
                    "SELECT firstName, lastName, position, year, player_Num FROM players WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, playerId);

            // Execute the insert statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Player archived successfully.");
                // Optionally, you can also remove the player from the original table after archiving
                // removePlayer(playerId);

                rosterController.removePlayer(playerId);
                rosterTab.refreshRoster(); // Update the roster after archiving

            } else {
                System.out.println("Failed to archive player.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL error
        }

        rosterTab.refreshRoster();
    }

    // Example method to simulate getting the selected player ID
    private int getSelectedPlayer() {
        // Replace this with your actual logic to get the selected player ID
        return 1; // Just returning 1 for demonstration purposes
    }
}
