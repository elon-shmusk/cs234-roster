package src.main.view;

import javax.swing.*;
import java.awt.*;
import src.main.controller.RosterController;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * Main frame of the application that holds all UI components.
 * This class initializes and displays the main window of the basketball roster application.
 */
public class GUI extends JFrame {
    private JTabbedPane tabbedPane;
    private RosterTab rosterTab;
    private StatsTab statsTab;
    private RosterController rosterController;
    /**
     * Constructs the main GUI frame with the given roster controller.
     * @param rosterController the controller that manages roster operations
     * @param rosterTab the tab component that displays the roster
     */
    public GUI(RosterController rosterController, RosterTab rosterTab, StatsTab statsTab) {
        this.rosterController = rosterController;
        this.rosterTab = rosterTab;
        this.statsTab = statsTab;
        initializeUI();
    }

    /**
     * Initializes the user interface components and layout.
     * This method sets up the frame's properties, such as size and title, and adds the necessary UI components.
     */
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("Moravian Girls Basketball Team");

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Roster", rosterTab);
        tabbedPane.addTab("Team Stats", statsTab);


        add(tabbedPane);
        setVisible(true);
    }

    /**
     * Retrieves the RosterTab component.
     * @return the RosterTab that displays the player roster
     */
    public RosterTab getRosterTab() {
        return rosterTab;
    }
}

