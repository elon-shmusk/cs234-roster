package src.main.view;

import src.main.controller.RosterController;
import src.main.model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EditPlayerDialog extends JDialog {
    private JTextField FirstnameField;
    private JTextField LastnameField;
    private JComboBox<String> yearComboBox;
    private JTextField positionField;
    private JTextField numberField;
    private RosterTab rosterTab;
    private RosterController rosterController;
    private List<Player> playerList;

    public EditPlayerDialog(RosterTab rosterTab, RosterController rosterController, Player chosenPlayer) {
        super();
        setTitle("Choose Player");
        setSize(300, 200);
        setLocationRelativeTo(rosterTab);
        setLayout(new GridLayout(0, 2));

        this.rosterTab = rosterTab;
        this.rosterController = rosterController;

        playerList = rosterController.getAllPlayers();

        JLabel FirstnameLabel = new JLabel("First Name:");
        FirstnameField = new JTextField();
        add(FirstnameLabel);
        add(FirstnameField);

        JLabel LastnameLabel = new JLabel("Last Name:");
        LastnameField = new JTextField();
        add(LastnameLabel);
        add(LastnameField);

        JLabel yearLabel = new JLabel("Year:");
        String[] years = {"","Fr.", "So.", "Jr.", "Sr."};
        yearComboBox = new JComboBox<>(years);
        add(yearLabel);
        add(yearComboBox);

        JLabel positionLabel = new JLabel("Position:");
        positionField = new JTextField();
        add(positionLabel);
        add(positionField);

        JLabel numberLabel = new JLabel("Number:");
        numberField = new JTextField();
        add(numberLabel);
        add(numberField);

        JButton editButton = new JButton("Update");

        editButton.addActionListener(new ActionListener() {
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

        add(editButton);
        add(cancelButton);
    }

    /**
     * Edits the player information in the roster.
     * @param chosenPlayer the player to be edited
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
            // Pass the data to the controller to handle
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
