package tests.cs234_Project;

import src.Remove_Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 * JUnit tests for the Remove_Player class.
 */
public class RemovePlayerTest {

    /*
     * Test to verify the presence of the dialog.
     */
    @Test
    public void testDialogPresence() {
        Remove_Player removePlayerDialog = new Remove_Player(null); // Pass null as parent panel for simplicity
        assertNotNull("Remove Player dialog not instantiated", removePlayerDialog);
        assertTrue("Remove Player dialog is not visible", removePlayerDialog.isVisible());
        assertEquals("Remove Player", removePlayerDialog.getTitle());
    }

    /*
     * Test to verify the presence and text of the buttons.
     */
    @Test
    public void testButtonPresenceAndText() {
        Remove_Player removePlayerDialog = new Remove_Player(null); // Pass null as parent panel for simplicity
        Component[] components = removePlayerDialog.getContentPane().getComponents();
        JButton updateButton = null;
        JButton cancelButton = null;

        // Search for buttons in the content pane
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                Component[] innerComponents = panel.getComponents();
                for (Component innerComponent : innerComponents) {
                    if (innerComponent instanceof JButton) {
                        JButton button = (JButton) innerComponent;
                        if (button.getText().equals("Update Roster")) {
                            updateButton = button;
                        } else if (button.getText().equals("Cancel")) {
                            cancelButton = button;
                        }
                    }
                }
            }
        }

        // Assert that both buttons are found and have correct text
        assertNotNull("Update Roster button not found", updateButton);
        assertNotNull("Cancel button not found", cancelButton);
        assertEquals("Update Roster", updateButton.getText());
        assertEquals("Cancel", cancelButton.getText());
    }

    /*
     * Test to verify action listener on Cancel button.
     */
    @Test
    public void testCancelButtonActionListener() {
        Remove_Player removePlayerDialog = new Remove_Player(null); // Pass null as parent panel for simplicity
        JButton cancelButton = findCancelButton(removePlayerDialog);
        assertNotNull("Cancel button not found", cancelButton);

        // Simulate a click on the Cancel button
        cancelButton.doClick();

        assertFalse("Remove Player dialog is still visible after clicking Cancel button", removePlayerDialog.isVisible());
    }

    /*
     * Helper method to find the Cancel button.
     */
    private JButton findCancelButton(Remove_Player removePlayerDialog) {
        Component[] components = removePlayerDialog.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                Component[] innerComponents = panel.getComponents();
                for (Component innerComponent : innerComponents) {
                    if (innerComponent instanceof JButton && ((JButton) innerComponent).getText().equals("Cancel")) {
                        return (JButton) innerComponent;
                    }
                }
            }
        }
        return null;
    }
}
