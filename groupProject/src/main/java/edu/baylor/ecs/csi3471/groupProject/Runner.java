package edu.baylor.ecs.csi3471.groupProject;

import edu.baylor.ecs.csi3471.groupProject.Business.User;

public class Runner {
	public static User curUser;
	public static void main(String[] args) {
//		HomePage h = new HomePage();
//		h.createAndShowGUI();

		Login login = new Login();
		login.beginLoginProcess();
	}
}
