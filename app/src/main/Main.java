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
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlayerDatabase playerDatabase = new PlayerDatabase();

            // Drop existing tables
            
            RosterController rosterController = new RosterController(playerDatabase);

            RosterTab rosterTab = new RosterTab(rosterController);
            StatsTab statsTab = new StatsTab(rosterController);
            PracticeStats practiceStats = new PracticeStats(rosterController);
            ArchiveTab archiveTab = new ArchiveTab(rosterController, rosterTab);

            rosterController.setRosterTab(rosterTab);
            rosterController.setStatsTab(statsTab);
            rosterController.setPracticeStats(practiceStats);
            rosterController.setArchiveTab(archiveTab);

            GUI dashboard = new GUI(rosterTab, statsTab, practiceStats, archiveTab);
        });
    }
}
