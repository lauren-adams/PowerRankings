package edu.baylor.ecs.csi3471.groupProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class loginDAO {
	public static int userLine;
	
	protected boolean isUsernameTaken(String username){
		//Scan user file, seeing if a username matches the attempted username
		try {
			Scanner sc = new Scanner(new File("UserFile.tsv"));
			String data[];
			while(sc.hasNextLine()) {
				data = sc.nextLine().split("\t");
	    		if(username.equals(data[0])) {
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
	
	static boolean validatePassword(String username, String password) {
    	//Scan user file, seeing if a username and password match passed
    	//parameters
    	Scanner sc;
		try {
			sc = new Scanner(new File("UserFile.tsv"));
			String data[];
			while(sc.hasNextLine()) {
	    		data = sc.nextLine().split("\t");
	    		if(username.equals(data[0])&&password.equals(data[1])) {
	    			return true;
	    		}
	    	}
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
    }
	
	static boolean validateUsername(String username) {
    	//scan user file, seeing if a username matches passed string
    	try {
			Scanner sc = new Scanner(new File("UserFile.tsv"));
			String data[];
			int curLine = 0;
			while(sc.hasNextLine()) {
				data = sc.nextLine().split("\t");
				if(username.equals(data[0])) {
					userLine = curLine;
					return true;
				}
				curLine++;
			}
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
    }
}
