package cs234_Project;

/**
 * Code generated with assistance from ChatGPT.
 */

import org.junit.Test;
import src.GUI;
import src.rosterTab;

import static org.junit.Assert.*;

/**
 * JUnit tests for the BasketballRosterTab class.
 */
public class RosterTest {

    /**
     * Test to verify the title of the roster tab.
     */
    @Test
    public void testRosterTabTitle() {
        GUI rosterTab = new GUI();
        assertEquals("Moravian University Women's Basketball", rosterTab.getTitle());
    }

    /**
     * Test to verify the visibility of the roster tab.
     */
    @Test
    public void testRosterTabVisibility() {
        GUI rosterTab = new GUI();
        assertTrue(rosterTab.isVisible());
    }

    /**
     * Test to verify the size of the roster tab.
     */
    @Test
    public void testRosterTabSize() {
        GUI rosterTab = new GUI();
        assertEquals(800, rosterTab.getWidth());
        assertEquals(400, rosterTab.getHeight());
    }
}