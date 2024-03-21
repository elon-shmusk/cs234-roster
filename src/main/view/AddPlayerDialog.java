package src.main.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.main.model.*;
import src.main.controller.RosterController;

public class AddPlayerDialog extends JDialog {
    private JTextField nameField;
    private JComboBox<String> yearComboBox;
    private JTextField positionField;
    private JTextField numberField;
    private RosterTab rosterTab;
    private RosterController rosterController;

    public AddPlayerDialog(RosterTab rosterTab, RosterController rosterController) {
        super();
        setTitle("Add Player");
        setSize(300, 200);
        setLocationRelativeTo(rosterTab);
        setLayout(new GridLayout(0, 2));

        this.rosterTab = rosterTab;
        this.rosterController = rosterController;

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        add(nameLabel);
        add(nameField);

        JLabel yearLabel = new JLabel("Year:");
        String[] years = {"Fr.", "So.", "Jr.", "Sr."};
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

        add(addButton);
        add(cancelButton);
    }

    private void addPlayer() {
        // Get player information from the input fields
        String name = nameField.getText();
        String year = (String) yearComboBox.getSelectedItem();
        String position = positionField.getText();
        int number = Integer.parseInt(numberField.getText()); // Parse the number as an integer

        // Pass the data to the controller to handle
        rosterController.addPlayer(name, year, position, number);

        // Close the dialog
        dispose();

        // Refresh the roster after adding a player
        rosterTab.refreshRoster();
    }
}
