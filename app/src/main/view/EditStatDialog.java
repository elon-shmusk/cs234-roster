package src.main.view;

import src.main.controller.RosterController;
import src.main.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditStatDialog extends JDialog {
    private RosterController rosterController;
    private Player chosenPlayer;
    private JTextField freeThrowsField;
    private JTextField threePointersField;
    private StatsTab statsTab;
    private Integer id;

    public EditStatDialog(StatsTab statsTab, RosterController rosterController, Player chosenPlayer) {
        super();
        setTitle("Edit Stats");
        setSize(500, 300);
        setLocationRelativeTo(statsTab);
        setLayout(new BorderLayout());

        this.chosenPlayer = chosenPlayer;
        this.rosterController = rosterController;
        this.statsTab = statsTab;
        id = chosenPlayer.getId();

        Font font = new Font("Arial", Font.PLAIN, 25);

        JPanel statsPanel = new JPanel();
        statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));
        statsPanel.setLayout(new GridLayout(0, 2));

        JLabel freeThrowsLabel = new JLabel("Free Throws:");
        freeThrowsLabel.setFont(font);
        freeThrowsField = new JTextField();
        freeThrowsField.setFont(font);
        statsPanel.add(freeThrowsLabel);
        statsPanel.add(freeThrowsField);

        JLabel threePointersLabel = new JLabel("Three Pointers:");
        threePointersLabel.setFont(font);
        threePointersField = new JTextField();
        threePointersField.setFont(font);
        statsPanel.add(threePointersLabel);
        statsPanel.add(threePointersField);

        JPanel buttonPanel = new JPanel();
        JButton updateButton = new JButton("Update Stats");
        JButton cancelButton = new JButton("Cancel");

        updateButton.setFont(font);
        cancelButton.setFont(font);

        buttonPanel.add(updateButton, BorderLayout.SOUTH);
        buttonPanel.add(cancelButton, BorderLayout.SOUTH);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPlayerStats(chosenPlayer);
            }
        });

        add(statsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void editPlayerStats(Player chosenPlayer) {
        id = chosenPlayer.getId();
        int freeThrows;
        int threePointers;

        try {
            freeThrows = Integer.parseInt(freeThrowsField.getText());
        } catch (NumberFormatException ex) {
            freeThrows = 0;
        }

        try {
            threePointers = Integer.parseInt(threePointersField.getText());
        } catch (NumberFormatException ex) {
            threePointers = 0;
        }

        if (rosterController.getTotalFreeThrowsMade(id) != freeThrows && freeThrows >= 0 && !freeThrowsField.getText().isEmpty())
            rosterController.setTotalFreeThrowsMade(id, freeThrows);

        if (rosterController.getTotalThreePointsMade(id) != threePointers && threePointers >= 0 && !threePointersField.getText().isEmpty())
            rosterController.setTotalThreePointsMade(id, threePointers);

        dispose();
        statsTab.refreshStats();
    }

}
//'getFreeThrowsMade(int, java.sql.Date)' in 'src.main.controller.RosterController' cannot be applied to '(java.lang.Integer)'
//'getFreeThrowsMade(int, java.sql.Date)' in 'src.main.controller.RosterController' cannot be applied to '(java.lang.Integer)'
//'getThreePointsMade(int, java.sql.Date)' in 'src.main.controller.RosterController' cannot be applied to '(java.lang.Integer)'
//Cannot resolve method 'getDateIdForPlayer' in 'RosterController'