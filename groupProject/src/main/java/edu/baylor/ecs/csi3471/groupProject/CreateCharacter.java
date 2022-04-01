package edu.baylor.ecs.csi3471.groupProject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class CreateCharacter extends CharacterDAO {
	protected void createAndShowGUI(String currUsername) {
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
						//check if name is valid, if it isn't update error message
						if(!isValidCharName(nameField.getText())) {
							errorMsg += "character name, ";
						}
						//check if world is valid, if it isn't update error message
						if(!isValidWorld(worldField.getText())) {
							if(8 < errorMsg.length()) {
								errorMsg += "and ";
							}
							errorMsg += "world name, ";
						}
						//check if description is valid, if it isn't update error message
						if(!isValidCharDesc(descField.getText())) {
							if(8 < errorMsg.length()) {
								errorMsg = errorMsg.replaceAll("and ", "");
								errorMsg += "and ";
							}
							errorMsg += "character description, ";
						}
						//check if URL is valid, if it isn't update error message
						if(!isValidCharURL(urlField.getText())) {
							if(8 < errorMsg.length()) {
								errorMsg = errorMsg.replaceAll("and ", "");
								errorMsg += "and ";
							}
							errorMsg += "picture URL";
						}
						//remove any trailing commas
						if(errorMsg.substring(errorMsg.length()-2, errorMsg.length()).equals(", ")) {
							errorMsg = errorMsg.substring(0, errorMsg.length()-2);
						}
						//throw error with correct message
						JOptionPane.showMessageDialog(createFrame, errorMsg,"ERROR", JOptionPane.ERROR_MESSAGE);
					//if no errors, write to file
					}else {
						if(doesCharExist(nameField.getText(), worldField.getText())) {
							JOptionPane.showMessageDialog(createFrame, "Character already exists in chosen world",
									"ERROR", JOptionPane.ERROR_MESSAGE);
						}else {

							addCharacter(nameField.getText(), worldField.getText(), descField.getText(),
									urlField.getText(), currUsername);
							createFrame.dispose();
						}
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
		createFrame.setSize(400, 300);
		createFrame.setVisible(true);
	}
	
	protected boolean isValidCharName(String name) {
		//checking if character name length is valid
		if(49<name.length()||name.length()==0) {
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
		if(49<world.length()||world.length()==0) {
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
		if(299<desc.length()||desc.length()==0) {
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
	
	protected boolean doesCharExist(String name, String world) {
		try {
			Scanner sc = new Scanner(new File("CharacterFile.csv"));
			String data[];
			while(sc.hasNextLine()) {
				data = sc.nextLine().split("\t");
				if(data[0].equalsIgnoreCase(name)&&data[1].equalsIgnoreCase(world)) {
					return true;
				}
			}
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return true;
		}
	}
	

}
