package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.PLAIN;

public class LibraryGUI extends JFrame implements ActionListener {

    //This is currently all a test, this may very likely not be used.


    Border border = BorderFactory.createLineBorder(Color.black,3);
    JButton openListOfTheorem;
    JButton openListOfEquation;
    JButton openListOfRequest;

    LibraryGUI() {

        this.setTitle("LibraryApplication");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(1024,768);
        this.setVisible(true);

        JLabel welcomeText = new JLabel();
        welcomeText.setText("Welcome To The Library!");
        welcomeText.setFont(new Font("Computer Modern", Font.PLAIN, 60));
        welcomeText.setBorder(border);
        welcomeText.setVerticalAlignment(JLabel.TOP);
        welcomeText.setHorizontalAlignment(JLabel.CENTER);
        welcomeText.setBounds(0,0,1024, 768);
        welcomeText.setBackground(Color.white);
        welcomeText.setOpaque(true);

        JLabel instructionsText = new JLabel();
        instructionsText.setText("Please click on the following library you would like to enter!");
        instructionsText.setFont(new Font("Computer Modern", Font.PLAIN, 20));
        instructionsText.setVerticalTextPosition(JLabel.CENTER);
        instructionsText.setHorizontalTextPosition(JLabel.CENTER);
        instructionsText.setBorder(border);
        instructionsText.setVerticalAlignment(JLabel.TOP);
        instructionsText.setHorizontalAlignment(JLabel.CENTER);
        instructionsText.setBounds(0,192,1024, 768);
        instructionsText.setBackground(Color.white);
        instructionsText.setOpaque(true);

        openListOfTheorem = new JButton();
        openListOfTheorem.setBounds(0, 384, 341, 192);
        openListOfTheorem.addActionListener(this);
        openListOfTheorem.setText("Open Theorem Library!");
        openListOfTheorem.setFocusable(true);

        openListOfEquation = new JButton();
        openListOfEquation.setBounds(342, 384, 341, 192);
        openListOfEquation.addActionListener(this);
        openListOfEquation.setText("Open Equation Library!");
        openListOfEquation.setFocusable(true);

        openListOfRequest = new JButton();
        openListOfRequest.setBounds(683, 384, 341, 192);
        openListOfRequest.addActionListener(this);
        openListOfRequest.setText("Open Request Library!");
        openListOfRequest.setFocusable(true);

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.gray);
        buttons.setBounds(0, 0, 1024,768);
        buttons.setLayout(new GridLayout(1,3,0,0));
        buttons.add(openListOfTheorem);
        buttons.add(openListOfEquation);
        buttons.add(openListOfRequest);


        JPanel welcomeScreen = new JPanel();
        welcomeScreen.setLayout(new GridLayout(3,1,0,0));
        welcomeScreen.setBackground(Color.gray);
        welcomeScreen.setBounds(0, 0, 1024,768);
        welcomeScreen.add(welcomeText);
        welcomeScreen.add(instructionsText);
        welcomeScreen.add(buttons);


        this.add(welcomeScreen);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openListOfTheorem) {
            System.out.print("opening list of theorems");
        }

        if (e.getSource() == openListOfEquation) {
            System.out.print("opening list of equations");
        }

        if (e.getSource() == openListOfRequest) {
            System.out.print("opening list of requests");
        }
    }

}
