package edu.baylor.ecs.csi3471.groupProject.Persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class loginDAO {
	public static String[] userData;
	
	protected boolean isUsernameTaken(String username){
		//Scan user file, seeing if a username matches the attempted username
		try {
			Scanner sc = new Scanner(new File("UserFile.tsv"));
			String data[];
			while(sc.hasNextLine()) {
				data = sc.nextLine().split("\t");
	    		if(username.equals(data[0])) {
	    			userData = data;
	    			return true;
	    		}
			}
			return false;
		} catch (FileNotFoundException e) {
			System.out.println("caught");
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean validatePassword(String username, String password) {
    	//Scan user file, seeing if a username and password match passed
    	//parameters
    	Scanner sc;
		try {
			sc = new Scanner(new File("UserFile.tsv"));
			String data[];
			while(sc.hasNextLine()) {
	    		data = sc.nextLine().split("\t");
	    		if(username.equals(data[0]) && password.equals(data[1])) {
	    			userData = data;
	    			return true;
	    		}
	    	}
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
    }
}
