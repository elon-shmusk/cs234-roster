package src.main.view;

import src.main.controller.RosterController;
import src.main.model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class playerToEdit extends JDialog {
    private List<JRadioButton> playerRadioButtons;
    private List<Player> playerList;
    private Connection connection;
    private ArchiveTab ArchiveTab;

    /**
     * Constructor for the playerToEdit dialog.
     * @param rosterTab the roster tab
     * @param rosterController the roster controller
     * @author Samuel Cadiz
     */
    public playerToEdit(RosterTab rosterTab,ArchiveTab archiveTab, RosterController rosterController)
    {
        super();
        this.ArchiveTab = archiveTab;
        setTitle("Choose Player");

        setSize(600, 500);

        setLocationRelativeTo(rosterTab);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 2));

        playerList = rosterController.getAllPlayers();
        playerRadioButtons = new ArrayList<>();

        for (Player player : playerList) {
            JRadioButton radioButton = new JRadioButton(player.getFirstName() + " " + player.getLastName());
            radioButton.setFont(new Font("Arial", Font.PLAIN, 25));
            getRadioButtonActionListener(radioButton);
            playerRadioButtons.add(radioButton);
            centerPanel.add(radioButton);
        }

        add(centerPanel, BorderLayout.CENTER);

        JButton editButton = new JButton("Edit");
        editButton.setFont(new Font("Arial", Font.PLAIN, 25));

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditPlayerDialog editPlayerDialog = new EditPlayerDialog(rosterTab, rosterController, getSelectedPlayer());
                editPlayerDialog.setVisible(true);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 25));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        JButton archiveButton = new JButton ("Archive"); // Checkbox for archiving
        archiveButton.setFont(new Font("Arial", Font.PLAIN, 25));
        archiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArchivePlayerDialog archivePlayerDialog = new ArchivePlayerDialog(rosterTab, rosterController);
               int playerID = getSelectedPlayer().getId();
                rosterController.archivePlayer(playerID);

                // Close the dialog
                dispose();

                // Refresh the roster after adding a player
                rosterTab.refreshRoster(); // Refresh the list of players in the GUI

                // archivePlayerDialog.setVisible(true);
            }
        });

        JButton unarchiveButton = new JButton ("Unarchive"); // Checkbox for archiving
        unarchiveButton.setFont(new Font("Arial", Font.PLAIN, 25));

        unarchiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // UnarchivePlayerDialog unarchivePlayerDialog = new UnarchivePlayerDialog(ArchiveTab, rosterController);
                // unarchivePlayerDialog.setVisible(true);

                ArchivedPlayersPanel archivedPlayersPanel = new ArchivedPlayersPanel(rosterController, rosterTab, ArchiveTab);
                archivedPlayersPanel.setVisible(true);

                // int playerID = getSelectedPlayer().getId();
                // rosterController.unarchivePlayer(playerID);

                // Create a new JFrame to hold the panel

                // Close the dialog
                dispose();

                // Refresh the roster after adding a player
                // rosterTab.refreshRoster(); // Refresh the list of players in the GUI

                // archivePlayerDialog.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(archiveButton);
        buttonPanel.add(unarchiveButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }




    /**
     * Adds an action listener to the radio button.
     * @param radioButton the radio button to add the action listener to
     * @author Samuel Cadiz
     */
    private void getRadioButtonActionListener(JRadioButton radioButton)
    {
        radioButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for (JRadioButton button : playerRadioButtons)
                    button.setSelected(false);

                radioButton.setSelected(true);
            }
        });
    }

    /**
     * Gets the selected player from the radio buttons.
     * @return the selected player
     * @author Fernando Peralta Castro
     */
    private Player getSelectedPlayer() {
        for (int i = 0; i < playerRadioButtons.size(); i++)
        {
            if (playerRadioButtons.get(i).isSelected())
                return playerList.get(i);
        }
        return null;
    }



}
