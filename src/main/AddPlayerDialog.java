package src.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import src.main.database.Database;

public class AddPlayerDialog extends JDialog {
    private JTextField nameField;
    private JComboBox<String> yearComboBox;
    private JTextField positionField;
    private JTextField numberField;
    private RosterTab rosterTab;

    /**
     * This is the constructor for the Add_Player class that creates the dialog box for adding a player to the roster.
     */
    public AddPlayerDialog(RosterTab rosterTab) {
        super();
        setTitle("Add Player");
        setSize(300, 200);
        setLocationRelativeTo(rosterTab);
        setLayout(new GridLayout(0, 2));

        this.rosterTab = rosterTab;

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
        String number = numberField.getText();

        // Get the database instance
        Database database = Database.getInstance();

        // Proceed with database operations
        try (Connection conn = database.connect()) {
            // Insert player data into the database
            String sql = "INSERT INTO Players(firstName, lastName, position, player_Num) VALUES(?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                // For simplicity, splitting the full name into first name and last name
                String[] names = name.split("\\s+");
                if (names.length > 1) {
                    pstmt.setString(1, names[0]); // First name
                    pstmt.setString(2, names[1]); // Last name
                } else {
                    pstmt.setString(1, names[0]); // First name only
                    pstmt.setString(2, ""); // Last name empty
                }
                pstmt.setString(3, position);
                pstmt.setInt(4, Integer.parseInt(number));
                pstmt.executeUpdate();
                System.out.println("Player added to the database.");
            } catch (SQLException ex) {
                System.out.println("Error adding player to the database: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println("Database connection error: " + ex.getMessage());
        }

        // Update the roster display
        rosterTab.refreshRoster();

        // Close the dialog
        dispose();
    }
}
