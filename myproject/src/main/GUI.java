/*
Purpose: This class is used as the main GUI for the project. It creates a tabbed pane with two tabs, one for the roster
and one for the team stats.
Course: CS 234
Instructor: Greg Schaper
contributors: Samuel Cadiz
Project: Term Team Project
 */
package myproject.src.main;

import myproject.src.main.rosterTab;

import javax.swing.*;

public class GUI extends JFrame
{
    /**
     * Constructor for the GUI class.
     */
    public GUI() {
        JFrame frame = new JFrame();

        frame.setTitle("Moravian University Women's Basketball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        // JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

//        // Table data and column names
        JPanel roster_Panel = new JPanel();
        rosterTab roster = new rosterTab(roster_Panel); // Create a new rosterTab object
        tabbedPane.addTab("Roster", roster_Panel);

        // Will show team stats and trends *NOT FOR SPRINT 1*
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("This is tab 2"));
        tabbedPane.addTab("Team Stats", panel2);





        // Add the tabbed pane to the frame
        frame.add(tabbedPane);

        // Display the frame
        frame.setVisible(true);

}
}

