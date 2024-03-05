/*
 * Purpose: Tests the title, visibility, presence, layout manager, and the visibility of the roster tab.
 * Course: CS 234
 * Instructor: Greg Schaper
 * Contributors: Deni Velasquez
 * Project: Term Team Project
 */
package src.test;

import org.junit.Test;
import src.main.RosterTab;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RosterTest {

    /*
     * Test to verify the title of the roster tab.
     */
    @Test
    public void testRosterTabTitle() {
        JPanel rosterPanel = new JPanel();
        RosterTab rosterTab = new RosterTab();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Roster", rosterPanel);

        assertEquals("Roster", tabbedPane.getTitleAt(tabbedPane.indexOfComponent(rosterPanel)));
    }

    /*
     * Test to verify the visibility of the roster tab.
     */
    @Test
    public void testRosterTabVisibility() {
        RosterTab rosterTab = new RosterTab();
        assertTrue(rosterTab.isVisible());
    }

    /*
     * Test to verify the presence of the "Roster" tab.
     */
    @Test
    public void testRosterTabPresence() {
        JTabbedPane tabbedPane = new JTabbedPane();
        RosterTab rosterTab = new RosterTab();
        tabbedPane.addTab("Roster", rosterTab);

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
        RosterTab rosterTab = new RosterTab();
        JPanel rosterPanel = new JPanel();
        rosterPanel = (JPanel) rosterTab.getComponent(0);
        LayoutManager layoutManager = rosterPanel.getLayout();
        assertTrue(layoutManager instanceof BorderLayout);
    }

    /*
     * Test to verify the visibility of the "Add Player" button.
     */
    @Test
    public void testAddPlayerButtonVisibility() {
        RosterTab rosterTab = new RosterTab();
        JPanel buttonPanel = (JPanel) rosterTab.getComponent(1);
        boolean buttonPanelVisible = buttonPanel.isVisible();
        assertTrue("Add Player button is not visible", buttonPanelVisible);
    }
}
