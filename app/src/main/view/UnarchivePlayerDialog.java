package src.main.view;

import src.main.controller.RosterController;
import src.main.model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UnarchivePlayerDialog extends JDialog {

    private List<JRadioButton> playerRadioButtons;
    private List<Player> playerList;
    private ArchiveTab archiveTab;


    public UnarchivePlayerDialog(ArchiveTab archiveTab,RosterController rosterController)
    {
    super();
    this.archiveTab = archiveTab;
    setTitle("Choose Player");
    setSize(600, 500);
    setLocationRelativeTo(archiveTab);
    BorderLayout layout = new BorderLayout();
    setLayout(layout);

    JPanel playerPanel = new JPanel();
    playerPanel.setLayout(new GridLayout(0, 2));

    playerList = rosterController.getAllPlayers();
    playerRadioButtons = new ArrayList<>();

        for (
    Player player : playerList) {
        JRadioButton radioButton = new JRadioButton(player.getFirstName() + " " + player.getLastName());
        radioButton.setFont(new Font("Arial", Font.PLAIN, 25));
        getRadioButtonActionListener(radioButton);
        playerRadioButtons.add(radioButton);
        playerPanel.add(radioButton);
    }

    add(playerPanel, BorderLayout.CENTER);

    JButton restoreButton = new JButton("Restore");
        restoreButton.setFont(new Font("Arial", Font.PLAIN, 25));

        restoreButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = getSelectedPlayer().getFirstName();
            String lastName = getSelectedPlayer().getLastName();
            int number = getSelectedPlayer().getNumber();
            String year = getSelectedPlayer().getYear();

            rosterController.getArchivedPlayerId(firstName, lastName);
            rosterController.addPlayer(firstName, lastName, "n/a",number,year);
            rosterController.unarchivePlayer(getSelectedPlayer().getId());
            archiveTab.refreshArchive();
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
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(restoreButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
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
}
