package src;

import javax.swing.*;
import java.awt.*;

public class Remove_Player extends JPanel{
    private JCheckBox player;
    public Remove_Player(JFrame parent) {
        JDialog remove_window = new JDialog(parent, "Input Dialog", true);
        remove_window.setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Remove Player"));
        add(new JLabel("Select a player to remove:"));

        JPanel checkPanel = new JPanel();

        player = new JCheckBox("Player: ");

        add(player);

    }

        public boolean player_to_be_removed()
        {
            if (player.isSelected())
                return true;
            else
                return false;

        }





}
