package edu.baylor.ecs.csi3471.groupProject;

import edu.baylor.ecs.csi3471.groupProject.Business.User;
import edu.baylor.ecs.csi3471.groupProject.Persistence.UserDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditProfile extends JPanel {

    public EditProfile(String username){
        User user;
        
        JFrame editFrame = new JFrame("Edit Profile");
        JPanel editPanel = new JPanel();
        
		try {
			//user = getUserByUsername(username);
			user = Main.curUser;
			
	        JLabel name = new JLabel("Name");
	        JLabel age = new JLabel("Age");
	        JLabel description = new JLabel("Description");
	
	        final JTextField nameInput = new JTextField();
	        final JTextField ageInput = new JTextField();
	        final JTextField descInput = new JTextField();
	
	        nameInput.setText(user.getName());
	        ageInput.setText(String.valueOf(user.getAge()));
	        descInput.setText(user.getDescription());
	
	        JButton editButton = new JButton("Save");
	
	        editButton.addActionListener(new ActionListener() {
	
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                user.setName(nameInput.getText());
	                user.setAge(Integer.valueOf(ageInput.getText()));
	                user.setDescription(descInput.getText());

					//Database Access
					UserDAO update = new UserDAO();

					update.updateUser();
					editFrame.dispose();
	            }
	
	        });
	
	        nameInput.setColumns(30);
	        ageInput.setColumns(30);
	        descInput.setColumns(30);
	
	        /*add(name);
	        add(nameInput);
	
	        add(age);
	        add(ageInput);
	
	        add(description);
	        add(descInput);
	
	        add(editButton);*/
	        editPanel.add(name);
	        editPanel.add(nameInput);
	
	        editPanel.add(age);
	        editPanel.add(ageInput);
	
	        editPanel.add(description);
	        editPanel.add(descInput);
	
	        editPanel.add(editButton);
	        
	        editFrame.add(editPanel);
	        
	        editFrame.setVisible(true);
	        editFrame.setSize(400, 300);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
    }
}
