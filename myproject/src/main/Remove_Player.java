package src.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Remove_Player extends JDialog{
    private JCheckBox player1, player2, player3, player4, player5, player6, player7, player8, player9, player10,
            player11, player12, player13, player14, player15, player16;
    public Remove_Player(JPanel GUI) {
        super();
        setSize(400, 400);
        setLocationRelativeTo(GUI);
        setLayout(new BorderLayout());

        JPanel checkPanel = new JPanel(new GridLayout(8, 2));
        checkPanel.setBorder(BorderFactory.createTitledBorder("Select Player to Remove"));

        //Mock data
        player1 = new JCheckBox("#0: Emma Altmire");
        player2 = new JCheckBox("#1: Sam Osorio");
        player3 = new JCheckBox("#2: Natalie Osorio");
        player4 = new JCheckBox("#4: Jayda Cartagena");
        player5 = new JCheckBox("#10: Brielle Guarente");
        player6 = new JCheckBox("#11: Yarian Fernandez");
        player7 = new JCheckBox("#12: Mikayla Snderson");
        player8 = new JCheckBox("#14: Reganne Flannery");
        player9 = new JCheckBox("#15: N'dera Francis");
        player10 = new JCheckBox("#20: Lanie Herbert");
        player11 = new JCheckBox("#21: Grace Steffen");
        player12 = new JCheckBox("#22: Juliana Vassallo");
        player13 = new JCheckBox("#23: Olivia Fanning");
        player14 = new JCheckBox("#24: Lizzie Lustig");
        player15 = new JCheckBox("#25: Bellisina Lew");
        player16 = new JCheckBox("#30: Khadije Fahie");

        checkPanel.add(player1);
        checkPanel.add(player2);
        checkPanel.add(player3);
        checkPanel.add(player4);
        checkPanel.add(player5);
        checkPanel.add(player6);
        checkPanel.add(player7);
        checkPanel.add(player8);
        checkPanel.add(player9);
        checkPanel.add(player10);
        checkPanel.add(player11);
        checkPanel.add(player12);
        checkPanel.add(player13);
        checkPanel.add(player14);
        checkPanel.add(player15);
        checkPanel.add(player16);


        JPanel Button_Panel = new JPanel();
        //TODO: add action listener to update the roster connected to the database
        JButton Update_Button = new JButton("Update Roster");

        JButton Cancel_Button = new JButton("Cancel");
        Cancel_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the dialog without doing anything
                dispose();
            }
        });

        Button_Panel.add(Update_Button);
        Button_Panel.add(Cancel_Button);

        add(checkPanel, BorderLayout.CENTER);
        add(Button_Panel, BorderLayout.SOUTH);
        setVisible(true);
    }

//        private boolean player_to_be_removed()
//        {
//        }





}
