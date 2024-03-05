package src.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Team2AddPlayer extends JFrame {
    private JTextField firstNameField, lastNameField, positionField;
    private JButton addButton;
    private Connection connection;

    public Team2AddPlayer() {
        super("Basketball Roster");

        // Initialize components
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        positionField = new JTextField(20);
        addButton = new JButton("Add Player");

        // Layout
        setLayout(new GridLayout(4, 2));
        add(new JLabel("First Name:"));
        add(firstNameField);
        add(new JLabel("Last Name:"));
        add(lastNameField);
        add(new JLabel("Position:"));
        add(positionField);
        add(new JLabel());
        add(addButton);

        /*

        Database connection


         */


        // Button listener
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPlayer();
            }
        });

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void addPlayer() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String position = positionField.getText();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO players (first_name, last_name, position) VALUES (?, ?, ?)");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, position);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Player added successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding player");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Team2AddPlayer();
            }
        });
    }
}