import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI
{
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame();

        // Create and add tabs
        JPanel panel1 = new JPanel();
        frame.setTitle("Player Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 550);
        frame.setLayout(new BorderLayout());

        // Create a JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Table data and column names
        String[] columnNames = {"Number", "Name", "Position", "FTM %", "3FGM %"};
        Object[][] data = {{"", "", "", "", ""}};

        // Create a table model
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Create the table
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel1.add(scrollPane, BorderLayout.CENTER);

        // Add Player button
        JButton addButton = new JButton("Add Player");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a JDialog to show as a popup window
                JDialog dialog = new JDialog(frame, "Input Dialog", true);
                dialog.setSize(300, 200);
                dialog.setLayout(new GridBagLayout());

                // Add components to the dialog for user input
                JTextField textField = new JTextField(20);
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Retrieve user input when OK button is clicked

                    }
                });



                JPanel panel1 = new JPanel();
                panel1.setLayout(new GridBagLayout());
                GridBagConstraints constraint = new GridBagConstraints();

                constraint.insets = new Insets(10, 10, 1, 1);
                constraint.anchor = GridBagConstraints.LINE_START;
                constraint.gridx = 0;
                constraint.gridy = 0;
                constraint.gridwidth = 1;
                panel1.add(new JLabel("Enter Player Name: "));

                constraint = new GridBagConstraints();
                constraint.insets = new Insets(10, 1, 1, 10);
                constraint.anchor = GridBagConstraints.HORIZONTAL;
                constraint.gridx = 1;
                constraint.gridy = 0;
                constraint.gridwidth = 1;
                panel1.add(textField, constraint);

                constraint = new GridBagConstraints();
                constraint.insets = new Insets(10, 10, 1, 1);
                constraint.anchor = GridBagConstraints.LINE_START;
                constraint.gridx = 2;
                constraint.gridy = 0;
                constraint.gridwidth = 1;
                constraint.weighty = 1;
                panel1.add(new JLabel("Enter Player Number: "));

                constraint = new GridBagConstraints();
                constraint.insets = new Insets(10, 10, 1, 10);
                constraint.anchor = GridBagConstraints.HORIZONTAL;
                constraint.gridx = 3;
                constraint.gridy = 0;
                constraint.gridwidth = 1;
                constraint.weighty = 1;
                panel1.add(textField);

                constraint = new GridBagConstraints();
                constraint.insets = new Insets(10, 10, 1, 1);
                constraint.anchor = GridBagConstraints.LINE_START;
                constraint.gridx = 4;
                constraint.gridy = 1;
                constraint.gridwidth = 1;
                panel1.add(okButton);

                dialog.add(panel1);
                dialog.pack();
                dialog.setVisible(true);
            }
        });



        // Remove Player button
        JButton removeButton = new JButton("Remove Player");
        removeButton.removeActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a JDialog to show as a popup window
                JDialog dialog = new JDialog(frame, "Input Dialog", true);
                dialog.setLayout(new BorderLayout());

                // Add components to the dialog for user input
                JTextField textField = new JTextField(20);
                JButton okButton = new JButton("OK");
                okButton.removeActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Retrieve user input when OK button is clicked
                        String userInput = textField.getText();
                        JOptionPane.showMessageDialog(frame, "You entered: " + userInput);
                        dialog.dispose(); // Close the dialog
                    }
                });

                JPanel panel = new JPanel();
                panel.add(new JLabel("Enter input: "));
                panel.add(textField);
                panel.add(okButton);

                dialog.add(panel, BorderLayout.CENTER);
                dialog.pack();
                dialog.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        panel1.add(buttonPanel, BorderLayout.SOUTH);


        tabbedPane.addTab("Roster", panel1);

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("This is tab 2"));
        tabbedPane.addTab("Team Stats", panel2);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel3.add(new JLabel("This is tab 3"));
        panel3.add(new Add_Player());
        tabbedPane.addTab("Add/Remove Player", panel3);




        // Add the tabbed pane to the frame
        frame.add(tabbedPane);

        // Display the frame
        frame.setVisible(true);
    }
}

