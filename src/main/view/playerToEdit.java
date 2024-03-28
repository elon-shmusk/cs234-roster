package src.main.view;

import src.main.controller.RosterController;
import src.main.model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class playerToEdit extends JDialog {
    private List<JRadioButton> playerRadioButtons;
    private List<Player> playerList;
    private Connection connection;

    public playerToEdit(RosterTab rosterTab, RosterController rosterController)
    {
        super();
        setTitle("Choose Player");
        setSize(400, 200);
        setLocationRelativeTo(rosterTab);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 2));

        playerList = rosterController.getAllPlayers();
        playerRadioButtons = new ArrayList<>();

        for (Player player : playerList) {
            JRadioButton radioButton = new JRadioButton(player.getFirstName() + " " + player.getLastName());
            getRadioButtonActionListener(radioButton);
            playerRadioButtons.add(radioButton);
            centerPanel.add(radioButton);
        }

        add(centerPanel, BorderLayout.CENTER);

        JButton editButton = new JButton("Edit");

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditPlayerDialog editPlayerDialog = new EditPlayerDialog(rosterTab, rosterController, getSelectedPlayer());
                editPlayerDialog.setVisible(true);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        JButton archiveButton = new JButton ("Archive"); // Checkbox for archiving
        archiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArchivePlayerDialog archivePlayerDialog = new ArchivePlayerDialog(rosterTab, rosterController);
                rosterController.archivePlayer();
                // archivePlayerDialog.setVisible(true);
            }
        });

        JButton unarchiveButton = new JButton ("Unarchive"); // Checkbox for archiving
        unarchiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditPlayerDialog editPlayerDialog = new EditPlayerDialog(rosterTab, rosterController, getSelectedPlayer());
                editPlayerDialog.setVisible(true);
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
