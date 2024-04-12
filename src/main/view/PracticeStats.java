package src.main.view;

import src.main.controller.RosterController;
import src.main.model.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class PracticeStats extends JPanel {
    private JTable statsTable;
    private JScrollPane scrollPane;
    private RosterController rosterController;
    private JButton editStatsButton;
    private JPanel buttonPanel;

    /**
     * Constructor that accepts a RosterController and sets up the table.
     * @param rosterController the controller responsible for managing the roster data
     * @author Samuel Cadiz and Kaleb Missmer
     */
    public PracticeStats(RosterController rosterController) {
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
        Font buttonFont = new Font("Arial", Font.PLAIN, 30);
        editStatsButton = new JButton("Edit Stats");
        editStatsButton.setFont(buttonFont);
        editStatsButton.addActionListener(e -> {
            // Open a dialog to edit player statistics
            statToEdit statToEdit = new statToEdit(this, rosterController);
            statToEdit.setVisible(true);
        });
        buttonPanel.add(editStatsButton);
        add(buttonPanel, BorderLayout.SOUTH);



        addInitialStats(rosterController.getAllPlayers());
        refreshStats();
    }


    /**
     * Refreshes the statistics by fetching the latest list of players and updating the table.
     * @author Kaleb Missmer
     */
    public void refreshStats() {
        if (rosterController != null) {
            java.util.List<Player> players = rosterController.getAllPlayers();
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
        int playerId;
        int playerNumber;
        String playerName;
        int freeThrowsMade;
        int freeThrowsAttempted;
        int threePointsMade;
        int threePointsAttempted;
        ArrayList<String> playerNames = new ArrayList<>();
        ArrayList<Integer> playerNumbers = new ArrayList<>();
        ArrayList<Integer> freeThrowsMades = new ArrayList<>();
        ArrayList<Integer> freeThrowsAttempteds = new ArrayList<>();
        ArrayList<Integer> threePointsMades = new ArrayList<>();
        ArrayList<Integer> threePointsAttempteds = new ArrayList<>();



        // Define column names for statistics
        model.setColumnIdentifiers(new String[]{"Number", "Name",});

        // Populate statistics data rows
        for (Player player : players)
        {
            // Assuming you have methods to retrieve statistics from the database for each player
            playerId = player.getId();
            playerNames.add(rosterController.getPlayerFullName(playerId));
            playerNumbers.add(player.getNumber());
//            freeThrowsMade = rosterController.getFreeThrowsMade(playerId);
//            freeThrowsAttempted = rosterController.getFreeThrowsAttempted(playerId);
//            threePointsMade = rosterController.getThreePointsMade(playerId);
//            threePointsAttempted = rosterController.getThreePointsAttempted(playerId);
        }

        for (int row = 0; row < playerNames.size(); row++) {
            model.addRow(new Object[]{playerNumbers.get(row)});
            model.addRow(new Object[]{"",playerNames.get(row)});
//            model.addRow(new Object[]{"","FMA", freeThrowsMades.get(row)});
//            model.addRow(new Object[]{"","FTA", freeThrowsAttempteds.get(row)});
//            model.addRow(new Object[]{"","TPM", threePointsMades.get(row)});
//            model.addRow(new Object[]{"","TPA", threePointsAttempteds.get(row)});
        }
        // Set the model to the stats table
        statsTable.setModel(model);
    }

    private void addInitialStats(List<Player> players) {
//        for (Player player : players) {
//            int playerId = player.getId();
//            rosterController.addPracticeStats(playerId, 0, 0, 0, 0);
//        }
    }
}
