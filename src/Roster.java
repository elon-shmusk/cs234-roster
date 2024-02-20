package cs234_Project;

import javax.swing.*;
import java.awt.*;

/**
 * A Java Swing application to display the Moravian University Women's Basketball Roster
 * using JTabbedPane, JPanel, JTextArea, and JScrollPane.
 */
public class Roster extends JFrame {

    /**
     * Constructs the BasketballRosterTab frame.
     */
    public Roster() {
        setTitle("Moravian University Women's Basketball Roster");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create a panel for roster information
        JPanel rosterPanel = new JPanel(new BorderLayout());
        JTextArea rosterTextArea = new JTextArea();
        rosterTextArea.setEditable(false);
        JScrollPane rosterScrollPane = new JScrollPane(rosterTextArea);
        rosterPanel.add(rosterScrollPane);

        // Add roster information to the text area
        rosterTextArea.setText(
                "2023-24 Moravian University Women's Basketball Roster\n" +
                        "No.    Name                Cl.   Pos.   Ht.   Hometown/High School\n" +
                        "0      Emma Altmire        Fr.   G      5-4   Easton, Pa. / Notre Dame-Green Pond\n" +
                        "1      Sam Osorio          Sr.   F      5-9   Kearny, N.J. / Kearny\n" +
                        "2      Natalie Osorio      Fr.   G/F    5-6   Kearny, N.J. / Kearny\n" +
                        "4      Jayda Cartagena     So.   G      5-4   Allentown, Pa. / Dieruff\n" +
                        "10     Brielle Guarente    Jr.   F      5-10  West Caldwell, N.J. / James Caldwell\n" +
                        "11     Yarian Fernandez    Fr.   G      5-7   Bethlehem, Pa. / Bethlehem Catholic\n" +
                        "12     Mikayla Anderson    Jr.   G      5-6   Garnet Valley, Pa. / Garnet Valley\n" +
                        "14     Reganne Flannery    Sr.   G      5-6   Lansdale, Pa. / Gwynedd Mercy Academy\n" +
                        "15     N'dera Francis      Fr.   G      5-6   Brooklyn, N.Y. / Summit Academy\n" +
                        "20     Lanie Herbert       Fr.   C/F    6-1   Jeffersonville, N.Y. / Sullivan West\n" +
                        "21     Grace Steffen       Fr.   C/F    5-11  Hawley, Pa. / Wallenpaupack Area\n" +
                        "22     Juliana Vassallo    So.   G      5-7   River Vale, N.J. / Pascack Valley\n" +
                        "23     Olivia Fanning      Fr.   F      5-10  North Haledon, N.J. / Mary Help of Christians\n" +
                        "24     Lizzie Lustig       Fr.   G/F    5-9   Newton, Pa. / Council Rock North\n" +
                        "25     Bellisima Lew       Fr.   F      5-10  Maywood, N.J. / Paramus Catholic\n" +
                        "30     Khadije Fahie       Fr.   G      5-8   Miami, Fla. / IMG Academy\n" +
                        "Head Coach: Mary Beth Spirk (37th season, 640-327)\n" +
                        "Assistant Coaches: Amy Endler, Kelly Anthony, Don Weiss\n" +
                        "Captains: Reganne Flannery, Sam Osorio\n" +
                        "\n" +
                        "Roster as of October 10, 2023"
        );

        // Add the roster panel to the tabbed pane
        tabbedPane.addTab("Roster", rosterPanel);

        // Add the tabbed pane to the frame
        getContentPane().add(tabbedPane);

        // Set frame size and make it visible
        setSize(800, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        Roster roster = new Roster();
    }
}


