package edu.baylor.ecs.csi3471.groupProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Database {
	protected boolean isUsernameTaken(){
		try {
			Scanner sc = new Scanner(new File("UserFile.csv"));
		} catch (FileNotFoundException e) {
			System.out.println("caught");
			e.printStackTrace();
		}
	}
}
