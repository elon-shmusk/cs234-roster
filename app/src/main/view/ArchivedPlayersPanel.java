package src.main.view;

import src.main.controller.RosterController;
import src.main.model.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ArchivedPlayersPanel extends JPanel {
    private RosterController rosterController;
    private RosterTab rosterTab;

    public ArchivedPlayersPanel(RosterController rosterController, RosterTab rosterTab) {
        this.rosterController = rosterController;
        this.rosterTab = rosterTab;
    }

    public void populatePanel() {
        List<Player> archivedPlayers = rosterController.getArchivedPlayers();
        for (Player player : archivedPlayers) {
            JButton playerButton = new JButton(player.getFirstName() + " " + player.getLastName());
            playerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rosterController.unarchivePlayer(player.getId());
                    rosterTab.refreshRoster();
                }
            });
            add(playerButton);
        }
    }
}