package src.main.view;

import javax.swing.*;
import java.awt.*;
import src.main.controller.RosterController;

/**
 * Main frame of the application that holds all UI components.
 * This class initializes and displays the main window of the basketball roster application.
 */
public class GUI extends JFrame {
    private RosterTab rosterTab;
    private JButton addPlayerButton;
    private JButton removePlayerButton;
    private JButton editPlayerButton;
    private RosterController rosterController;

    /**
     * Constructs the main GUI frame with the given roster controller.
     * @param rosterController the controller that manages roster operations
     */
    public GUI(RosterController rosterController) {
        this.rosterController = rosterController;
        initializeUI();
    }

    /**
     * Initializes the user interface components and layout.
     * This method sets up the frame's properties, such as size and title, and adds the necessary UI components.
     */
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("Basketball Roster");

        rosterTab = new RosterTab(rosterController);
        getContentPane().add(rosterTab, BorderLayout.CENTER);

        addPlayerButton = new JButton("Add Player");
        removePlayerButton = new JButton("Remove Player");
        editPlayerButton = new JButton("Edit Player");

        Font buttonFont = new Font("Arial", Font.PLAIN, 30);
        addPlayerButton.setFont(buttonFont);
        removePlayerButton.setFont(buttonFont);
        editPlayerButton.setFont(buttonFont);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addPlayerButton);
        buttonPanel.add(removePlayerButton);
        buttonPanel.add(editPlayerButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Retrieves the 'Add Player' button.
     * @return the JButton that triggers the addition of a new player
     */
    public JButton getAddPlayerButton() {
        return addPlayerButton;
    }

    /**
     * Retrieves the 'Remove Player' button.
     * @return the JButton that triggers the removal of an existing player
     */
    public JButton getRemovePlayerButton() {
        return removePlayerButton;
    }
    public JButton getEditPlayerButton() {
        return editPlayerButton;
    }

    /**
     * Retrieves the RosterTab component.
     * @return the RosterTab that displays the player roster
     */
    public RosterTab getRosterTab() {
        return rosterTab;
    }
}
