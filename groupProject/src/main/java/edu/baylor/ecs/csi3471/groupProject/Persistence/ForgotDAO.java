package edu.baylor.ecs.csi3471.groupProject.Persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ForgotDAO {
    /**
     * @param userField entered username field
     * @return the password of the user is returned
     */
	public String findPassword(String userField){
        try {
            Scanner scanner = new Scanner(new FileReader("UserFile.tsv"));
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] data = line.split("\t");
                if(data[0].equals(userField)){
                    return data[1];
                }
            }
            return "User not found";
        } catch (FileNotFoundException f) {
        	return "User not found";
        }
	}

    /**
     * @param email user entered email
     * @return users username is returned
     */
	public String findUsername(String email) {
         try {
             Scanner scanner = new Scanner(new FileReader("UserFile.tsv"));
             while(scanner.hasNextLine()){
                 String line = scanner.nextLine();
                 String [] data = line.split("\t");
                 if(data[2].equals(email)){
                     return data[0];
                 }
             }
             return "User not found";

         } catch (FileNotFoundException f) {
        	 return "User not found";
         }
	}
}
