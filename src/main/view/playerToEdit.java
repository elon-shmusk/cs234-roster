package src.main.view;

import src.main.controller.RosterController;
import src.main.model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class playerToEdit extends JDialog {
    private List<JRadioButton> playerRadioButtons;
    private List<Player> playerList;

    public playerToEdit(RosterTab rosterTab, RosterController rosterController)
    {
        super();
        setTitle("Choose Player");
        setSize(300, 200);
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

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

    private Player getSelectedPlayer() {
        for (int i = 0; i < playerRadioButtons.size(); i++)
        {
            if (playerRadioButtons.get(i).isSelected())
                return playerList.get(i);
        }
        return null;
    }



}
