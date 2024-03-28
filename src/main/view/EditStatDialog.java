package src.main.view;

import src.main.controller.RosterController;
import src.main.model.Player;
import src.main.model.PlayerDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditStatDialog extends JDialog {
    private RosterController RosterController;
    private Player chosenPlayer;
    private JTextField freeThrowsField;
    private JTextField freeThrowsPercentageField;
    private JTextField threePointersField;
    private JTextField threePointersPercentageField;
    private StatsTab statsTab;
    private Integer id;
    public EditStatDialog(StatsTab statsTab, RosterController RosterController, Player chosenPlayer) {
        super();
        setTitle("Edit Stats");
        setSize(500, 400);
        setLocationRelativeTo(statsTab);
        setLayout(new BorderLayout());

        this.chosenPlayer = chosenPlayer;
        this.RosterController = RosterController;
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

        JLabel freeThrowsPercentageLabel = new JLabel("Free Throws %:");
        freeThrowsPercentageLabel.setFont(font);
        freeThrowsPercentageField = new JTextField();
        freeThrowsPercentageField.setFont(font);
        statsPanel.add(freeThrowsPercentageLabel);
        statsPanel.add(freeThrowsPercentageField);

        JLabel threePointersLabel = new JLabel("Three Pointers:");
        threePointersLabel.setFont(font);
        threePointersField = new JTextField();
        threePointersField.setFont(font);
        statsPanel.add(threePointersLabel);
        statsPanel.add(threePointersField);

        JLabel threePointersPercentageLabel = new JLabel("Three Pointers %:");
        threePointersPercentageLabel.setFont(font);
        threePointersPercentageField = new JTextField();
        threePointersPercentageField.setFont(font);
        statsPanel.add(threePointersPercentageLabel);
        statsPanel.add(threePointersPercentageField);

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
            double freeThrowsPercentage;
            int threePointers;
            double threePointersPercentage;

            try {
                freeThrows = Integer.parseInt(freeThrowsField.getText());
            }
            catch(NumberFormatException ex){
                freeThrows = 0;}

            try {
                freeThrowsPercentage = Double.parseDouble(freeThrowsPercentageField.getText());
            }
            catch(NumberFormatException ex){
                freeThrowsPercentage = 0;}

            try {
                threePointers = Integer.parseInt(threePointersField.getText());
            }
            catch(NumberFormatException ex){
                threePointers = 0;
            }

            try {
                threePointersPercentage = Double.parseDouble(threePointersPercentageField.getText());
            }
            catch(NumberFormatException ex){
                threePointersPercentage = 0;}



            if(RosterController.getFreeThrowsMade(id) != freeThrows && freeThrows != 0 && !freeThrowsField.getText().isEmpty())
                RosterController.setFreeThrowsMade(id, freeThrows);

            if(RosterController.getFreeThrowsPercentage(id) != freeThrowsPercentage && freeThrowsPercentage != 0 && !freeThrowsPercentageField.getText().isEmpty())
                RosterController.setFreeThrowsPercentage(id, freeThrowsPercentage);

            if(RosterController.getThreePointsMade(id) != threePointers && threePointers != 0 && !threePointersField.getText().isEmpty())
                RosterController.setThreePointsMade(id, threePointers);

            if(RosterController.getThreePointsPercentage(id) != threePointersPercentage && threePointersPercentage != 0 && !threePointersPercentageField.getText().isEmpty())
                RosterController.setThreePointsPercentage(id, threePointersPercentage);


            dispose();

            statsTab.refreshStats();
        }
    }

