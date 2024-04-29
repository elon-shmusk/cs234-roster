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
        JTextField ftmField = new JTextField();
        JTextField ftaField = new JTextField();
        JTextField tpmField = new JTextField();
        JTextField tpaField = new JTextField();
        JButton okButton = new JButton("OK");

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
        dialogPanel.add(new JLabel("Player:"));
        dialogPanel.add(playerComboBox);
        dialogPanel.add(new JLabel("Date (MM/dd/yyyy):"));
        dialogPanel.add(dateField);
        dialogPanel.add(new JLabel("FTM:"));
        dialogPanel.add(ftmField);
        dialogPanel.add(new JLabel("FTA:"));
        dialogPanel.add(ftaField);
        dialogPanel.add(new JLabel("TPM:"));
        dialogPanel.add(tpmField);
        dialogPanel.add(new JLabel("TPA:"));
        dialogPanel.add(tpaField);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.add(dialogPanel, BorderLayout.CENTER);

        dialog.setVisible(true);
    }

    private void addPlayerStats(String playerName,Date date, int ftm, int fta, int tpm, int tpa) {
        // Add player stats to the database
        String firstName = playerName.split(" ")[0];
        String lastName = playerName.split(" ")[1];

        int playerId = rosterController.getPlayerId(firstName, lastName);

        rosterController.addPlayerStats(playerId, date, ftm, fta, tpm, tpa);
        refreshStats();
    }

    private void populateTable(List<Player> players, ArrayList<ArrayList<Integer>> stats) {
        // Plot player stats in the table to correspond with the player
        for (Player player : players) {
            model.addRow(new Object[]{player.getNumber(), player.getName()});
            model.addRow(new Object[]{"", "FTM"});
            model.addRow(new Object[]{"", "FTA"});
            model.addRow(new Object[]{"", "TPM"});
            model.addRow(new Object[]{"", "TPA"});

            for (int i = 0; i < stats.size(); i++) {
                int columnIndex = -1;
                for (int j = 2; i < model.getColumnCount(); j++) {
                    String header = model.getColumnName(i);
                    Date columnDate = Date.valueOf(LocalDate.parse(header, DateTimeFormatter.ofPattern("MMM/d/yyyy")));
                    if (columnDate.equals(rosterController.getDate(stats.get(i).get(1)))) {
                        columnIndex = i;
                        break;
                    }
                }

                if (columnIndex != -1) {
                    // Iterate through the table data and add player stats to the corresponding date column
                    for (int k = 0; i < model.getRowCount(); k += 3) {
                        if(model.getValueAt(i, 1).equals(playerName)){
                            model.setValueAt("FTM: "+stats.get(i).get(2), i + 1, columnIndex);
                            model.setValueAt("FTA: "+stats.get(i).get(3), i + 2, columnIndex);
                            model.setValueAt("TPM: "+stats.get(i).get(4), i + 3, columnIndex);
                            model.setValueAt("TPA: "+stats.get(i).get(5), i + 4, columnIndex);
                        }
                    }
                }
            }

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