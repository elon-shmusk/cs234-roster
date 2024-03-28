package src.main.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.main.model.*;
import src.main.controller.RosterController;

/**
 * Dialog for adding a new player to the roster.
 * This dialog provides a form for entering player details such as name, year, position, and number.
 */
public class AddPlayerDialog extends JDialog {
    private JTextField FirstnameField;
    private JTextField LastnameField;
    private JComboBox<String> yearComboBox;
    private JTextField positionField;
    private JTextField numberField;
    private RosterTab rosterTab;
    private RosterController rosterController;

    /**
     * Constructs a new AddPlayerDialog.
     * @param rosterTab the RosterTab associated with this dialog
     * @param rosterController the RosterController to handle player addition
     * @author Samuel Cadiz and Deni Velasquez
     */
    public AddPlayerDialog(RosterTab rosterTab, RosterController rosterController) {
        super();
        setTitle("Add Player");
        setSize(400, 300);
        setLocationRelativeTo(rosterTab);
        setLayout(new BorderLayout());

        this.rosterTab = rosterTab;
        this.rosterController = rosterController;

        JPanel playerInfoPanel = new JPanel();
        playerInfoPanel.setLayout(new GridLayout(0, 2));
        playerInfoPanel.setBorder(BorderFactory.createTitledBorder("Enter Player Information"));
        Font font = new Font("Arial", Font.PLAIN, 25);

        JLabel FirstnameLabel = new JLabel("First Name:");
        FirstnameLabel.setFont(font);
        FirstnameField = new JTextField();
        FirstnameField.setFont(font);
        FirstnameField.setFont(font);
        playerInfoPanel.add(FirstnameLabel);
        playerInfoPanel.add(FirstnameField);

        JLabel LastnameLabel = new JLabel("Last Name:");
        LastnameLabel.setFont(font);
        LastnameField = new JTextField();
        LastnameField.setFont(font);
        playerInfoPanel.add(LastnameLabel);
        playerInfoPanel.add(LastnameField);

        JLabel positionLabel = new JLabel("Position:");
        positionLabel.setFont(font);
        positionField = new JTextField();
        positionField.setFont(font);
        playerInfoPanel.add(positionLabel);
        playerInfoPanel.add(positionField);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(font);
        String[] years = {"","Fr.", "So.", "Jr.", "Sr."};
        yearComboBox = new JComboBox<>(years);
        yearComboBox.setFont(font);
        playerInfoPanel.add(yearLabel);
        playerInfoPanel.add(yearComboBox);


        JLabel numberLabel = new JLabel("Number:");
        numberLabel.setFont(font);
        numberField = new JTextField();
        numberField.setFont(font);
        playerInfoPanel.add(numberLabel);
        playerInfoPanel.add(numberField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayer();
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

        Font buttonFont = new Font("Arial", Font.PLAIN, 25);
        addButton.setFont(buttonFont);
        cancelButton.setFont(buttonFont);

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        add(playerInfoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }


    /**
     * Invoked when the 'Add' button is pressed to collect player data and add the player.
     * @author Deni Velasquez
     */
    private void addPlayer() {
        // Get player information from the input fields
        String firstName = FirstnameField.getText();
        String lastName = LastnameField.getText();
        String position = positionField.getText();
        String year = (String) yearComboBox.getSelectedItem();
        int number = Integer.parseInt(numberField.getText()); // Parse the number as an integer

        // Pass the data to the controller to handle
        rosterController.addPlayer(firstName, lastName, position, number, year);

        // Close the dialog
        dispose();

        // Refresh the roster after adding a player
        rosterTab.refreshRoster();
    }
}
