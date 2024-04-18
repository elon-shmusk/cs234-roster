package src.main;

import src.main.view.*;
import src.main.controller.*;
import src.main.model.*;

import javax.swing.*;


/**
 * The entry point of the basketball roster application.
 * This class initializes the application and sets up the main GUI components.
 */
public class Main {

    /**
     * The main method that sets up the application's GUI and controllers.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlayerDatabase playerDatabase = new PlayerDatabase();
            RosterController rosterController = new RosterController(playerDatabase); // Pass null for now


            RosterTab rosterTab = new RosterTab(rosterController); // Initialize RosterTab with the controller
            StatsTab statsTab = new StatsTab(rosterController); // Initialize StatsTab with the controller
            PracticeStats practiceStats = new PracticeStats(rosterController); // Initialize PracticeStats with the controller
            ArchiveTab archiveTab = new ArchiveTab(rosterController);

            // Set the rosterController for the RosterTab
            rosterController.setRosterTab(rosterTab);
            rosterController.setStatsTab(statsTab);

            // remove tables


           

            GUI dashboard = new GUI(rosterTab, statsTab,practiceStats, archiveTab); // Pass the rosterController and rosterTab
        });
    }
}
