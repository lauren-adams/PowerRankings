package edu.baylor.ecs.csi3471.groupProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

//FIXME MAYBE IMPLEMENT TO ALSO DO FORGOT PASSWORD
public class ForgotUsername extends JPanel {
    public ForgotUsername(){

        //JFrame frame = new JFrame("Forgot Username");				// creating instance of JFrame
        this.setSize(500, 500);									// 500 width and 500 height
        this.setLayout(new GridLayout(3, 2));
        this.setVisible(true);										// making the frame visible

        final JLabel label = new JLabel("Please enter Email:");
        label.setBounds(100, 110, 100, 40);

        final JLabel email = new JLabel("");
        final JTextField usernameField = new JTextField(30);
        final JTextField emailField = new JTextField(30);
        final JLabel username = new JLabel("");
        username.setBounds(100, 100, 100, 40);
        JButton submit = new JButton("Submit");
        submit.setBounds(50, 70, 50, 40);

        this.add(new JLabel("Email: "));
        this.add(emailField);
        //this.add(username);



        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ForgotDAO f = new ForgotDAO();
            	username.setText(f.findUsername(emailField.getText()));
            }
        });


        this.add(submit);
        this.add(username);


    }



    protected void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableFilterDemo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        //Create and set up the content pane.
        ForgotUsername newContentPane = new ForgotUsername();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}

