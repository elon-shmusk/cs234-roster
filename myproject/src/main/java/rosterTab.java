package main.java;//package myproject.src.main.java;

//import myproject.src.main.java.Add_Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A Java Swing application to display the Moravian University Women's Basketball Roster
 * using JTabbedPane, JPanel, JTextArea, and JScrollPane.
 */
public class rosterTab extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs the Roster frame.
     * @author Fernando Peralta Castro
     */
    public rosterTab() {
        setTitle("Moravian University Women's Basketball Roster");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Player");

        // Add action listener to the button to open the dialog
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of the Add_Player dialog
                JPanel panel = new JPanel(); // Create a JPanel
                Add_Player addPlayerDialog = new Add_Player(panel);
                addPlayerDialog.setVisible(true);
            }
        });

        // Add the button to the panel
        buttonPanel.add(addButton, BorderLayout.CENTER);

        // Add the panel to the frame's south region
        add(buttonPanel, BorderLayout.SOUTH);

        // Roster data
        String[][] rosterData = {
                {"0", "Emma Altmire", "Fr.", "G", "5-4", "Easton, Pa. / Notre Dame-Green Pond"},
                {"1", "Sam Osorio", "Sr.", "F", "5-9", "Kearny, N.J. / Kearny"},
                {"2", "Natalie Osorio", "Fr.", "G/F", "5-6", "Kearny, N.J. / Kearny"},
                {"4", "Jayda Cartagena", "So.", "G", "5-4", "Allentown, Pa. / Dieruff"},
                {"10", "Brielle Guarente", "Jr.", "F", "5-10", "West Caldwell, N.J. / James Caldwell"},
                {"11", "Yarian Fernandez", "Fr.", "G", "5-7", "Bethlehem, Pa. / Bethlehem Catholic"},
                {"12", "Mikayla Anderson", "Jr.", "G", "5-6", "Garnet Valley, Pa. / Garnet Valley"},
                {"14", "Reganne Flannery", "Sr.", "G", "5-6", "Lansdale, Pa. / Gwynedd Mercy Academy"},
                {"15", "N'dera Francis", "Fr.", "G", "5-6", "Brooklyn, N.Y. / Summit Academy"},
                {"20", "Lanie Herbert", "Fr.", "C/F", "6-1", "Jeffersonville, N.Y. / Sullivan West"},
                {"21", "Grace Steffen", "Fr.", "C/F", "5-11", "Hawley, Pa. / Wallenpaupack Area"},
                {"22", "Juliana Vassallo", "So.", "G", "5-7", "River Vale, N.J. / Pascack Valley"},
                {"23", "Olivia Fanning", "Fr.", "F", "5-10", "North Haledon, N.J. / Mary Help of Christians"},
                {"24", "Lizzie Lustig", "Fr.", "G/F", "5-9", "Newton, Pa. / Council Rock North"},
                {"25", "Bellisima Lew", "Fr.", "F", "5-10", "Maywood, N.J. / Paramus Catholic"},
                {"30", "Khadije Fahie", "Fr.", "G", "5-8", "Miami, Fla. / IMG Academy"}
        };

        String[] columnNames = {"No.", "Name", "Cl.", "Pos.", "Ht.", "Hometown/High School"};

        // Create a table with roster data
        JTable table = new JTable(rosterData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        // Create a panel for roster information
        JPanel rosterPanel = new JPanel(new BorderLayout());
        rosterPanel.add(scrollPane, BorderLayout.CENTER); // Add the JTable to the roster panel

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add the roster panel to the tabbed pane
        tabbedPane.addTab("Roster", rosterPanel);

        // Add the tabbed pane to the frame
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Set frame size and make it visible
        setSize(1500, 600);
        setVisible(true);

        // Add another tab for team stats
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("This is tab 2"));
        tabbedPane.addTab("Team Stats", panel2);
    }
}