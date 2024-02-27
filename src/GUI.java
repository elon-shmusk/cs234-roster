package src;

import src.Add_Player;
import src.Remove_Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI
{
    public GUI(){
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
                Add_Player add_window = new Add_Player(panel1);
                add_window.setVisible(true);
            }
        });

        // Remove Player button
        JButton removeButton = new JButton("Remove Player");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a JDialog to show as a popup window
               Remove_Player remove_window = new Remove_Player(panel1);
                remove_window.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(add_button);
        buttonPanel.add(removeButton);
        panel1.add(buttonPanel, BorderLayout.SOUTH);

        //

}
}

