package edu.baylor.ecs.csi3471.groupProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class HomePage {
	static String currUsername;
	protected static void createAndShowGUI(String username) {
		//create mainFrame
		JFrame mainFrame = new JFrame("Power Rankings");
		mainFrame.setSize(650, 750);
		
		//add the menu to mainFrame
		//mainFrame.setLayout(new BorderLayout());
		//mainFrame.add(addMenu(), BorderLayout.CENTER);
		//GridLayout g = new GridLayout(2, 1);
		
		JPanel menuPanel = addMenu();
		menuPanel.setBounds(0, 0, 650, 50);
		mainFrame.add(menuPanel);
		
		TournamentBracketFrame f = new TournamentBracketFrame();
		JLayeredPane layered = f.getBracket();
		
		layered.setBounds(0, 50, 550, 750);
		
		
		mainFrame.add(layered);
		
		
		//mainFrame.setLayout(g);
		//set mainFrame to true
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		currUsername = username;
	}
	
	
	protected static JPanel addMenu() {
	//variable declarations
		JPanel menuPanel;
		final JButton editProfile, charSearch, leaderboard, currentRound, createChar;

		
	//variable initialization
		menuPanel = new JPanel();

		
		editProfile = new JButton("Edit Profile");
		editProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					EditProfile ep = new EditProfile(currUsername);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		editProfile.setFocusPainted(false);
		
		charSearch = new JButton("Characters");
		charSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Table t = new Table();
				t.createAndShowGUI();
			}
		});
		charSearch.setFocusPainted(false);
		
		leaderboard = new JButton("View Leaderboards");
		leaderboard.setFocusPainted(false);

		createChar = new JButton("Create Character");
		createChar.setFocusPainted(false);
		
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
						Table t = new Table();
						t.createAndShowGUI();
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
		
		currentRound = new JButton("Current Round");
		currentRound.setFocusPainted(false);

		currentRound.addActionListener(new ActionListener() {
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
		menuPanel.add(currentRound);
		menuPanel.add(createChar);

		/*BufferedImage image = null;
		try {
			image = ImageIO.read(new File("bracket_template.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}


		Image scaledImage = image.getScaledInstance(800,500,Image.SCALE_SMOOTH);

		JLabel picLabel = new JLabel(new ImageIcon(scaledImage));


		menuPanel.add(picLabel);*/

		
	//return the menu JPanel
		menuPanel.setVisible(true);
		return menuPanel;
	}
}

