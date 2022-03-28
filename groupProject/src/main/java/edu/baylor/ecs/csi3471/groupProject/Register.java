package edu.baylor.ecs.csi3471.groupProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Register {

    void beginRegistration() {
        Database d = new Database();
    	
    	JFrame RegisterPage = new JFrame("Register");						// register page frame with name
        RegisterPage.setExtendedState(JFrame.MAXIMIZED_BOTH);				// make frame full screen

        JMenuBar registerMenuBar = new JMenuBar();    						// title menu bar
        registerMenuBar.setFont(new Font("sans-serif", Font.PLAIN, 12));
        registerMenuBar.setOpaque(true);									// make opaque
        registerMenuBar.setPreferredSize(new Dimension(200, 200));			// second parameter controls height

        JMenuItem title = new JMenuItem("REGISTRATION");					// title name
        title.setOpaque(true);												// make opaque
        title.setFont(new Font("roboto condensed", Font.PLAIN, 50));		// font of title
        title.setBackground(Color.BLACK);									// make title background black
        registerMenuBar.add(title);											// add title to title menu bar

        final RegisterForm registerForm = new RegisterForm();
        registerForm.setPreferredSize(new Dimension(2000, 180));
        registerForm.setLayout(new GridLayout(5, 5));						// sets the number of rows, columns

        JButton submitButton = new JButton("Submit");

        // confirms the users input
        submitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent submit) {
                try {
                	registerForm.getUserNameField().getText();
                	//validateUsername(registerForm.getUserNameField().getText());
                	
                	registerForm.getPasswordField().getText();
                	//validatePassword(loginForm.getPasswordField().getText());
                } catch(NullPointerException e) {
                    JOptionPane.showMessageDialog(registerForm,"Invalid username or password","ERROR", JOptionPane.ERROR_MESSAGE);
                }
            	/*boolean fail = false;

                if (registerForm.getUserNameField().getText().isEmpty()) {
                    fail = true;
                }
                else {
                    //validateUsername(registerForm.getUserNameField().getText());
                }
                if(registerForm.getPasswordField().getText().isEmpty()) {
                    fail = true;
                }
                else {
                    //validatePassword(loginForm.getPasswordField().getText());
                }

                if(fail) {
                    JOptionPane.showMessageDialog(registerForm,"Invalid username or password","ERROR", JOptionPane.ERROR_MESSAGE);
                }*/
            }
        });

        registerForm.add(submitButton);
        RegisterPage.setJMenuBar(registerMenuBar);
        RegisterPage.getContentPane().add(registerForm, BorderLayout.CENTER);
        RegisterPage.setVisible(true);

    }

    void setAge() {

    }

    void setEmail() {

    }

    String getUniqueId() {
        String id = "";
        return id;
    }




}
