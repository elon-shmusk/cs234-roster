package src.main.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
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

        buttonPanel = new JPanel();
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

        // Populate statistics app.data rows
        for (Player player : players) {
            // Assuming you have methods to retrieve statistics from the database for each player
            int playerId = player.getId();
            String playerName = rosterController.getPlayerFullName(playerId);
            int freeThrowsMade = rosterController.getFreeThrowsMade(playerId);
            double freeThrowsPercentage = rosterController.getFreeThrowsPercentage(playerId);
            int threePointsMade = rosterController.getThreePointsMade(playerId);
            double threePointsPercentage = rosterController.getThreePointsPercentage(playerId);

            // Add a row with player statistics
            model.addRow(new Object[]{playerName, freeThrowsMade, freeThrowsPercentage, threePointsMade, threePointsPercentage});
        }

        // Set the model to the stats table
        statsTable.setModel(model);
    }


}