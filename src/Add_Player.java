import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Add_Player extends JButton
{
    public Add_Player()
    {
        JFrame frame = new JFrame();
        frame.setTitle("Add/Remove Player");
        frame.setLayout(new GridBagLayout());

        JTextField num_text_Field = new JTextField(20);
        JTextField name_text_Field = new JTextField(20);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve user input when OK button is clicked

            }
        });

        GridBagConstraints constraint = new GridBagConstraints();

        constraint.insets = new Insets(10, 10, 1, 1);
        constraint.anchor = GridBagConstraints.LINE_START;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.gridwidth = 1;
        add(new JLabel("Enter Player Name: "));

        constraint = new GridBagConstraints();
        constraint.insets = new Insets(10, 1, 1, 10);
        constraint.anchor = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.gridwidth = 1;


        constraint = new GridBagConstraints();
        constraint.insets = new Insets(10, 10, 1, 1);
        constraint.anchor = GridBagConstraints.LINE_START;
        constraint.gridx = 2;
        constraint.gridy = 0;
        constraint.gridwidth = 1;
        constraint.weighty = 1;


        constraint = new GridBagConstraints();
        constraint.insets = new Insets(10, 10, 1, 10);
        constraint.anchor = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 3;
        constraint.gridy = 0;
        constraint.gridwidth = 1;
        constraint.weighty = 1;


        constraint = new GridBagConstraints();
        constraint.insets = new Insets(10, 10, 1, 1);
        constraint.anchor = GridBagConstraints.LINE_START;
        constraint.gridx = 4;
        constraint.gridy = 1;
        constraint.gridwidth = 1;

        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter Player Name: "));
        panel.add(name_text_Field);
        panel.add(new JLabel("Enter Player Number: "));
        panel.add(num_text_Field);
        panel.add(okButton);



        frame.pack();
        setVisible(true);


    }


}
