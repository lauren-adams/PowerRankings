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
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainPage {
	
	public static void createGUI() {
		
		JFrame mainPage = new JFrame("Fantasy Fight Club");				// main page frame with frame name
		mainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// closes frame upon clicking 'x'
		mainPage.setExtendedState(JFrame.MAXIMIZED_BOTH);				// make frame full screen
		
		JMenuBar titleMenuBar = new JMenuBar();    						// title menu bar
		titleMenuBar.setFont(new Font("sans-serif", Font.PLAIN, 12));
	    titleMenuBar.setOpaque(true);									// make opaque
	    titleMenuBar.setPreferredSize(new Dimension(200, 200));			// second parameter controls height
	    	
	    JMenuItem title = new JMenuItem("FANTASY FIGHT CLUB");			// title name
	    title.setOpaque(true);											// make opaque
	    title.setFont(new Font("roboto condensed", Font.PLAIN, 50));	// font of title
	    title.setBackground(Color.BLACK);								// make title background black
	    titleMenuBar.add(title);										// add title to title menu bar
	                        
	    JPanel lowerPageLabel = new JPanel();							// lower half of the page
	    lowerPageLabel.setOpaque(true);
	    lowerPageLabel.setBackground(Color.WHITE);					
	    lowerPageLabel.setPreferredSize(new Dimension(200, 180));		
	    
	    JLabel menuSelectionLabel = new JLabel();						// menu selection label (probably can change to panel) 
	    menuSelectionLabel.setOpaque(true);
	    menuSelectionLabel.setBackground(new Color(198, 198, 198));		// label color
	    menuSelectionLabel.setLayout(new GridLayout());					// layout for buttons
	    menuSelectionLabel.setPreferredSize(new Dimension(600, 200));
	   
	    JButton loginButton = new JButton("Login");						// login button
	    JButton registerButton = new JButton("Register");				// register button
	    
	    loginButton.addActionListener(new ActionListener() {			// login action
			public void actionPerformed(ActionEvent change) {
				//Login login = new Login();
				//login.beginLoginProcess();
			}
	    });
	    
	    registerButton.addActionListener(new ActionListener() {			// register action
			public void actionPerformed(ActionEvent change) {
				//Register register = new Register();
				//register.beginRegistration();
			}
	    });
	    
	    menuSelectionLabel.add(loginButton);
	    menuSelectionLabel.add(registerButton);
	    
	    mainPage.setJMenuBar(titleMenuBar);
	    mainPage.getContentPane().add(lowerPageLabel, BorderLayout.CENTER);
	    mainPage.getContentPane().add(menuSelectionLabel, BorderLayout.EAST);
	    
	    mainPage.setVisible(true);
	}
}
