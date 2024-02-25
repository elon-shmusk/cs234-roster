package cs234_Project;

/**
 * Code generated with assistance from ChatGPT.
 */

import org.junit.Test;
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
        Roster rosterTab = new Roster();
        assertEquals("Moravian University Women's Basketball Roster", rosterTab.getTitle());
    }

    /**
     * Test to verify the visibility of the roster tab.
     */
    @Test
    public void testRosterTabVisibility() {
        Roster rosterTab = new Roster();
        assertTrue(rosterTab.isVisible());
    }

    /**
     * Test to verify the size of the roster tab.
     */
    @Test
    public void testRosterTabSize() {
        Roster rosterTab = new Roster();
        assertEquals(800, rosterTab.getWidth());
        assertEquals(600, rosterTab.getHeight());
    }
}