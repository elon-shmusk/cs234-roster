package src;

import src.Add_Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI
{
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        // Create and add tabs
        JPanel panel1 = new JPanel();
        frame.setTitle("Player Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 550);
        frame.setLayout(new BorderLayout());

        // JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

//        // Table data and column names
//        String[] columnNames = {"Number", "Name", "Position", "FTM %", "3FGM %"};
//        Object[][] data = {{"", "", "", "", ""}};
//
//        // Create a table model
//        DefaultTableModel model = new DefaultTableModel(data, columnNames);
//
//        // Create the table *TO BE REPLACED*
//        JTable table = new JTable(model);
//        JScrollPane scrollPane = new JScrollPane(table);
//        panel1.add(scrollPane, BorderLayout.CENTER);

        //First tab will show the roster and possibly allow for adding and removing players

        // Add Player button
        JButton add_button = new JButton("Add Player");
        add_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Add_Player add_window = new Add_Player(frame);
                add_window.setVisible(true);
            }
        });

        // Remove Player button
        JButton removeButton = new JButton("Remove Player");
        removeButton.removeActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a JDialog to show as a popup window
                JDialog remove_window = new JDialog(frame, "Input Dialog", true);
                remove_window.setLayout(new BorderLayout());

                // Add components to the dialog for user input
                JTextField textField = new JTextField(20);
                JButton okButton = new JButton("OK");
                okButton.removeActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Retrieve user input when OK button is clicked
                        String userInput = textField.getText();
                        JOptionPane.showMessageDialog(frame, "You entered: " + userInput);
                        remove_window.dispose(); // Close the dialog
                    }
                });

                JPanel panel = new JPanel();
                panel.add(new JLabel("Enter input: "));
                panel.add(textField);
                panel.add(okButton);

                remove_window.add(panel, BorderLayout.CENTER);
                remove_window.pack();
                remove_window.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(add_button);
        buttonPanel.add(removeButton);
        panel1.add(buttonPanel, BorderLayout.SOUTH);


        tabbedPane.addTab("Roster", panel1);
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

