package src;

import src.Add_Player;
import src.Remove_Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame
{
    public GUI() {
        JFrame frame = new JFrame();

        // Create and add tabs

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

