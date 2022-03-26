package test;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class HomePage {
	
	protected static void createAndShowGUI() {
		//create mainFrame
		JFrame mainFrame = new JFrame("Power Rankings");
		mainFrame.setSize(960, 540);
		
		//add the menu to mainFrame
		mainFrame.setJMenuBar(addMenu());
		
		//set mainFrame to true
		mainFrame.setVisible(true);
	}
	
	protected static JMenuBar addMenu() {
		//variable declarations
		JMenu menu;
		JMenuBar menuBar;
		JMenuItem editProfile;
		
		//initializing variables
		menu = new JMenu("Menu");
		menuBar = new JMenuBar();
		editProfile = new JMenuItem("Edit Profile");
		
		//adding menu attributes
		menu.setMnemonic(KeyEvent.VK_A);
    	menu.getAccessibleContext().setAccessibleDescription(
    	        "This menu contains...");
		
		menu.add(editProfile);
		menuBar.add(menu);
		
		return menuBar;
	}
}
