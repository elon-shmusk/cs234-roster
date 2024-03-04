/*
 * Purpose: Tests the title, visibility, presence, layout manager, and the visibility of the roster tab.
 * Course: CS 234
 * Instructor: Greg Schaper
 * Contributors: Deni Velasquez
 * Project: Term Team Project
 */
package tests.cs234_Project;

import myproject.src.main.rosterTab;
import org.junit.Test;

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
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Roster", rosterPanel);

        assertEquals("Roster", tabbedPane.getTitleAt(tabbedPane.indexOfComponent(rosterPanel)));
    }


    /*
     * Test to verify the visibility of the roster tab.
     */
    @Test
    public void testRosterTabVisibility() {
        JPanel rosterPanel = new JPanel();
        rosterTab rosterTab = new rosterTab(rosterPanel);
        assertTrue(rosterTab.isVisible());
    }

    /*
     * Test to verify the presence of the "Roster" tab.
     */
    @Test
    public void testRosterTabPresence() {
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel rosterPanel = new JPanel();
        rosterTab rosterTab = new rosterTab(rosterPanel);
        tabbedPane.addTab("Roster", rosterPanel);

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
        JPanel rosterPanel = new JPanel();
        rosterTab rosterTab = new rosterTab(rosterPanel);
        LayoutManager layoutManager = rosterPanel.getLayout();
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