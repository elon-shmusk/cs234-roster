package src.main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.main.database.Database;

/**
 * A Java Swing application to display the Moravian University Women's Basketball Roster
 * using JTabbedPane, JPanel, JTextArea, and JScrollPane.
 */
public class RosterTab extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;

    /**
     * Constructs the Roster frame.
     * @author Fernando Peralta Castro
     */
    public RosterTab() {
        setLayout(new BorderLayout());

        // Initialize the table
        table = new JTable();
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        // Add the table to the panel
        add(scrollPane, BorderLayout.CENTER);

        // Refresh the roster when the tab is first shown
        refreshRoster();
    }

    public void refreshRoster() {
        // Create an instance of the Database class
        Database database = Database.getInstance();

        try (Connection conn = database.connect()) {
            if (conn != null) {
                String sql = "SELECT * FROM Players";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    ResultSet rs = pstmt.executeQuery();

                    // Create a table model and populate it with data from the ResultSet
                    DefaultTableModel model = new DefaultTableModel();
                    model.setColumnIdentifiers(new String[]{"ID", "First Name", "Last Name", "Position", "Number"});
                    while (rs.next()) {
                        model.addRow(new Object[]{rs.getInt("id"), rs.getString("firstName"),
                                rs.getString("lastName"), rs.getString("position"), rs.getInt("player_Num")});
                    }

                    // Set the table model
                    table.setModel(model);
                } catch (SQLException ex) {
                    System.out.println("Error executing SQL query: " + ex.getMessage());
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException ex) {
            System.out.println("Database connection error: " + ex.getMessage());
        }
    }
}
