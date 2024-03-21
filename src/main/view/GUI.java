package src.main.view;

import javax.swing.*;
import java.awt.*;
import src.main.controller.RosterController;

public class GUI extends JFrame {
    private RosterTab rosterTab;
    private JButton addPlayerButton;
    private JButton removePlayerButton;
    private RosterController rosterController;

    public GUI(RosterController rosterController) {
        this.rosterController = rosterController;
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("Basketball Roster");

        rosterTab = new RosterTab(rosterController);
        getContentPane().add(rosterTab, BorderLayout.CENTER);

        addPlayerButton = new JButton("Add Player");
        removePlayerButton = new JButton("Remove Player");

        Font buttonFont = new Font("Arial", Font.PLAIN, 30);
        addPlayerButton.setFont(buttonFont);
        removePlayerButton.setFont(buttonFont);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addPlayerButton);
        buttonPanel.add(removePlayerButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JButton getAddPlayerButton() {
        return addPlayerButton;
    }

    public JButton getRemovePlayerButton() {
        return removePlayerButton;
    }

    public RosterTab getRosterTab() {
        return rosterTab;
    }
}