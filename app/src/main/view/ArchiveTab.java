package src.main.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;
import src.main.model.Player;
import src.main.controller.RosterController;

public class ArchiveTab extends JPanel {
    private RosterController rosterController;
    private JTable archiveTable;
    private JScrollPane scrollPane;
    private JButton restoreButton;
    private JButton filterButton;
    private JComboBox<String> filterComboBox;

    public ArchiveTab(RosterController rosterController) {
        super();
        this.rosterController = rosterController;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Archive"));
        setPreferredSize(new Dimension(500, 400));

        // Initialize components
        initializeComponents();

        // Populate the archive table with all players initially
        populateArchiveTable(rosterController.getAllPlayers());
    }

    /**
     * Initializes GUI components.
     */
    private void initializeComponents() {
        archiveTable = new JTable();
        archiveTable.setFont(new Font("Arial", Font.PLAIN, 25));
        scrollPane = new JScrollPane(archiveTable);
        archiveTable.setFillsViewportHeight(true);
        archiveTable.setRowHeight(25);

        // Table header font size
        Font headerFont = new Font("Arial", Font.BOLD, 25);
        archiveTable.getTableHeader().setFont(headerFont);

        add(scrollPane, BorderLayout.CENTER);

        // Restore button
        restoreButton = new JButton("Restore");
        restoreButton.setFont(new Font("Arial", Font.PLAIN, 25));
        restoreButton.addActionListener(e -> {
            // Call the method to restore archived player
            restoreArchivedPlayer();
        });

        // Filter combo box
        filterComboBox = new JComboBox<>(new String[]{"All", "Active", "Archived"});
        filterComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        filterComboBox.addActionListener(e -> {
            // Filter players based on selected option
            filterPlayers((String) filterComboBox.getSelectedItem());
        });

        // Panel for buttons and filter combo box
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(restoreButton);
        buttonPanel.add(new JLabel("Filter:"));
        buttonPanel.add(filterComboBox);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Refreshes the archive tab by fetching the latest list of players and updating the table.
     */
    public void refreshArchive() {
        List<Player> players = rosterController.getAllPlayers();
        populateArchiveTable(players);
    }

    /**
     * Populates the archive table with the provided list of players.
     * @param players the list of players to display in the table
     */
    private void populateArchiveTable(List<Player> players) {
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        archiveTable.setRowSorter(sorter);

        model.setColumnIdentifiers(new String[]{"First Name", "Last Name", "Year", "Number", "Status"});

        for (Player player : players) {
            int id = player.getId();
            String status = rosterController.isArchived(id) ? "Archived" : "Active";
            model.addRow(new Object[]{player.getFirstName(), player.getLastName(), player.getYear(), player.getNumber(), status});
        }

        archiveTable.setModel(model);
    }

    /**
     * Restores the selected archived player.
     */
    private void restoreArchivedPlayer() {
        // Implement the restoration logic here
    }

    /**
     * Filters the players based on the selected filter option.
     * @param filterOption the selected filter option (All, Active, or Archived)
     */
    private void filterPlayers(String filterOption) {
        List<Player> filteredPlayers;

        switch (filterOption) {
            case "Active":
                filteredPlayers = rosterController.getActivePlayers();
                break;
            case "Archived":
                filteredPlayers = rosterController.getArchivedPlayers();
                break;
            default:
                filteredPlayers = rosterController.getAllPlayers();
                break;
        }

        populateArchiveTable(filteredPlayers);
    }
}
