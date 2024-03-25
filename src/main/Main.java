package src.main;

import src.main.view.*;
import src.main.controller.*;
import src.main.model.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlayerDatabase playerDatabase = new PlayerDatabase();
            RosterController rosterController = new RosterController(playerDatabase, null); // Pass null for now

            RosterTab rosterTab = new RosterTab(rosterController); // Initialize RosterTab with the controller

            // Set the rosterController for the RosterTab
            rosterController.setRosterTab(rosterTab);

            GUI dashboard = new GUI(rosterController);
            AddPlayerDialog addPlayerDialog = new AddPlayerDialog(rosterTab, rosterController); // Pass the rosterController
            RemovePlayerDialog removePlayerDialog = new RemovePlayerDialog(rosterTab, rosterController); // Pass the rosterController
            playerToEdit playertoEdit = new playerToEdit(rosterTab, rosterController); // Pass the rosterController

            dashboard.getAddPlayerButton().addActionListener(e -> addPlayerDialog.setVisible(true));
            dashboard.getRemovePlayerButton().addActionListener(e -> removePlayerDialog.setVisible(true));
            dashboard.getEditPlayerButton().addActionListener(e -> playertoEdit.setVisible(true));
        });
    }
}
