package src.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;


public class Add_Player extends JDialog
{
    private JTextField numberField, nameField, positionField, ftmField, threeFtgmField, yearField;
    private Connection connection;


    public Add_Player(JPanel GUI)
    {
        super();
        setSize(400, 400);
        setLocationRelativeTo(GUI);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Player Information"));

        JLabel numberLabel = new JLabel("Number:");
        numberField = new JTextField(10);
        inputPanel.add(numberLabel);
        inputPanel.add(numberField);

        JLabel yearLabel = new JLabel("Year:");
        yearField = new JTextField(10);
        inputPanel.add(yearLabel);
        inputPanel.add(yearField);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(10);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel positionLabel = new JLabel("Position:");
        positionField = new JTextField(10);
        inputPanel.add(positionLabel);
        inputPanel.add(positionField);

        JLabel ftmLabel = new JLabel("FTM:");
        ftmField = new JTextField(10);
        inputPanel.add(ftmLabel);
        inputPanel.add(ftmField);

        JLabel threeFtgmLabel = new JLabel("3FTGM:");
        threeFtgmField = new JTextField(10);
        inputPanel.add(threeFtgmLabel);
        inputPanel.add(threeFtgmField);

        JPanel buttonPanel = new JPanel();
        //TODO: add action listener to update the roster connected to the database
        JButton updateButton = new JButton("Update Roster");


        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve input values
                addPlayer();

                // Close the dialog
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the dialog without doing anything
                dispose();
            }
        });

        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Add a player to the database
    private void addPlayer()
    {
        String number = numberField.getText();
        String year = yearField.getText();
        String firstName = nameField.getText();
        String lastName = nameField.getText();
        String position = positionField.getText();
        String ftm = ftmField.getText();
        String threeFtgm = threeFtgmField.getText();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO players (first_name, last_name, position) VALUES (?, ?, ?)");
            statement.setString(1, number);
            statement.setString(2, year);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, position);
            statement.setString(6, ftm);
            statement.setString(7, threeFtgm);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Player added successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding player");
        }
    }
}
