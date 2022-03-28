package edu.baylor.ecs.csi3471.groupProject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		worldLabel = new JLabel("World: ");
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
				
			}
		});
		
		//adding elements
		createPanel.add(nameLabel);
		createPanel.add(nameField);
		
		createPanel.add(descLabel);
		createPanel.add(descField);
		
		createPanel.add(urlLabel);
		createPanel.add(urlField);
		
		createPanel.add(createButton);
		
		createFrame.add(createPanel);
		
		//setting visible
		createFrame.setSize(400, 150);
		createFrame.setVisible(true);
	}
	
	//protected void check
}
