package src;

import javax.swing.*;
import java.awt.*;

/**
 * A Java Swing application to display the Moravian University Women's Basketball Roster
 * using JTabbedPane, JPanel, JTextArea, and JScrollPane.
 * /*
 *  * Code generated with assistance from ChatGPT.
 *
 */
public class rosterTab extends JFrame {

    /**
     * Constructs the Roster frame.
     * @author Fernando Peralta Castro
     */

    private static final long serialVersionUID = 1L;

    public rosterTab() {
        setTitle("Moravian University Women's Basketball Roster");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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

        JTable table = new JTable(rosterData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create a panel for roster information
        JPanel rosterPanel = new JPanel(new BorderLayout());
        rosterPanel.add(scrollPane, BorderLayout.CENTER); // Add the JTable to the roster panel

        // Add the roster panel to the tabbed pane
        tabbedPane.addTab("Roster", rosterPanel);

        // Add the tabbed pane to the frame
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Set frame size and make it visible
        setSize(800, 600);
        setVisible(true);

        // Add another tab for team stats
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("This is tab 2"));
        tabbedPane.addTab("Team Stats", panel2);

        //
    }
}
