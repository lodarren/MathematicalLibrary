package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryGUI extends JFrame implements ActionListener {
    JButton button;

    public LibraryGUI(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Border border = BorderFactory.createLineBorder(Color.black,3);

        JLabel label = new JLabel();
        label.setText("Welcome To The Library");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setFont(new Font("Comic Sans", Font.PLAIN,20));
        label.setOpaque(true); //Changes the background
        label.setBorder(border);

        JPanel greetPanel = new JPanel();
        greetPanel.setBackground(Color.white);
        greetPanel.setBounds(0,0, 420, 50);

        button = new JButton();
        button.setBounds(200, 100, 50, 50);

        JFrame frame = new LibraryGUI("My Library");
        frame.setTitle("Main Menu");
        frame.setVisible(true); //Make sure you do this
        frame.setSize(420,420);
        frame.setResizable(false); //Allows you to change the size of the program
        frame.add(greetPanel);
        frame.add(label);
        frame.add(button);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.print("wee");
        }
    }
}
