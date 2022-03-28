package edu.baylor.ecs.csi3471.groupProject;

import java.awt.GridLayout;

import javax.swing.*;

public class CreateCharacter {
	protected void createAndShowGUI() {
		/*//declaration of variables
		GridLayout g = new GridLayout(4, 3);
		JFrame createFrame;
		JLabel nameLabel, descLabel, urlLabel;
		JTextField nameField, descField, urlField;
		
		//initialization of variables
		createFrame = new JFrame("Create Character");
		
		nameLabel = new JLabel("Name:");
		nameField = new JTextField();
		//nameField.setColumns(30);
		
		descLabel = new JLabel("Description:");
		descField = new JTextField();
		//descField.setColumns(30);
		
		urlLabel = new JLabel("Photo URL:");
		urlField = new JTextField();
		//urlField.setColumns(30);
		
		//adding elements and listeners
		createFrame.add(nameLabel);
		createFrame.add(nameField);
		
		createFrame.add(descLabel);
		createFrame.add(descField);
		
		createFrame.add(urlLabel);
		createFrame.add(urlField);
		
		//setting visible
		createFrame.setLayout(g);
		createFrame.pack();
		createFrame.setVisible(true);*/
		
		//declaration of variables
		GridLayout g = new GridLayout(4, 3);
		JFrame createFrame;
		JLabel nameLabel, descLabel, urlLabel;
		JTextField nameField, descField, urlField;
		
		//initialization of variables
		createFrame = new JFrame("Create Character");
		
		nameLabel = new JLabel("Name:");
		nameField = new JTextField();
		//nameField.setColumns(30);
		
		descLabel = new JLabel("Description:");
		descField = new JTextField();
		//descField.setColumns(30);
		
		urlLabel = new JLabel("Photo URL:");
		urlField = new JTextField();
		//urlField.setColumns(30);
		
		//adding elements and listeners
		createFrame.add(nameLabel);
		createFrame.add(nameField);
		
		//createFrame.add(descLabel);
		//createFrame.add(descField);
		
		//createFrame.add(urlLabel);
		//createFrame.add(urlField);
		
		//setting visible
		//createFrame.setLayout(g);
		createFrame.pack();
		createFrame.setVisible(true);
	}
}
