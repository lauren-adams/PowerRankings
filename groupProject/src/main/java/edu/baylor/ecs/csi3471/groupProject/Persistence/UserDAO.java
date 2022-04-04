package edu.baylor.ecs.csi3471.groupProject.Persistence;

import edu.baylor.ecs.csi3471.groupProject.Business.Runner;
import edu.baylor.ecs.csi3471.groupProject.Business.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

//Used to update the csv holding all of the user info without deleteing all the older data
//just writes to the file
public class UserDAO {

    //update one specific user from
    public void updateUser(User user) throws IOException {
        List<String[]> data = new ArrayList<>();
        ArrayList<User> savedUsers = getUsers();

        for (User u : savedUsers) {
            if (u.getUsername().equals(user.getUsername())) {
                u = user;
            }
            data.add(new String[]{u.getUsername(), u.getPassword(), u.getEmail(), u.getName(),
                    String.valueOf(u.getAge()), String.valueOf(u.getFunds()), String.valueOf(u.getBet()),
                    String.valueOf(u.isVoted()), String.valueOf(u.isAdmin()), u.getDescription(),
                    u.getCurrentVote(), String.valueOf(u.getCurrentStreak()), u.getLastLogin()});
        }

        File tsvOut = new File("UserFile.tsv");
        PrintWriter pw = new PrintWriter(tsvOut);
        pw.write("Username	Password	Email	Name	Age	Currency	Bet	Voted	Admin	Description	CurrentVote	CurrentStreak	LastLogin");
        pw.write("\n");
        for(String s[]: data) {
            pw.write(String.join("\t", s));
            pw.write("\n");
        }
        pw.close();

    }
    //Get full list of users from file
    public ArrayList<User> getUsers() throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        BufferedReader br = new BufferedReader(new FileReader("UserFile.tsv"));
        String line = "";
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] user = line.split("\t");
            User curr = new User(user);

            users.add(curr);
        }

        return users;
    }

    //Find User from file
    public User getUserByUsername(String username) throws Exception {
    	Scanner sc = new Scanner(new File("UserFile.tsv"));
    	String data[];
    	while(sc.hasNextLine()) {
    		data = sc.nextLine().split("\t");
    		if(data[0].equals(username)) {
    			User curr = new User(data);
    			return curr;
    		}
    	}
    	throw new NoSuchElementException("This user does not exist");
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
    
    protected void setCurrentBal(String username, int newBalance) {
    	try {
            Scanner sc = new Scanner(new File("UserFile.tsv"));
            String data[];
            while(sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if(data[0].equals(username)) {
                	data[5] = Integer.toString(newBalance);
                	User newUser = new User(data);
                	try {
						updateUser(newUser);
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
            }
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        }
    }

    protected int findCurrentStreak(String username) {
    	try {
            Scanner sc = new Scanner(new File("UserFile.tsv"));
            String data[];
            while(sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if(data[0].equals(username)) {
                    return Integer.parseInt(data[11]);
                }
            }
            return 0;
        } catch (FileNotFoundException e) {
            return 0;
        }
    }
    
    protected void setCurrentStreak(String username, int newStreak) {
    	try {
            Scanner sc = new Scanner(new File("UserFile.tsv"));
            String data[];
            while(sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if(data[0].equals(username)) {
                	data[11] = Integer.toString(newStreak);
                	User newUser = new User(data);
                	try {
						updateUser(newUser);
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
            }
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        }
    }
    
    protected String findLastLogin(String username) {
    	try {
            Scanner sc = new Scanner(new File("UserFile.tsv"));
            String data[];
            while(sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if(data[0].equals(username)) {
                    return data[12];
                }
            }
            return "null";
        } catch (FileNotFoundException e) {
            return "null";
        }
    }
    
    protected void setLastLogin(String username, String newLastLogin) {
    	try {
            Scanner sc = new Scanner(new File("UserFile.tsv"));
            String data[];
            while(sc.hasNextLine()) {
                data = sc.nextLine().split("\t");
                if(data[0].equals(username)) {
                	data[12] = newLastLogin;
                	User newUser = new User(data);
                	try {
						updateUser(newUser);
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
            }
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        }
    }
}
