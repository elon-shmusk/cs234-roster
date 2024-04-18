package src.main.view;
import src.main.controller.RosterController;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import src.main.model.Player;

import java.time.temporal.ChronoUnit;
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

    public PracticeStats(RosterController rosterController) {
        super(new BorderLayout());
        this.rosterController = rosterController;
        setLayout(new BorderLayout());

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Allow editing only for FTM and FTA columns
                return column >= 2 && column % 2 == 0;
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


        Font headerFont = new Font("Arial", Font.BOLD, 20);
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
        prevWeekButton.addActionListener(e -> {
            currentMonday = currentMonday.minusWeeks(1);
            displayWeek(currentMonday);
        });
        JButton nextWeekButton = new JButton("Next Week");
        nextWeekButton.addActionListener(e -> {
            currentMonday = currentMonday.plusWeeks(1);
            displayWeek(currentMonday);
        });
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> updateDatabase());
        buttonPanel.add(prevWeekButton);
        buttonPanel.add(nextWeekButton);
        buttonPanel.add(updateButton);
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
        populateTable();
    }

    private void populateTable() {
        List<Player> players = rosterController.getAllPlayers();
        List<Player> playerStats = rosterController.getAllStats();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            model.addRow(new Object[]{player.getNumber(), player.getName()});
            model.addRow(new Object[]{"","FTM"});
            model.addRow(new Object[]{"","FTA"});
            for (int j = 0; j < playerStats.size(); j++) {
                Player playerStat = playerStats.get(j);
                if (playerStat.getId() == player.getId()) {
                    model.setValueAt(playerStat.getFreeThrowsMade(), i * 3 + 1, j + 2);
                    model.setValueAt(playerStat.getFreeThrowsAttempted(), i * 3 + 2, j + 2);
                }
                else
                {
                    model.setValueAt(0, i * 3 + 1, j + 2);
                    model.setValueAt(0, i * 3 + 2, j + 2);

                }
            }
        }
    }


    private void updateDatabase() {
        List<Player> players = rosterController.getAllPlayers();
        for (Player player : players) {

            // Iterate through the table data and update the database
            for (int i = 2; i < model.getRowCount(); i += 3) {
                int playerNumber = (int) model.getValueAt(i - 2, 0); // Player number
                int ftm = (int) model.getValueAt(i, 2); // FTM
                int fta = (int) model.getValueAt(i + 1, 2); // FTA

                rosterController.setFreeThrowsMade(player.getId(), ftm);
                rosterController.setFreeThrowsAttempted(player.getId(), fta);
            }
        }
    }


}
