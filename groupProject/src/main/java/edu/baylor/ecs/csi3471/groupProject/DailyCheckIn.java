package edu.baylor.ecs.csi3471.groupProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JLabel;

public class DailyCheckIn {
	protected JLabel showBalance(String username) {
		JLabel balanceLabel;
		String bal = "Balance: ";
		
		bal += Integer.toString(findCurrentBal(username));
		bal += " coins";
		
		balanceLabel = new JLabel(bal);
		
		return balanceLabel;
	}
	
	protected int findCurrentBal(String username) {
		try {
			Scanner sc = new Scanner(new File("UserFile.tsv"));
			String data[];
			while(sc.hasNextLine()) {
				data = sc.nextLine().split("\t");
				if(data[0].equals(username)) {
					return Integer.parseInt(data[5]);
				}
			}
			return 0;
		} catch (FileNotFoundException e) {
			return 0;
		}
	}
	
	protected void updateBalance(User user){
		
	}
}
