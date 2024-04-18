package src.main.view;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.*;
import java.util.List;
import src.main.model.Player;
import src.main.controller.RosterController;

/**
 * A panel that displays the roster in a table format.
 * It allows for viewing and refreshing the list of players.
 * Sam and Kaleb Missmer
 */
public class RosterTab extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private RosterController rosterController;
    private JButton addPlayerButton;
    private JButton removePlayerButton;
    private JButton editPlayerButton;
    private playerToEdit playerToEdit;
    private ArchiveTab archiveTab;
    private static final String DB_URL = "jdbc:sqlite:app/data/players.db";
    private static final String SELECT_QUERY = "SELECT * FROM players";


    /**
     * Default constructor that initializes the panel layout.
     */
    // Constructor without arguments
    public RosterTab() {
        setLayout(new BorderLayout());
        // Initialize other components as needed
    }


    /**
     * Constructor that accepts a RosterController and sets up the table.
     * @param rosterController the controller responsible for managing the roster app.data
     */
    // Constructor with RosterController argument
    public RosterTab(RosterController rosterController) {
        this.rosterController = rosterController;
//        this.archiveTab = archiveTab;
        setLayout(new BorderLayout());

        // Initialize the table
        table = new JTable();
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);

        // Table header font size
        Font headerFont = new Font("Arial", Font.BOLD, 25);
        JTableHeader header = table.getTableHeader();
        header.setFont(headerFont);

        // Add the table to the panel
        add(scrollPane, BorderLayout.CENTER);

        // Font for table content
        Font tableFont = new Font("Arial", Font.PLAIN, 25);
        table.setFont(tableFont);

        addPlayerButton = new JButton("Add Player");
        removePlayerButton = new JButton("Remove Player");
        editPlayerButton = new JButton("Edit Player");

        Font buttonFont = new Font("Arial", Font.PLAIN, 30);
        addPlayerButton.setFont(buttonFont);
        removePlayerButton.setFont(buttonFont);
        editPlayerButton.setFont(buttonFont);

        addPlayerButton.addActionListener(e -> {
            AddPlayerDialog addPlayerDialog = new AddPlayerDialog(this, rosterController);
            addPlayerDialog.setVisible(true);
        });

        removePlayerButton.addActionListener(e -> {
            RemovePlayerDialog removePlayerDialog = new RemovePlayerDialog(this, rosterController);
            removePlayerDialog.setVisible(true);
        });

        editPlayerButton.addActionListener(e -> {
            playerToEdit playerToEdit = new playerToEdit(this, archiveTab,rosterController);

            playerToEdit.setVisible(true);
        });


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addPlayerButton);
        buttonPanel.add(removePlayerButton);
        buttonPanel.add(editPlayerButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Initialize the roster
        refreshRoster();

    }

    private static void fetchAndPopulateData(DefaultTableModel model) throws SQLException {
    try (Connection conn = DriverManager.getConnection(DB_URL);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(SELECT_QUERY)) {

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Set column names in the table model
        for (int i = 1; i <= columnCount; i++) {
            model.addColumn(metaData.getColumnName(i));
        }

        // Populate app.data rows
        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                rowData[i] = rs.getObject(i + 1);
            }
            model.addRow(rowData);
        }

    } catch (SQLException e) {
        throw new SQLException("Error fetching app.data from database: " + e.getMessage(), e);
    }
}

    /**
     * Refreshes the roster by fetching the latest list of players and updating the table.
     */
    public void refreshRoster() {
        if (rosterController != null) {
            List<Player> players = rosterController.getAllPlayers();
            populateTable(players);

        } else {
            // Handle case where rosterController is not set
            System.out.println("RosterController is not set");
        }
    }

    /**
     * Populates the table with the provided list of players.
     * @param players the list of players to display in the table
     */
    private void populateTable(List<Player> players) {
        // Populate the JTable with player app.data
        DefaultTableModel model = new DefaultTableModel();

        // Enable sorting on the JTable
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        model.setColumnIdentifiers(new String[]{"Number","First Name", "Last Name","Position", "Year" });
        for (Player player : players)
        {
            int id = player.getId();

//            if (rosterController.isPlayerArchived(id)) {
//                model.addRow(new Object[]{player.getFirstName(),
//                        player.getLastName(),player.getPosition(), player.getYear(), player.getNumber()});
//            }
            model.addRow(new Object[]{player.getNumber(), player.getFirstName(),
                        player.getLastName(),player.getPosition(), player.getYear()});
        }

        table.setModel(model);
    }

    /**
     * Retrieves the RosterController associated with this panel.
     * @return the RosterController instance
     */
    public RosterController getRosterController() {
        return rosterController;}

    }



