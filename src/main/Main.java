package src.main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI dashboard = new GUI();
            AddPlayerDialog addPlayerDialog = new AddPlayerDialog(dashboard.getRosterTab());
            RemovePlayerDialog removePlayerDialog = new RemovePlayerDialog(dashboard.getRosterTab());

            dashboard.getAddPlayerButton().addActionListener(e -> addPlayerDialog.setVisible(true));
            dashboard.getRemovePlayerButton().addActionListener(e -> removePlayerDialog.setVisible(true));
        });
    }
}