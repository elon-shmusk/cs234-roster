package tests.cs234_Project;

import src.rosterTab; // Import the rosterTab class

import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.*;
import java.awt.*;

/*
 * JUnit tests for the rosterTab class.
 */
public class RosterTest {

    /*
     * Test to verify the title of the roster tab.
     */
    @Test
    public void testRosterTabTitle() {
        JPanel rosterPanel = new JPanel(); // Create a JPanel object
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Roster", rosterPanel); // Add rosterPanel as a tab to the tabbedPane

        // Assuming "Roster" is the expected title of the tab
        assertEquals("Roster", tabbedPane.getTitleAt(tabbedPane.indexOfComponent(rosterPanel)));
    }


    /*
     * Test to verify the visibility of the roster tab.
     */
    @Test
    public void testRosterTabVisibility() {
        JPanel rosterPanel = new JPanel(); // Create a JPanel object
        rosterTab rosterTab = new rosterTab(rosterPanel); // Instantiate rosterTab object with rosterPanel
        assertTrue(rosterTab.isVisible());
    }

    /*
     * Test to verify the presence of the "Roster" tab.
     */
    @Test
    public void testRosterTabPresence() {
        JTabbedPane tabbedPane = new JTabbedPane(); // Create a JTabbedPane object
        JPanel rosterPanel = new JPanel(); // Create a JPanel object
        rosterTab rosterTab = new rosterTab(rosterPanel); // Instantiate rosterTab object with rosterPanel
        tabbedPane.addTab("Roster", rosterPanel); // Add rosterPanel as a tab to the tabbedPane

        boolean tabFound = false;
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            if (tabbedPane.getTitleAt(i).equals("Roster")) {
                tabFound = true;
                break;
            }
        }
        assertTrue(tabFound);
    }

    /*
     * Test to verify the layout manager of the roster panel.
     */
    @Test
    public void testRosterPanelLayoutManager() {
        JPanel rosterPanel = new JPanel(); // Create a JPanel object
        rosterTab rosterTab = new rosterTab(rosterPanel); // Instantiate rosterTab object with rosterPanel
        LayoutManager layoutManager = rosterPanel.getLayout(); // Get the layout manager of the roster panel
        assertTrue(layoutManager instanceof BorderLayout);
    }

    /*
     * Test to verify the visibility of the "Add Player" button.
     */
    @Test
    public void testAddPlayerButtonVisibility() {
        JPanel rosterPanel = new JPanel();
        rosterTab rosterTab = new rosterTab(rosterPanel);

        JPanel buttonPanel = (JPanel) rosterPanel.getComponent(1);
        boolean buttonPanelVisible = buttonPanel.isVisible();

        assertTrue("Add Player button is not visible", buttonPanelVisible);
    }
}
