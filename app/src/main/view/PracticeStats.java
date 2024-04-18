package src.main.view;
import src.main.controller.RosterController;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import src.main.model.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ComponentAdapter;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class PracticeStats extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private LocalDate currentMonday;
    private RosterController rosterController;
    private JComboBox<String> playerComboBox;
    private ArrayList<String> playerName = new ArrayList<>();

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
        buttonPanel.add(prevWeekButton);
        buttonPanel.add(nextWeekButton);
        buttonPanel.add(updateButton);
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
            model.addColumn(date.format(DateTimeFormatter.ofPattern("MMM/d/yyyy")));
            date = date.plusDays(1);
        }

        // Populate table with data from the database
        refreshStats();
    }

    private void openAddDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Player Stats");
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 250);

        JComboBox<String> playerComboBox = new JComboBox<>(playerName.toArray(new String[0]));
        JTextField dateField = new JTextField();
        dateField.setFont(new Font("Arial", Font.PLAIN, 25));
        JTextField ftmField = new JTextField();
        ftmField.setFont(new Font("Arial", Font.PLAIN, 25));
        JTextField ftaField = new JTextField();
        ftaField.setFont(new Font("Arial", Font.PLAIN, 25));
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.PLAIN, 25));

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = (String) playerComboBox.getSelectedItem();
                String dateText = dateField.getText();
                String ftmText = ftmField.getText();
                String ftaText = ftaField.getText();
                try {
                    LocalDate date = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    int ftm = Integer.parseInt(ftmText);
                    int fta = Integer.parseInt(ftaText);
                    addPlayerStats(playerName,date, ftm, fta);
                    dialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid input. Please enter valid values.");
                }
            }
        });
        JPanel dialogPanel = new JPanel();
        dialogPanel.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.setLayout(new GridLayout(0, 2));

        JLabel playerLabel = new JLabel("Player:");
        playerLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.add(playerLabel);

        playerComboBox.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.add(playerComboBox);

        JLabel dateLabel = new JLabel("Date (MM/dd/yyyy):");
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.add(dateLabel);

        dateField.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.add(dateField);

        JLabel ftmLabel = new JLabel("FTM:");
        ftmLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.add(ftmLabel);

        ftmField.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.add(ftmField);

        JLabel ftaLabel = new JLabel("FTA:");
        ftaLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.add(ftaLabel);

        ftaField.setFont(new Font("Arial", Font.PLAIN, 25));
        dialogPanel.add(ftaField);


        dialogPanel.add(new JLabel()); // Empty label for layout
        JPanel buttonPanel = new JPanel();
        okButton.setFont(new Font("Arial", Font.PLAIN, 25));
        buttonPanel.add(okButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.add(dialogPanel, BorderLayout.CENTER);

        dialog.setVisible(true);
    }

    private void addPlayerStats(String playerName,LocalDate date, int ftm, int fta) {
        // Find the index of the date column in the table model
        int columnIndex = -1;
        for (int i = 2; i < model.getColumnCount(); i++) {
            String header = model.getColumnName(i);
            LocalDate columnDate = LocalDate.parse(header, DateTimeFormatter.ofPattern("MMM/d/yyyy"));
            if (columnDate.equals(date)) {
                columnIndex = i;
                break;
            }
        }

        if (columnIndex != -1) {
            // Iterate through the table data and add player stats to the corresponding date column
            for (int i = 0; i < model.getRowCount(); i += 3) {
                if(model.getValueAt(i, 1).equals(playerName)){
                    model.setValueAt("FTM: "+ftm, i + 1, columnIndex);
                    model.setValueAt("FTA: "+fta, i + 2, columnIndex);}

                List<Player> players = rosterController.getAllPlayers();
                for (Player player : players) {
                    if (player.getName().equals(playerName)) {
                        rosterController.setFreeThrowsMade(player.getId(), ftm);
                        rosterController.setFreeThrowsAttempted(player.getId(), fta);
                    }
                }
            }
        }
    }

    private void populateTable(List<Player> players) {
        List<Player> playerStats = rosterController.getAllStats();


        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            model.addRow(new Object[]{player.getNumber(), player.getName()});
            model.addRow(new Object[]{""});
            model.addRow(new Object[]{""});

            if (playerStats.get(i).getFreeThrowsMade() == 0 && playerStats.get(i).getFreeThrowsAttempted() == 0){
                for (int columnIndex = 2; columnIndex < model.getColumnCount(); columnIndex++) {
                    for (int r = 0; r < model.getRowCount(); r += 3) {
                        model.setValueAt("FTM: " + 0, r + 1, columnIndex);
                        model.setValueAt("FTA: " + 0, r + 2, columnIndex);
                    }
                }
            }
            else {
                for (int j = 0; j < playerStats.size(); j++) {
                    Player playerStat = playerStats.get(j);
                    if (playerStat.getId() == player.getId()) {
                        model.setValueAt(playerStat.getFreeThrowsMade(), i * 3 + 1, j + 2);
                        model.setValueAt(playerStat.getFreeThrowsAttempted(), i * 3 + 2, j + 2);
                    }
                }
            }
        }


    }


    /**
     * Refreshes the statistics by fetching the latest list of players and updating the table.
     * @author Kaleb Missmer
     */
    public void refreshStats() {
        if (rosterController != null) {
            List<Player> players = rosterController.getAllPlayers();
            populateTable(players);
        } else {
            // Handle case where rosterController is not set
            System.out.println("RosterController is not set");
        }
    }

//    private void updateDatabase() {
//        List<Player> players = rosterController.getAllPlayers();
//        for (Player player : players) {
//
//            // Iterate through the table data and update the database
//            for (int i = 2; i < model.getRowCount(); i += 3) {
//                int playerNumber = (int) model.getValueAt(i - 2, 0); // Player number
//                int ftm = (int) model.getValueAt(i, 2); // FTM
//                int fta = (int) model.getValueAt(i + 1, 2); // FTA
//
//                rosterController.setFreeThrowsMade(player.getId(), ftm);
//                rosterController.setFreeThrowsAttempted(player.getId(), fta);
//            }
//        }
//    }
}
