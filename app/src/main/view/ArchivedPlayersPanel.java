package src.main.view;

import src.main.controller.RosterController;
import src.main.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ArchivedPlayersPanel extends JDialog {
    private RosterController rosterController;
    private RosterTab rosterTab;
    private ArchiveTab archiveTab;

    public ArchivedPlayersPanel(RosterController rosterController, RosterTab rosterTab, ArchiveTab archiveTab){
        super();
        this.rosterController = rosterController;
        this.rosterTab = rosterTab;
        this.archiveTab = archiveTab;
        setTitle("Choose Player");
        setSize(600, 500);
        setLocationRelativeTo(this.archiveTab);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        populatePanel(); // Call the method here
    }

    public void populatePanel() {
        List<Player> archivedPlayers = rosterController.getArchivedPlayers();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        for (Player player : archivedPlayers) {
            JButton playerButton = new JButton(player.getFirstName() + " " + player.getLastName());
            playerButton.setFont(new Font("Arial", Font.PLAIN, 35)); // Set a larger font
            playerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rosterController.unarchivePlayer(player.getId());
                    rosterTab.refreshRoster();
                    archiveTab.refreshArchive();
                    dispose();
                }
            });
            buttonPanel.add(playerButton);
        }

        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        add(scrollPane, BorderLayout.CENTER);
    }
}