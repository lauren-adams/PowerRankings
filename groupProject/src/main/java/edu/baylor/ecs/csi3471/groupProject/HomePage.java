package edu.baylor.ecs.csi3471.groupProject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class HomePage {
	
	protected static void createAndShowGUI() {
		//create mainFrame
		JFrame mainFrame = new JFrame("Power Rankings");
		mainFrame.setSize(960, 540);
		
		//add the menu to mainFrame
		//mainFrame.setLayout(new BorderLayout());
		//mainFrame.add(addMenu(), BorderLayout.CENTER);
		mainFrame.add(addMenu());
		
		//set mainFrame to true
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	protected static JPanel addMenu() {
	//variable declarations
		JPanel menuPanel;
		JButton editProfile, charSearch, leaderboard;
		
	//variable initialization
		menuPanel = new JPanel();
		
		editProfile = new JButton("Edit Profile");
		editProfile.setFocusPainted(false);
		
		charSearch = new JButton("Search Characters");
		charSearch.setFocusPainted(false);
		
		leaderboard = new JButton("View Leaderboards");
		leaderboard.setFocusPainted(false);
		
	//leaderboard action listener
		leaderboard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			//variable declaration
				JFrame selectLead;
				JPanel buttonPanel;
				JButton charLead, userLead;
			
			//variable initialization
				selectLead = new JFrame("Select Leaderboard");
				
				buttonPanel = new JPanel();
				
				charLead = new JButton("Character Leaderboard");
				charLead.setFocusPainted(false);
				
				userLead = new JButton("User Leaderboard");
				userLead.setFocusPainted(false);
				
			//add items to JFrame
				buttonPanel.add(charLead);
				buttonPanel.add(userLead);
				
				selectLead.add(buttonPanel);
				selectLead.setSize(350, 75);
				selectLead.setVisible(true);
			}
		});
		
	//add items to JPanel
		menuPanel.add(editProfile);
		menuPanel.add(charSearch);
		menuPanel.add(leaderboard);
		
	//return the menu JPanel
		menuPanel.setVisible(true);
		return menuPanel;
	}
}

