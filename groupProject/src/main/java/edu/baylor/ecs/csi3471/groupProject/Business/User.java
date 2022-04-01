package edu.baylor.ecs.csi3471.groupProject.Business;

import java.util.Objects;

public class User
{
    private String username;
    private String password;
    private String email;
    private String name;
    private int age;
    private int funds;
    private int bet;
    private boolean voted;
    private boolean admin;
    private String description;
    private String currentVote;

    public User(String[] data){
    	try {
    		this.username = data[0];
	        this.password = data[1];
	        this.email = data[2];
	        this.name = data[3];
	        this.age = Integer.parseInt(data[4]);
	        this.funds = Integer.parseInt(data[5]);
	        this.bet = Integer.parseInt(data[6]);
	        this.voted = Boolean.parseBoolean(data[7]);
	        this.admin = Boolean.parseBoolean(data[8]);
	        this.description = data[9];
	        this.currentVote = data[10];
    	} catch (NullPointerException e) {
    		e.printStackTrace();
    	}
    }

    public User()
    {
        username = "";
        password = "";
        email = "";
        name = "";
        age = -1;
        funds = -1;
        bet = -1;
        voted = false;
        admin = false;
        description = "";
        currentVote = "";
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentVote() {
        return currentVote;
    }

    public void setCurrentVote(String currentVote) {
        this.currentVote = currentVote;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }



}