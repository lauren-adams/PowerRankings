package edu.baylor.ecs.csi3471.groupProject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	
	protected static JPanel addMenu() {
	//variable declarations
		JPanel menuPanel;
		JButton editProfile, charSearch, leaderboard, viewCurrentRound;
		
	//variable initialization
		menuPanel = new JPanel();
		
		editProfile = new JButton("Edit Profile");
		editProfile.setFocusPainted(false);
		
		charSearch = new JButton("Search Characters");
		charSearch.setFocusPainted(false);
		
		leaderboard = new JButton("View Leaderboards");
		leaderboard.setFocusPainted(false);

		viewCurrentRound = new JButton("View Current Round");
		viewCurrentRound.setFocusPainted(false);
		
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
				
			//char leaderboard action listener
				charLead.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("character leaderboard selected");
					}
				});
				
			//user leaderboard action listener
				userLead.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Table t = new Table();
						t.createAndShowGUI();
					}
				});


				
			//add items to JFrame
				buttonPanel.add(charLead);
				buttonPanel.add(userLead);
				
				selectLead.add(buttonPanel);
				selectLead.setSize(350, 75);
				selectLead.setVisible(true);
			}
		});

		viewCurrentRound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VotingBooth booth = new VotingBooth();
				booth.createAndShowGUI();
			}
		});
	//add items to JPanel
		menuPanel.add(editProfile);
		menuPanel.add(charSearch);
		menuPanel.add(leaderboard);
		menuPanel.add(viewCurrentRound);
		
	//return the menu JPanel
		menuPanel.setVisible(true);
		return menuPanel;
	}
}

