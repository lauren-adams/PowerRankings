package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Persistence.ForgotDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPassword extends JPanel {
    public ForgotPassword(){

        //JFrame frame = new JFrame("Forgot Username");				// creating instance of JFrame
        this.setSize(500, 500);									// 500 width and 500 height
        this.setLayout(new GridLayout(3, 2));
        this.setVisible(true);										// making the frame visible

        final JLabel label = new JLabel("Please enter Username:");
        label.setBounds(100, 110, 100, 40);

        final JLabel user = new JLabel("");
        final JTextField usernameField = new JTextField(30);
        final JTextField userField = new JTextField(30);
        final JLabel password = new JLabel("");
        user.setBounds(100, 100, 100, 40);
        JButton submit = new JButton("Submit");
        submit.setBounds(50, 70, 50, 40);

        this.add(new JLabel("Username: "));
        this.add(userField);
        //this.add(username);



        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ForgotDAO f = new ForgotDAO();
            	password.setText(f.findPassword(userField.getText()));
            }
        });


        this.add(submit);
        this.add(password);


    }

    /**
     * creates the window for the forgotten password prompt
     */
    public void createAndShowGUI2() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableFilterDemo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        //Create and set up the content pane.
        ForgotPassword newContentPane = new ForgotPassword();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
