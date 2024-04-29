/**
 * StatsTab.java
 * A panel that displays the statistics of players in a table format.
 * Kaleb Missmer
 */


package src.main.view;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

import org.slf4j.helpers.MessageFormatter;
import src.main.controller.RosterController;
import src.main.model.Player;
import java.text.DecimalFormat;


/**
 * A panel that displays the statistics of players in a table format.
 * Kaleb Missmer
 */
public class StatsTab extends JPanel {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    private JTable statsTable;
    private JScrollPane scrollPane;
    private RosterController rosterController;
    private JButton editStatsButton;
    private JPanel buttonPanel;

    /**
     * Constructor that accepts a RosterController and sets up the table.
     * @param rosterController the controller responsible for managing the roster app.data
     * @author Samuel Cadiz and Kaleb Missmer
     */
    public StatsTab(RosterController rosterController) {
        this.rosterController = rosterController;;
        setLayout(new BorderLayout());

        // Initialize the table
        Font tableFont = new Font("Arial", Font.PLAIN, 25);
        statsTable = new JTable();
        statsTable.setRowHeight(25);
        statsTable.setFont(tableFont);

        Font headerFont = new Font("Arial", Font.BOLD, 25);
        JTableHeader header = statsTable.getTableHeader();
        header.setFont(headerFont);

        scrollPane = new JScrollPane(statsTable);
        statsTable.setFillsViewportHeight(true);

        // Add the table to the panel
        add(scrollPane, BorderLayout.CENTER);

//        buttonPanel = new JPanel();
//        Font buttonFont = new Font("Arial", Font.PLAIN, 30);
//        editStatsButton = new JButton("Edit Stats");
//        editStatsButton.setFont(buttonFont);
//        editStatsButton.addActionListener(e -> {
//            // Open a dialog to edit player statistics
//            statToEdit statToEdit = new statToEdit(this, rosterController);
//            statToEdit.setVisible(true);
//        });
//        buttonPanel.add(editStatsButton);
//        add(buttonPanel, BorderLayout.SOUTH);



        refreshStats();
    }


    /**
     * Refreshes the statistics by fetching the latest list of players and updating the table.
     * @author Kaleb Missmer
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
     * @author Kaleb Missmer
     */
    private void populateStatsTable(List<Player> players) {
        DefaultTableModel model = new DefaultTableModel();

        // Define column names for statistics
        model.setColumnIdentifiers(new String[]{"Name", "Free Throws Made", "Free Throws %", "Three Points Made", "Three Points %"});

        // Populate statistics data rows
        for (Player player : players) {
            int playerId = player.getId();
            String playerName = rosterController.getPlayerFullName(playerId);

            // Assuming you have methods to retrieve total free throws and three-pointers for the player
            int totalFreeThrowsMade = rosterController.getTotalFreeThrowsMade(playerId);
            int totalFreeThrowsAttempted = rosterController.getTotalFreeThrowsAttempted(playerId);
            int totalThreePointsMade = rosterController.getTotalThreePointsMade(playerId);
            int totalThreePointsAttempted = rosterController.getTotalThreePointsAttempted(playerId);

            // Calculate overall percentages
            double overallFreeThrowsPercentage = calculatePercentage(totalFreeThrowsMade, totalFreeThrowsAttempted);
            double overallThreePointsPercentage = calculatePercentage(totalThreePointsMade, totalThreePointsAttempted);

            // Format percentages to two decimal places
            String formattedFreeThrowsPercentage = DECIMAL_FORMAT.format(overallFreeThrowsPercentage);
            String formattedThreePointsPercentage = DECIMAL_FORMAT.format(overallThreePointsPercentage);

            // Add a row with player statistics
            model.addRow(new Object[]{playerName, totalFreeThrowsMade, formattedFreeThrowsPercentage + "%", totalThreePointsMade, formattedThreePointsPercentage + "%"});

            statsTable.setModel(model);

        }
    }

    /**
     * Calculates the percentage of made shots out of attempted shots.
     * @param made the number of made shots
     * @param attempted the number of attempted shots
     * @return the percentage of made shots out of attempted shots
     */
    private double calculatePercentage(int made, int attempted) {
        if (attempted == 0) {
            return 0; // Handle division by zero
        }
        return ((double) made / attempted) * 100;
    }



}