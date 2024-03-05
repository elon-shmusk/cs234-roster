package src.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.main.database.Database;

public class RemovePlayerDialog extends JDialog {
    private RosterTab rosterTab;
    private List<Player> playerList;
    private List<JCheckBox> playerCheckboxes;

    public RemovePlayerDialog(RosterTab rosterTab) {
        super();
        this.rosterTab = rosterTab;
        setSize(400, 400);
        setLocationRelativeTo(rosterTab);
        setLayout(new BorderLayout());

        JPanel checkPanel = new JPanel(new GridLayout(0, 1));
        checkPanel.setBorder(BorderFactory.createTitledBorder("Select Player(s) to Remove"));

        // Fetch player data from the database and populate checkboxes
        fetchPlayersFromDatabase();
        playerCheckboxes = new ArrayList<>();

        for (Player player : playerList) {
            JCheckBox checkBox = new JCheckBox(player.getName());
            playerCheckboxes.add(checkBox);
            checkPanel.add(checkBox);
        }

        JButton updateButton = new JButton("Update Roster");
        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeSelectedPlayers();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);

        add(checkPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void fetchPlayersFromDatabase() {
        playerList = new ArrayList<>();
        try (Connection conn = Database.getInstance().connect()) {
            if (conn != null) {
                String sql = "SELECT * FROM Players";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("firstName") + " " + rs.getString("lastName");
                        playerList.add(new Player(id, name));
                    }
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

    private void removeSelectedPlayers() {
        // Implement logic to remove selected players from the database
        for (JCheckBox checkBox : playerCheckboxes) {
            if (checkBox.isSelected()) {
                // Remove player based on the checkbox
                String playerName = checkBox.getText();
                Player playerToRemove = getPlayerByName(playerName);
                if (playerToRemove != null) {
                    // Add logic to remove the player from the database
                    try (Connection conn = Database.getInstance().connect()) {
                        String sql = "DELETE FROM Players WHERE id = ?";
                        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                            pstmt.setInt(1, playerToRemove.getId());
                            pstmt.executeUpdate();
                            System.out.println("Player removed: " + playerName);
                        } catch (SQLException ex) {
                            System.out.println("Error removing player: " + ex.getMessage());
                        }
                    } catch (SQLException ex) {
                        System.out.println("Database connection error: " + ex.getMessage());
                    }
                }
            }
        }
        // After removal, update the roster display
        rosterTab.refreshRoster();
        dispose(); // Close the dialog
    }

    private Player getPlayerByName(String name) {
        for (Player player : playerList) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }
}
