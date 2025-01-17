/**
 * PracticeStats.java
 * This class is responsible for displaying the practice statistics of the players in a table.
 * The table displays the player number, player name, and the statistics for each day of the week.
 * The user can navigate through the weeks using the "Previous Week" and "Next Week" buttons.
 * The user can also add a new player stat by clicking the "Add Stat" button.
 * The user can update the database by clicking the "Update" button.
 * Author: Samuel Cadiz, Kaleb Missmer
 */

package src.main.view;


import src.main.controller.RosterController;
import src.main.model.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PracticeStats extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private LocalDate currentMonday;
    private RosterController rosterController;
    private JComboBox<String> playerComboBox;
    private ArrayList<String> playerName = new ArrayList<>();

    /**
     * Constructs a new PracticeStats panel.
     * @param rosterController the roster controller
     */
    public PracticeStats(RosterController rosterController) {
        super(new BorderLayout());
        this.rosterController = rosterController;
        setLayout(new BorderLayout());

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Allow editing only for FTM and FTA columns
                return false;
            }
        };
        table = new JTable(model) {
            @Override
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                if (row == 0 || row % 3 == 0) {
                    return false;
                }
                return super.editCellAt(row, column, e);
            }
        };
        table.setFont(new Font("Arial", Font.PLAIN, 25));
        table.setRowHeight(25);

        List<Player> players = rosterController.getAllPlayers();
        for (Player player : players) {
            playerName.add(player.getName());
        }


        Font headerFont = new Font("Arial", Font.BOLD, 25);
        JTableHeader header = table.getTableHeader();
        header.setFont(headerFont);

        // Add columns to the model
        model.addColumn("Number");
        model.addColumn("Name");

        // Set current Monday to today's Monday
        currentMonday = LocalDate.now().with(DayOfWeek.MONDAY);

        // Display current week in the table
        displayWeek(currentMonday);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        // Add navigation buttons
        JPanel buttonPanel = new JPanel();
        JButton prevWeekButton = new JButton("Previous Week");
        prevWeekButton.setFont(new Font("Arial", Font.PLAIN, 25));
        prevWeekButton.addActionListener(e -> {
            currentMonday = currentMonday.minusWeeks(1);
            displayWeek(currentMonday);
        });
        JButton nextWeekButton = new JButton("Next Week");
        nextWeekButton.setFont(new Font("Arial", Font.PLAIN, 25));
        nextWeekButton.addActionListener(e -> {
            currentMonday = currentMonday.plusWeeks(1);
            displayWeek(currentMonday);
        });
        JButton updateButton = new JButton("Update");
        updateButton.setFont(new Font("Arial", Font.PLAIN, 25));
        JButton addButton = new JButton("Add Stat");
        addButton.setFont(new Font("Arial", Font.PLAIN, 25));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddDialog();
            }
        });

//        updateButton.addActionListener(e -> updateDatabase());
//        buttonPanel.add(prevWeekButton);
//        buttonPanel.add(nextWeekButton);
//        buttonPanel.add(updateButton);
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.NORTH);

        // Add component listener to set the width of the player number column
        table.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                TableColumnModel columnModel = table.getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(5);
            }
        });
    }

    /**
     * displays week
     */
    private void displayWeek(LocalDate monday) {
        // Clear existing table data
        model.setRowCount(0);
        model.setColumnCount(0); // Clear existing columns

        // Add columns for player number and player name
        model.addColumn("Number");
        model.addColumn("Name");



        // Add columns for the days of the week
        LocalDate date = monday;
        for (int i = 0; i < 7; i++) {
            model.addColumn("");
            date = date.plusDays(1);
        }

        // Populate table with data from the database
        refreshStats();
    }


    /**
     * Opens a dialog to add a new player stat.
     */
    private void openAddDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Player Stats");
        dialog.setLayout(new BorderLayout());
        dialog.setSize(600, 250);

        JComboBox<String> playerComboBox = new JComboBox<>(playerName.toArray(new String[0]));
        playerComboBox.setFont(new Font("Arial", Font.PLAIN, 25));
        JTextField dateField = new JTextField();
        dateField.setFont(new Font("Arial", Font.PLAIN, 25));
        JTextField ftmField = new JTextField();
        ftmField.setFont(new Font("Arial", Font.PLAIN, 25));
        JTextField ftaField = new JTextField();
        ftaField.setFont(new Font("Arial", Font.PLAIN, 25));
        JTextField tpmField = new JTextField();
        tpmField.setFont(new Font("Arial", Font.PLAIN, 25));
        JTextField tpaField = new JTextField();
        tpaField.setFont(new Font("Arial", Font.PLAIN, 25));
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.PLAIN, 25));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = (String) playerComboBox.getSelectedItem();
                String dateText = dateField.getText();
                String ftmText = ftmField.getText();
                String ftaText = ftaField.getText();
                String tpmText = tpmField.getText();
                String tpaText = tpaField.getText();
                try {
                    Date date = Date.valueOf(LocalDate.parse(dateText, DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                    int ftm = Integer.parseInt(ftmText);
                    int fta = Integer.parseInt(ftaText);
                    int tpm = Integer.parseInt(tpmText);
                    int tpa = Integer.parseInt(tpaText);
                    addPlayerStats(playerName,date, ftm, fta, tpm, tpa);
                    dialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid input. Please enter valid values.");
                }
            }
        });
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new GridLayout(0, 2));
        JLabel playerLabel = new JLabel("Player:");
        playerLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.add(playerLabel);
        dialogPanel.add(playerComboBox);
        JLabel dateLabel = new JLabel("Date (MM/DD/YYYY):");
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.add(dateLabel);
        dialogPanel.add(dateField);
        JLabel ftmLabel = new JLabel("Free Throws Made:");
        ftmLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.add(ftmLabel);
        dialogPanel.add(ftmField);
        JLabel ftaLabel = new JLabel("Free Throws Attempted:");
        ftaLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.add(ftaLabel);
        dialogPanel.add(ftaField);
        JLabel tpmLabel = new JLabel("Three-Pointers Made:");
        tpmLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.add(tpmLabel);
        dialogPanel.add(tpmField);
        JLabel tpaLabel = new JLabel("Three-Pointers Attempted:");
        tpaLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.add(tpaLabel);
        dialogPanel.add(tpaField);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.add(dialogPanel, BorderLayout.CENTER);

        dialog.setVisible(true);
    }

    /**
     * Adds player stats to the database.
     * @param playerName the name of the player
     * @param date the date of the practice
     * @param ftm the number of free throws made
     * @param fta the number of free throws attempted
     * @param tpm the number of three-pointers made
     * @param tpa the number of three-pointers attempted
     */
    private void addPlayerStats(String playerName,Date date, int ftm, int fta, int tpm, int tpa) {
        // Add player stats to the database
        String firstName = playerName.split(" ")[0];
        String lastName = playerName.split(" ")[1];

        int playerId = rosterController.getPlayerId(firstName, lastName);

        rosterController.addPlayerStats(playerId, date, ftm, fta, tpm, tpa);
        refreshStats();
    }

    private void populateTable(List<Player> players, ArrayList<ArrayList<Integer>> stats) {
        // Clear existing table data
        model.setRowCount(0);

        model.setColumnIdentifiers(new String[]{"Number", "Name", "Date", "", "", "", ""});
        // Add rows to the table
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            // Add a row with player number and name
            model.addRow(new Object[]{player.getNumber(), player.getName()});

            for (ArrayList<Integer> playerStats : stats) {
                Date date = rosterController.getDate(playerStats.get(1));
                ;
                if (playerStats.get(0) == player.getId())

                    model.addRow(new Object[]{"", "",date.toLocalDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) , "FTM:" + playerStats.get(2), "FTA:" + playerStats.get(3), "TPM:" + playerStats.get(4), "TPA:" + playerStats.get(5)});
            }

            // Add a row with player stats
        }




    }

    /**
     * Refreshes the statistics by fetching the latest list of players and updating the table.
     */
    public void refreshStats() {
        if (rosterController != null) {
            List<Player> players = rosterController.getAllPlayers();
            ArrayList<ArrayList<Integer>> stats = rosterController.getAllStats();
            populateTable(players, stats);
        } else {
            // Handle case where rosterController is not set
            System.out.println("RosterController is not set");
        }
    }
}