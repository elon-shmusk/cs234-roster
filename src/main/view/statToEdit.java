package src.main.view;

import src.main.controller.RosterController;
import src.main.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class statToEdit extends JDialog{
    private List<JRadioButton> playerRadioButtons;
    private List<Player> playerList;
    private GUI GUI;
    private RosterTab rosterTab;


    public statToEdit(PracticeStats practiceStats, RosterController rosterController)
    {
        super();
        setTitle("Choose Player");
        setSize(500, 500);
        setLocationRelativeTo(rosterTab);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createTitledBorder("Select Player"));
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

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditStatDialog editStatDialog = new EditStatDialog(practiceStats, rosterController, getSelectedPlayer());
                editStatDialog.setVisible(true);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        editButton.setFont(new Font("Arial", Font.PLAIN, 25));
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 25));

        buttonPanel.add(editButton);
        buttonPanel.add(cancelButton);
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
     * @author Samuel Cadiz
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
