package src.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * A JFrame-based application to add a new player to the basketball roster.
 * @author Fernando
 */
public class Team2AddPlayer extends JFrame {
    private JTextField firstNameField, lastNameField, positionField;
    private JButton addButton;
    private Connection connection;


    /**
     * Constructor that initializes the Team2AddPlayer frame and its components.
     */
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

    /**
     * Adds a new player to the database when the 'Add Player' button is pressed.
     */
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

    /**
     * The main method that creates and displays the Team2AddPlayer frame.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Team2AddPlayer();
            }
        });
    }
}