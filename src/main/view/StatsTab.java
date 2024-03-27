package src.main.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import src.main.controller.RosterController;
import src.main.model.Player;

/**
 * A panel that displays the statistics of players in a table format.
 * Kaleb Missmer
 */
public class StatsTab extends JPanel {
    private JTable statsTable;
    private JScrollPane scrollPane;
    private RosterController rosterController;

    /**
     * Constructor that accepts a RosterController and sets up the table.
     * @param rosterController the controller responsible for managing the roster data
     */
    public StatsTab(RosterController rosterController) {
        this.rosterController = rosterController;
        setLayout(new BorderLayout());

        // Initialize the table
        statsTable = new JTable();
        scrollPane = new JScrollPane(statsTable);
        statsTable.setFillsViewportHeight(true);

        // Add the table to the panel
        add(scrollPane, BorderLayout.CENTER);

        // Initialize the stats
        refreshStats();
    }

    /**
     * Refreshes the statistics by fetching the latest list of players and updating the table.
     */
    public void refreshStats() {
        if (rosterController != null) {
            List<Player> players = rosterController.getAllPlayers();
            populateStatsTable(players);
        } else {
            // Handle case where rosterController is not set
            System.out.println("RosterController is not set");
        }
    }

    /**
     * Populates the table with the provided list of players' statistics.
     * @param players the list of players to display statistics for
     */
    private void populateStatsTable(List<Player> players) {
        DefaultTableModel model = new DefaultTableModel();

        // Define column names for statistics
        model.setColumnIdentifiers(new String[]{"ID", "Free Throws Made", "Free Throws Percentage", "Three Points Made", "Three Points Percentage"});

        // Populate statistics data rows
        for (Player player : players) {
            // Assuming you have methods to retrieve statistics from the database for each player
            int playerId = player.getId();
            int freeThrowsMade = rosterController.getFreeThrowsMade(playerId);
            double freeThrowsPercentage = rosterController.getFreeThrowsPercentage(playerId);
            int threePointsMade = rosterController.getThreePointsMade(playerId);
            double threePointsPercentage = rosterController.getThreePointsPercentage(playerId);

            // Add a row with player statistics
            model.addRow(new Object[]{playerId, freeThrowsMade, freeThrowsPercentage, threePointsMade, threePointsPercentage});
        }

        // Set the model to the stats table
        statsTable.setModel(model);
    }
}