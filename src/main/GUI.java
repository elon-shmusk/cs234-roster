package src.main;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private RosterTab rosterTab;
    private JButton addPlayerButton;
    private JButton removePlayerButton;

    /**
     * Constructor for the GUI class.
     */
    public GUI() {
        initializeUI();
    }

    /**
     * @author Kaleb Missmer 
     * Initializes the UI for the application.
     */
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("Basketball Roster");

        rosterTab = new RosterTab();
        getContentPane().add(rosterTab, BorderLayout.CENTER);

        // Create buttons
        addPlayerButton = new JButton("Add Player");
        removePlayerButton = new JButton("Remove Player");

        // Set Font Size
        Font buttonFont = new Font("Arial", Font.PLAIN, 30); // Choose your font and size
        addPlayerButton.setFont(buttonFont);
        removePlayerButton.setFont(buttonFont);

        // Add buttons to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addPlayerButton);
        buttonPanel.add(removePlayerButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Getter methods for buttons
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
