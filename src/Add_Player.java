package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Add_Player extends JDialog
{
    private JTextField numberField, nameField, positionField, ftmField, threeFtgmField, yearField;


    public Add_Player(JPanel GUI)
    {
        super();
        setSize(300, 300);
        setLocationRelativeTo(GUI);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Player Information"));

        JLabel numberLabel = new JLabel("Number:");
        numberField = new JTextField(10);
        inputPanel.add(numberLabel);
        inputPanel.add(numberField);

        JLabel yearLabel = new JLabel("Year:");
        yearField = new JTextField(10);
        inputPanel.add(yearLabel);
        inputPanel.add(yearField);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(10);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel positionLabel = new JLabel("Position:");
        positionField = new JTextField(10);
        inputPanel.add(positionLabel);
        inputPanel.add(positionField);

        JLabel ftmLabel = new JLabel("FTM:");
        ftmField = new JTextField(10);
        inputPanel.add(ftmLabel);
        inputPanel.add(ftmField);

        JLabel threeFtgmLabel = new JLabel("3FTGM:");
        threeFtgmField = new JTextField(10);
        inputPanel.add(threeFtgmLabel);
        inputPanel.add(threeFtgmField);

        JPanel buttonPanel = new JPanel();
        //TODO: add action listener to update the roster connected to the database
        JButton okButton = new JButton("Update Roster");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve input values
                String number = numberField.getText();
                String year = yearField.getText();
                String name = nameField.getText();
                String position = positionField.getText();
                String ftm = ftmField.getText();
                String threeFtgm = threeFtgmField.getText();

                // Do something with the input (e.g., display or process)
                System.out.println("Number: " + number);
                System.out.println("Year: " + year);
                System.out.println("Name: " + name);
                System.out.println("Position: " + position);
                System.out.println("FTM: " + ftm);
                System.out.println("3FTGM: " + threeFtgm);

                // Close the dialog
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the dialog without doing anything
                dispose();
            }
        });

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
