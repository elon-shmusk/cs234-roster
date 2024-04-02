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
    private int selectedPlayerId;
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

    /**
     * Archives the selected player by moving them to the 'archived' table.
     */
    private void archivePlayer() {
        int playerId = getSelectedPlayer();
        rosterController.archivePlayer(playerId);
        rosterTab.refreshRoster();
    }

/**
     * Gets the ID of the selected player.
     * @return the ID of the selected player
     * @author Fernando Peralta Castro
     */
    private int getSelectedPlayer() {
        // Return the ID of the selected player
        return this.selectedPlayerId;
    }

    }
