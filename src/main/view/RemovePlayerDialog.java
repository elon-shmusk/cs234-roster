package src.main.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import src.main.model.*;
import src.main.controller.RosterController;

/**
 * Dialog for removing players from the roster.
 * This dialog presents a list of players with checkboxes to select players for removal.
 */
public class RemovePlayerDialog extends JDialog {
    private RosterTab rosterTab;
    private List<Player> playerList;
    private List<JCheckBox> playerCheckboxes;
    private RosterController rosterController;

    /**
     * Constructs a new RemovePlayerDialog.
     * @param rosterTab the RosterTab associated with this dialog
     * @param rosterController the RosterController to handle player removal
     */
    public RemovePlayerDialog(RosterTab rosterTab, RosterController rosterController) {
        super();
        this.rosterTab = rosterTab;
        this.rosterController = rosterController;
        setSize(400, 400);
        setLocationRelativeTo(rosterTab);
        setLayout(new BorderLayout());

        JPanel checkPanel = new JPanel(new GridLayout(0, 1));
        checkPanel.setBorder(BorderFactory.createTitledBorder("Select Player(s) to Remove"));

        // Fetch player data from the database and populate checkboxes
        fetchPlayersFromDatabase();
        playerCheckboxes = new ArrayList<>();

        for (Player player : playerList) {
            JCheckBox checkBox = new JCheckBox(player.getName()); // Ensure Player class has a getName() method
            playerCheckboxes.add(checkBox);
            checkPanel.add(checkBox);
        }

        JButton updateButton = new JButton("Update Roster");
        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeSelectedPlayers();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);

        add(checkPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Fetches the list of players from the database and populates the dialog with checkboxes.
     */
    private void fetchPlayersFromDatabase() {
        // Fetch player data from the database and populate the playerList
        playerList = rosterController.getAllPlayers();
    }

    /**
     * Removes the selected players from the roster when the 'Update Roster' button is pressed.
     */
    private void removeSelectedPlayers() {
        for (int i = 0; i < playerCheckboxes.size(); i++) {
            JCheckBox checkBox = playerCheckboxes.get(i);
            if (checkBox.isSelected()) {
                Player playerToRemove = playerList.get(i);
                rosterController.removePlayer(playerToRemove.getId());
            }
        }
        // Update the roster display
        rosterTab.refreshRoster();
        // Close the dialog
        dispose();
    }
}
