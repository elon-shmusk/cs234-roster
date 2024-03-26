package src.main.view;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import src.main.model.Player;
import src.main.controller.RosterController;


/**
 * A panel that displays the roster in a table format.
 * It allows for viewing and refreshing the list of players.
 */
public class RosterTab extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private RosterController rosterController;

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
     * @param rosterController the controller responsible for managing the roster data
     */
    // Constructor with RosterController argument
    public RosterTab(RosterController rosterController) {
        this(); // Call the constructor without arguments
        this.rosterController = rosterController;
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

        // Initialize the roster
        refreshRoster();
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
        // Populate the JTable with player data
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "First Name", "Last Name","Year", "Position", "Number"});
        for (Player player : players) {
            model.addRow(new Object[]{player.getId(), player.getFirstName(),
                    player.getLastName(),player.getYear(), player.getPosition(), player.getNumber()});
        }
        table.setModel(model);
    }

    /**
     * Retrieves the RosterController associated with this panel.
     * @return the RosterController instance
     */
    public RosterController getRosterController() {
        return rosterController;
    }
}
