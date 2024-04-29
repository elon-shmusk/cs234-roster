/*
    * EditPlayerDialog.java
    * This class provides a dialog for editing a player's information.
    * It allows the user to change the player's first name, last name, year, position, and number.
 */

package src.main.view;

import src.main.controller.RosterController;
import src.main.model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditPlayerDialog extends JDialog {
    private JTextField FirstnameField;
    private JTextField LastnameField;
    private JComboBox<String> yearComboBox;
    private JTextField positionField;
    private JTextField numberField;
    private RosterController rosterController;
    private List<Player> playerList;
    private RosterTab rosterTab;


    /**
     * Constructs a new EditPlayerDialog.
     * @param rosterTab the RosterTab associated with this dialog
     * @param rosterController the RosterController to handle player editing
     * @param chosenPlayer the player to be edited
     * @author Samuel Cadiz
     */
    public EditPlayerDialog(RosterTab rosterTab,RosterController rosterController, Player chosenPlayer) {
        super();
        setTitle("Choose Player");
        setSize(400, 300);
        setLocationRelativeTo(rosterTab);
        setLayout(new BorderLayout());

        this.rosterController = rosterController;
        this.rosterTab = rosterTab;

        playerList = rosterController.getAllPlayers();
        Font font = new Font("Arial", Font.PLAIN, 25);

        JPanel playerInfoPanel = new JPanel();
        playerInfoPanel.setLayout(new GridLayout(0, 2));
        playerInfoPanel.setBorder(BorderFactory.createTitledBorder("Enter Player Information"));


        JLabel FirstnameLabel = new JLabel("First Name:");
        FirstnameLabel.setFont(font);
        FirstnameField = new JTextField();
        FirstnameField.setFont(font);
        playerInfoPanel.add(FirstnameLabel);
        playerInfoPanel.add(FirstnameField);

        JLabel LastnameLabel = new JLabel("Last Name:");
        LastnameLabel.setFont(font);
        LastnameField = new JTextField();
        LastnameField.setFont(font);
        playerInfoPanel.add(LastnameLabel);
        playerInfoPanel.add(LastnameField);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(font);
        String[] years = {"","Fr.", "So.", "Jr.", "Sr."};
        yearComboBox = new JComboBox<>(years);
        yearComboBox.setFont(font);
        playerInfoPanel.add(yearLabel);
        playerInfoPanel.add(yearComboBox);

        JLabel positionLabel = new JLabel("Position:");
        positionLabel.setFont(font);
        positionField = new JTextField();
        positionField.setFont(font);
        playerInfoPanel.add(positionLabel);
        playerInfoPanel.add(positionField);

        JLabel numberLabel = new JLabel("Number:");
        numberLabel.setFont(font);
        numberField = new JTextField();
        numberField.setFont(font);
        playerInfoPanel.add(numberLabel);
        playerInfoPanel.add(numberField);

        JPanel buttonPanel = new JPanel();

        JButton updateButton = new JButton("Update");

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPlayer(chosenPlayer);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        updateButton.setFont(font);
        cancelButton.setFont(font);

        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);

        add(playerInfoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Edits the player information in the roster.
     * @param chosenPlayer the player to be edited
     * @author Samuel Cadiz
     */
    private void editPlayer(Player chosenPlayer) {

        // Get player information from the input fields
        String firstName = FirstnameField.getText();
        String lastName = LastnameField.getText();
        String year = (String) yearComboBox.getSelectedItem();
        String position = positionField.getText();
        int number;
        try {
            number = Integer.parseInt(numberField.getText()); // Parse the number as an integer
        }
        catch (NumberFormatException e){
            number = 0;
        }
            // Pass the app.data to the controller to handle
            if (!chosenPlayer.getFirstName().equals(firstName) && !chosenPlayer.getLastName().equals(lastName) && !firstName.isEmpty() && !lastName.isEmpty())
                rosterController.updatePlayerFullName(chosenPlayer.getId(), firstName, lastName);

            else
            {

                if (!chosenPlayer.getFirstName().equals(firstName) && !firstName.isEmpty())
                    rosterController.updatePlayerFirstName(chosenPlayer.getId(), firstName);

                else if (!chosenPlayer.getLastName().equals(lastName) && !lastName.isEmpty())
                    rosterController.updatePlayerLastName(chosenPlayer.getId(), lastName);
            }

            if (!chosenPlayer.getPosition().equals(position) && !position.isEmpty())
                rosterController.updatePlayerPosition(chosenPlayer.getId(), position);

            if(!chosenPlayer.getYear().equals(year) && !year.isEmpty())
                rosterController.updatePlayerYear(chosenPlayer.getId(), year);

            if (chosenPlayer.getNumber() != number && number != 0)
                rosterController.updatePlayerNumber(chosenPlayer.getId(), number);

        // Close the dialog
        dispose();

        // Refresh the roster after editing a player
        rosterTab.refreshRoster();
    }
}
