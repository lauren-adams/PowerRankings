package edu.baylor.ecs.csi3471.groupProject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class CreateCharacter {
	protected void createAndShowGUI() {
		//declaration of variables
		JFrame createFrame;
		JLabel nameLabel, worldLabel, descLabel, urlLabel;
		JTextField nameField, worldField, descField, urlField;
		JPanel createPanel;
		JButton createButton;
		
		//initialization of variables
		createFrame = new JFrame("Create Character");
		createPanel = new JPanel();
		createButton = new JButton("Create");
		
		nameLabel = new JLabel("Name:           ");
		nameField = new JTextField();
		nameField.setColumns(30);
		
		worldLabel = new JLabel("World:          ");
		worldField = new JTextField();
		worldField.setColumns(30);
		
		descLabel = new JLabel("Description:");
		descField = new JTextField();
		descField.setColumns(30);
		
		urlLabel = new JLabel("Photo URL:  ");
		urlField = new JTextField();
		urlField.setColumns(30);
		
		//adding listeners
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String errorMsg = "Invalid ";
				try {
					//check for any errors
					if(!isValidCharName(nameField.getText())||!isValidWorld(worldField.getText())||
							!isValidCharDesc(descField.getText())||!isValidCharURL(urlField.getText())) {
						
						if(!isValidCharName(nameField.getText())) {
							errorMsg += "character name, ";
						}
						if(!isValidWorld(worldField.getText())) {
							if(8 < errorMsg.length()) {
								errorMsg += "and ";
							}
							errorMsg += "world name, ";
						}
						if(!isValidCharDesc(descField.getText())) {
							if(8 < errorMsg.length()) {
								errorMsg = errorMsg.replaceAll("and ", "");
								errorMsg += "and ";
							}
							errorMsg += "character description, ";
						}
						if(!isValidCharURL(urlField.getText())) {
							if(8 < errorMsg.length()) {
								errorMsg = errorMsg.replaceAll("and ", "");
								errorMsg += "and ";
							}
							errorMsg += "picture URL";
						}
						if(errorMsg.substring(errorMsg.length()-2, errorMsg.length()).equals(", ")) {
							errorMsg = errorMsg.substring(0, errorMsg.length()-2);
						}
						JOptionPane.showMessageDialog(createFrame, errorMsg,"ERROR", JOptionPane.ERROR_MESSAGE);
					//if no errors, write to file
					}else {
						createFrame.dispose();
					}
				} catch (NullPointerException n) {
					n.printStackTrace();
				}
			}
		});
		//JOptionPane.showMessageDialog(loginForm,"Invalid username or password","ERROR", JOptionPane.ERROR_MESSAGE);
		
		//adding elements
		createPanel.add(nameLabel);
		createPanel.add(nameField);
		
		createPanel.add(worldLabel);
		createPanel.add(worldField);
		
		createPanel.add(descLabel);
		createPanel.add(descField);
		
		createPanel.add(urlLabel);
		createPanel.add(urlField);
		
		createPanel.add(createButton);
		
		createFrame.add(createPanel);
		
		//setting visible
		createFrame.setSize(400, 175);
		createFrame.setVisible(true);
	}
	
	protected boolean isValidCharName(String name) {
		//checking if character name length is valid
		if(49<name.length()) {
			return false;
		}
		//checking if character name contains any banned characters
		String spec = "!?.@#$%&*()'+,-/:;<=>[]^_`{|}";
		char ch;
		for(int i=0; i<name.length(); i++) {
			ch = name.charAt(i);
			if(spec.contains(String.valueOf(ch))) {
				return false;
			}
		}
		return true;
	}
	
	protected boolean isValidWorld(String world) {
		//checking if character world length is valid
		if(49<world.length()) {
			return false;
		}
		//checking if character world contains any banned characters
		String spec = "@#$%&*()+,-/:;<=>[]^_`{|}";
		char ch;
		for(int i=0; i<world.length(); i++) {
			ch = world.charAt(i);
			if(spec.contains(String.valueOf(ch))) {
				return false;
			}
		}
		return true;
	}
	
	protected boolean isValidCharDesc(String desc) {
		//checking if character description length is valid
		if(299<desc.length()) {
			return false;
		}
		//checking if character description contains any banned characters
		String spec = "@#$%&*()+,-/:;<=>[]^_`{|}";
		char ch;
		for(int i=0; i<desc.length(); i++) {
			ch = desc.charAt(i);
			if(spec.contains(String.valueOf(ch))) {
				return false;
			}
		}
		return true;
	}
	
	protected boolean isValidCharURL(String URL) {
		//checking if URL is within required length
		if(299<URL.length()) {
			return false;
		}
		//check if it is a valid URL
		try {
			new URL(URL).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
