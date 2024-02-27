//package cs234_Project;
//
///**
// * Code generated with assistance from ChatGPT.
// */
//
//import org.junit.Test;
//import src.rosterTab;
//
//import static org.junit.Assert.*;
//
///**
// * JUnit tests for the BasketballRosterTab class.
// */
//public class RosterTest {
//
//    /**
//     * Test to verify the title of the roster tab.
//     */
//    @Test
//    public void testRosterTabTitle() {
//        rosterTab rosterTab = new rosterTab();
//        assertEquals("Moravian University Women's Basketball Roster", rosterTab.getTitle());
//    }
//
//    /**
//     * Test to verify the visibility of the roster tab.
//     */
//    @Test
//    public void testRosterTabVisibility() {
//        rosterTab rosterTab = new rosterTab();
//        assertTrue(rosterTab.isVisible());
//    }
//
//    /**
//     * Test to verify the size of the roster tab.
//     */
//    @Test
//    public void testRosterTabSize() {
//        rosterTab rosterTab = new rosterTab();
//        assertEquals(800, rosterTab.getWidth());
//        assertEquals(600, rosterTab.getHeight());
//    }
//}