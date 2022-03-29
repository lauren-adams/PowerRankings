package edu.baylor.ecs.csi3471.groupProject;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

//Used to update the csv holding all of the user info without deleteing all the older data
//just writes to the file
public class UpdateCSV {

    //currently writing it, will finish tomorrow :)))))
    //so just ignore for now, well get done soon enough
    public void updateFunds(){
        User user = Main.curUser;
        //user.setName(nameInput.getText());
        //user.setAge(Integer.valueOf(ageInput.getText()));
        //user.setDescription(descInput.getText());

        try {
            updateUser(user);
        } catch (IOException e1) {
            throw new NoSuchElementException("This user does not exist");
        }
        //editFrame.dispose();
    }
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
                    u.getCurrentVote()});
        }

        File tsvOut = new File("UserFile.tsv");
        PrintWriter pw = new PrintWriter(tsvOut);
        pw.write("Username	Password	Email	Name	Age	Currency	Bet	Voted	Admin	Description	CurrentVote");
        pw.write("\n");
        for(String s[]: data) {
            pw.write(String.join("\t", s));
            pw.write("\n");
        }
        pw.close();
        //File csvOutputFile = new File("UserFile.csv");
        //try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
        //data.stream().map(this::convertToCSV).forEach(pw::println);
        //}

    }
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
}
