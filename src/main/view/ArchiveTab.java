package src.main.view;

import javax.swing.*;
import javax.swing.table.JTableHeader;
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



    public ArchiveTab(RosterController rosterController) {
        super();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Archive"));
        setPreferredSize(new Dimension(500, 400));

        this.rosterController = rosterController;


        archiveTable = new JTable();
        archiveTable.setFont(new Font("Arial", Font.PLAIN, 25));
        scrollPane = new JScrollPane(archiveTable);
        archiveTable.setFillsViewportHeight(true);
        archiveTable.setRowHeight(25);

        // Table header font size
        Font headerFont = new Font("Arial", Font.BOLD, 25);
        JTableHeader header = archiveTable.getTableHeader();
        header.setFont(headerFont);

        add(scrollPane, BorderLayout.CENTER);

        populateArchiveTable(rosterController.getAllPlayers());

        restoreButton = new JButton("Restore");
        restoreButton.setFont(new Font("Arial", Font.PLAIN, 25));
        restoreButton.addActionListener(e -> {
            UnarchivePlayerDialog unarchivePlayerDialog = new UnarchivePlayerDialog(this,rosterController);
            unarchivePlayerDialog.setVisible(true);
            }
        );

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(restoreButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    /**
     * Refreshes the roster by fetching the latest list of players and updating the table.
     */
    public void refreshArchive() {
        if (rosterController != null) {
            List<Player> players = rosterController.getAllPlayers();
            populateArchiveTable(players);

        } else {
            // Handle case where rosterController is not set
            System.out.println("RosterController is not set");
        }
    }

        /**
         * Populates the table with the provided list of players.
         * @param players the list of players to display in the table
         */
        private void populateArchiveTable(List<Player> players) {
            // Populate the JTable with player data
            DefaultTableModel model = new DefaultTableModel();

            // Enable sorting on the JTable
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            archiveTable.setRowSorter(sorter);

            model.setColumnIdentifiers(new String[]{"First Name", "Last Name","Year", "Number"});
            for (Player player : players)
            {
                int id = player.getId();

                if (!rosterController.isPlayerArchived(id)) {
                    model.addRow(new Object[]{player.getFirstName(),
                            player.getLastName(), player.getYear(), player.getNumber()});
                }
            }

            archiveTable.setModel(model);
        }
    }

