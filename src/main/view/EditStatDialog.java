package src.main.view;

import src.main.controller.RosterController;
import src.main.model.Player;
import src.main.model.PlayerDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditStatDialog extends JDialog {
    private PlayerDatabase playerDatabase;
    private Player chosenPlayer;
    private JTextField freeThrowsField;
    private JTextField freeThrowsPercentageField;
    private JTextField threePointersField;
    private JTextField threePointersPercentageField;
    private Integer id;
    public EditStatDialog(StatsTab statsTab, RosterController RosterController, Player chosenPlayer) {
        super();
        setTitle("Edit Stats");
        setSize(300, 200);
        setLocationRelativeTo(statsTab);
        setLayout(new BorderLayout());

        this.chosenPlayer = chosenPlayer;
        id = chosenPlayer.getId();

        JPanel statsPanel = new JPanel();
        statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));
        statsPanel.setLayout(new GridLayout(0, 2));

        JLabel freeThrowsLabel = new JLabel("Free Throws:");
        freeThrowsField = new JTextField();
        statsPanel.add(freeThrowsLabel);
        statsPanel.add(freeThrowsField);

        JLabel freeThrowsPercentageLabel = new JLabel("Free Throws Percentage:");
        freeThrowsPercentageField = new JTextField();
        statsPanel.add(freeThrowsPercentageLabel);
        statsPanel.add(freeThrowsPercentageField);

        JLabel threePointersLabel = new JLabel("Three Pointers:");
        threePointersField = new JTextField();
        statsPanel.add(threePointersLabel);
        statsPanel.add(threePointersField);

        JLabel threePointersPercentageLabel = new JLabel("Three Pointers Percentage:");
        threePointersPercentageField = new JTextField();
        statsPanel.add(threePointersPercentageLabel);
        statsPanel.add(threePointersPercentageField);

        JPanel buttonPanel = new JPanel();
        JButton updateButton = new JButton("Update Stats");
        JButton cancelButton = new JButton("Cancel");

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
                editPlayerStats(chosenPlayer, RosterController, statsTab);
            }
        });

        add(statsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

        public void editPlayerStats(Player chosenPlayer,RosterController RosterController, StatsTab statsTab) {
            id = chosenPlayer.getId();
            int freeThrows;
            double freeThrowsPercentage;
            int threePointers;
            double threePointersPercentage;

            try{
                freeThrows = Integer.parseInt(freeThrowsField.getText());
                freeThrowsPercentage = Double.parseDouble(freeThrowsPercentageField.getText());
                threePointers = Integer.parseInt(threePointersField.getText());
                threePointersPercentage = Double.parseDouble(threePointersPercentageField.getText());}
            catch(NumberFormatException ex)
            {
                freeThrows = 0;
                freeThrowsPercentage = 0;
                threePointers = 0;
                threePointersPercentage = 0;
            }

            if(RosterController.getFreeThrowsMade(id) != freeThrows && playerDatabase.getFreeThrowsMade(id) != 0)
            {
                RosterController.setFreeThrowsMade(id, freeThrows);
            }

            if(RosterController.getFreeThrowsPercentage(id) != freeThrowsPercentage && playerDatabase.getFreeThrowsPercentage(id) != 0)
            {
                RosterController.setFreeThrowsPercentage(id, freeThrowsPercentage);
            }

            if(RosterController.getThreePointsMade(id) != threePointers && playerDatabase.getThreePointsMade(id) != 0)
            {
                RosterController.setThreePointsMade(id, threePointers);
            }

            if(RosterController.getThreePointsPercentage(id) != threePointersPercentage && playerDatabase.getThreePointsPercentage(id) != 0)
            {
                RosterController.setThreePointsPercentage(id, threePointersPercentage);
            }

            dispose();

            statsTab.refreshStats();
        }
    }

