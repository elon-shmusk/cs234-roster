package tests.cs234_Project;

/*
 * Code generated with assistance from ChatGPT.
 */

import src.rosterTab; // Import the rosterTab class
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import static org.junit.Assert.*;

/*
 * JUnit tests for the rosterTab class.
 */
public class RosterTest {

    /*
     * Test to verify the title of the roster tab.
     */
    @Test
    public void testRosterTabTitle() {
        rosterTab rosterTab = new rosterTab(); // Instantiate rosterTab object
        assertEquals("Moravian University Women's Basketball Roster", rosterTab.getTitle());
    }

    /*
     * Test to verify the visibility of the roster tab.
     */
    @Test
    public void testRosterTabVisibility() {
        rosterTab rosterTab = new rosterTab(); // Instantiate rosterTab object
        assertTrue(rosterTab.isVisible());
    }

    /*
     * Test to verify the size of the roster tab.
     */
    @Test
    public void testRosterTabSize() {
        rosterTab rosterTab = new rosterTab(); // Instantiate rosterTab object
        assertEquals(800, rosterTab.getWidth());
        assertEquals(600, rosterTab.getHeight());
    }

    /*
     * Test to verify the presence of the "Add Player" button.
     */
    @Test
    public void testAddPlayerButtonPresence() {
        rosterTab rosterTab = new rosterTab(); // Instantiate rosterTab object
        JButton addButton = findAddPlayerButton(rosterTab);
        assertNotNull("Add Player button not found", addButton);
        assertEquals("Add Player", addButton.getText());
    }

    /*
     * Helper method to find the "Add Player" button.
     */
    private JButton findAddPlayerButton(rosterTab rosterTab) {
        for (Component component : rosterTab.getContentPane().getComponents()) {
            if (component instanceof JPanel panel) {
                for (Component innerComponent : panel.getComponents()) {
                    if (innerComponent instanceof JButton && ((JButton) innerComponent).getText().equals("Add Player")) {
                        return (JButton) innerComponent;
                    }
                }
            }
        }
        return null;
    }
}
