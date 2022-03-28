package edu.baylor.ecs.csi3471.groupProject;


import java.io.*;

//Used to update the csv holding all of the user info without deleteing all the older data
//just writes to the file
public class UpdateCSV {

    //currently writing it, will finish tomorrow :)))))
    //so just ignore for now, well get done soon enough
    public void updateFunds(){

        try {
            BufferedReader br = new BufferedReader(new FileReader("UserFile.tsv"));
            BufferedWriter wr = new BufferedWriter(new FileWriter("UserFile.tsv", false));
            String line;

            while((line = br.readLine()) != null) {
                if(line.contains(id)) {
                    //returns true
                    System.out.println("Is line there: " + line.contains(id));

                    //returns helloText
                    System.out.println("ID: " + extractId(line));

                    //returns How are you
                    System.out.println("TEXT: " + extractText(line));

                    //returns Some new text for a change
                    System.out.println("NEW_TEXT: " + newText);

                    // This is where I am trying to replace the old text
                    // with new text that came from the main method.
                    line = line.replaceAll(extractText(line), newText);
                    //wr.newLine();
                    wr.write(line);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            wr.close();
        }
    }
    public void updateBet(){

    }
    public void updateVoted(){

    }
    public void updateCurrentVote(){

    }
}
