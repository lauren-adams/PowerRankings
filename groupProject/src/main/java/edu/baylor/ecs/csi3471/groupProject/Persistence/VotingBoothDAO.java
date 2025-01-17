package edu.baylor.ecs.csi3471.groupProject.Persistence;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Business.CharacterVotes;
import edu.baylor.ecs.csi3471.groupProject.Business.Runner;
import edu.baylor.ecs.csi3471.groupProject.Business.User;
import edu.baylor.ecs.csi3471.groupProject.UI.TournamentBracketPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.*;
//Based on Round Configure the List

/**
 * The VotingBoothDAO sets up the voting booth and allows the user to place votes and bets.
 */
public class VotingBoothDAO extends JPanel {
    JLabel label;

    JFrame frame;

    String simpleDialogDesc = "";

    User bill = Runner.curUser;



    public VotingBoothDAO(Character a, Character b) throws Exception {
        //FIXME get the current characters for current round from the file instead
        Runner.logger.info("VotingBooth Class Called");
        Character bert = a;
        Character gandhi = b;
        if(a == null || b == null){
            Runner.logger.severe("One of the Characters isn't loading!");
            Exception Exception = new Exception();
            throw Exception;
        }
        //this.frame = frame;
        JLabel title;


        // Create the components.
        JPanel choicePanel = createSimpleDialogBox(bert, gandhi);



        title = new JLabel("Click the \"Vote\" button"
                + " once you have selected a candidate.", JLabel.CENTER);

        label = new JLabel("Vote now!", JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        choicePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));

        // Set the layout.
        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);
        add(label, BorderLayout.SOUTH);
        add(choicePanel, BorderLayout.CENTER);

        if(Runner.curUser.isAdmin()){
            Runner.logger.info("User is Admin");
            JButton endRound = new JButton("End Round");
            endRound.setPreferredSize(new Dimension(100, 30));
            endRound.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //FIXME GO TO NEXT ROUND FUNCTIONALITY
                    TournamentBracketPanel frame = new TournamentBracketPanel();
                    Runner.logger.info("End Round Button Pressed");
                    Integer a = 0;
                    if(bert.getCurrVote() > gandhi.getCurrVote()) {
                        a = bert.getWin();
                        bert.setWin(a++);
                        a = gandhi.getLoss();
                        gandhi.setLoss(a++);
                        //TournamentBracketPanel frame = new TournamentBracketPanel();
                        FileWriter fileWriter = null;
                        try {
                            fileWriter = new FileWriter("CharacterRounds.csv", true);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            fileWriter.write(bert.charToCSV());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        Character[] myChars = frame.getBracketCharacters();
                        int length = 0;
                        while(myChars[length] != null){
                            length++;
                        }
                        System.out.println(length);
                        length--;
                        Integer match = 0;
                        if (length == 9 || length == 13) {
                            match = 1;
                        } else if (length == 10 || length == 14) {
                            match = 2;
                        } else if (length == 11) {
                            match = 3;
                        } else if (length == 12) {
                            match = 4;
                        }
                        JOptionPane.showMessageDialog(null, bert.getName() + " won the match!");
                        CharacterVotesDAO charVote = new CharacterVotesDAO();
                        UserDAO users = new UserDAO();
                        ArrayList<User> userList = null;
                        try {
                            userList = users.getUsers();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            ArrayList<CharacterVotes> votes = charVote.getCharacterVotes();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        for (User u : userList) {
                            try {
                                String opt = charVote.findCurrentVote(u.getUsername(), match);
                                if(opt.equalsIgnoreCase(bert.getName())){
                                    int base = u.getFunds();
                                    int win = charVote.findCurrentBet(u.getUsername(), match);
                                    u.setFunds(base + (win * 2));
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        if (bill.isVoted()) {
                            if (bill.getBet() != 0) {
                                if (bill.getCurrentVote() == bert.getName()) {
                                    Runner.logger.info("User wins the bet");
                                    bill.setFunds(bill.getFunds() + (bill.getBet() * 2));
                                    JOptionPane.showMessageDialog(null, "Congrats! You won " + bill.getBet() + "!\n Your current funds are: " + bill.getFunds());

                                }
                            }
                        }
                    }
                    else{
                        a = gandhi.getWin();
                        gandhi.setWin(a++);
                        a = bert.getLoss();
                        bert.setLoss(a++);
                        FileWriter fileWriter = null;
                        try {
                            fileWriter = new FileWriter("CharacterRounds.csv", true);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            fileWriter.write(gandhi.charToCSV());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, gandhi.getName() + " won the match!");
                        Character[] myChars = frame.getBracketCharacters();
                        int length = 0;
                        while(myChars[length] != null){
                            length++;
                        }
                        System.out.println(length);
                        length--;
                        Integer match = 0;
                        if (length == 9 || length == 13) {
                            match = 1;
                        } else if (length == 10 || length == 14) {
                            match = 2;
                        } else if (length == 11) {
                            match = 3;
                        } else if (length == 12) {
                            match = 4;
                        }
                        CharacterVotesDAO charVote = new CharacterVotesDAO();
                        UserDAO users = new UserDAO();
                        ArrayList<User> userList = null;
                        try {
                            userList = users.getUsers();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            ArrayList<CharacterVotes> votes = charVote.getCharacterVotes();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        for (User u : userList) {
                            try {
                                String opt = charVote.findCurrentVote(u.getUsername(), match);
                                if(opt.equalsIgnoreCase(gandhi.getName())){
                                    int base = u.getFunds();
                                    int win = charVote.findCurrentBet(u.getUsername(), match);
                                    u.setFunds(base + (win * 2));
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        if(bill.isVoted()){
                            if(bill.getBet() != 0) {
                                if (bill.getCurrentVote() == bert.getName()) {
                                    Runner.logger.info("User wins the vote");
                                    bill.setFunds(bill.getFunds() + (bill.getBet() * 2));
                                    JOptionPane.showMessageDialog(null, "Congrats! You won " + bill.getBet() + "!\n Your current funds are: " + bill.getFunds());
                                }
                            }
                        }
                    }
                    bert.setCurrVote(0);
                    gandhi.setCurrVote(0);
                }
            });
            add(endRound, BorderLayout.WEST);
        }
    }

    /**
     * This method always takes two Characters in order to make the voting mechanism
     *
     * @param  a  One of the characters in the match
     * @param  b The other characters in the match
     * @return      JPanel
     */
    private JPanel createSimpleDialogBox(final Character a, final Character b) throws MalformedURLException {
        final int numButtons = 3;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];

        final ButtonGroup group = new ButtonGroup();

        JButton voteButton = null;

        final String choosingA = "default";
        final String choosingB = "yesno";
        final String Skip = "yeahnah";

        radioButtons[0] = new JRadioButton(
                a.getName() + " from " + a.getWorld());
        radioButtons[0].setActionCommand(choosingA);
        URL url  = new URL(a.getPicture());
        ImageIcon img1 = new ImageIcon(url, "Option");
        Image image = img1.getImage(); // transform it
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        img1 = new ImageIcon(newimg);
        radioButtons[0].setIcon(img1);

        radioButtons[1] = new JRadioButton(
                b.getName() + " from " + b.getWorld());
        radioButtons[1].setActionCommand(choosingB);
        URL url2  = new URL(b.getPicture());
        ImageIcon img2 = new ImageIcon(url2, "Option");
        Image image2 = img2.getImage(); // transform it
        Image newimg2 = image2.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        img2 = new ImageIcon(newimg2);
        radioButtons[1].setIcon(img2);

        radioButtons[2] = new JRadioButton(
                "SKIP");
        radioButtons[2].setActionCommand(Skip);

        JPanel subPanel = new JPanel();
        subPanel.setOpaque(false);

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
            subPanel.add(radioButtons[i]);
        }



        // Select the first button by default.
        radioButtons[0].setSelected(true);

        voteButton = new JButton("Vote");

        JButton finalVoteButton = voteButton;
        voteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Runner.logger.info("Vote Button is Activated");
                boolean exit = false;
                while (!exit) {
                    String command = group.getSelection().getActionCommand();
                    boolean broke = false;
                    TournamentBracketPanel bracketPanel = new TournamentBracketPanel();
                    Character[] loop = bracketPanel.getBracketCharacters();
                    CharacterVotesDAO characterVotesDAO = new CharacterVotesDAO();
                    UserDAO userDAO = new UserDAO();
                    CharacterVotes red = null;
                    Integer match = 0;
                    try {
                        red = characterVotesDAO.getCharacterVoteByUsername(bill.getUsername());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                // ok dialog

                    if (command == choosingA) {
                        JOptionPane.showMessageDialog(frame,
                                "Thank you for choosing " + a.getName() + "!");
                        radioButtons[0].setEnabled(false);
                        radioButtons[1].setEnabled(false);
                        radioButtons[2].setEnabled(false);
                        finalVoteButton.setEnabled(false);


                        if(loop.length < 10){
                            if(loop[1].equals(a)){
                                red.setMatchAChoice(a.getName());
                                match = 1;
                            }
                            else if(loop[3].equals(a)){
                                red.setMatchBChoice(a.getName());
                                match = 2;
                            }
                            else if(loop[5].equals(a)){
                                red.setMatchCChoice(a.getName());
                                match = 3;
                            }
                            else{
                                red.setMatchDChoice(a.getName());
                                match = 4;
                            }
                        }
                        else if(loop.length < 14){
                            if(loop[9].equals(a)){
                                red.setMatchAChoice(a.getName());
                                match = 1;
                            }
                            else{
                                red.setMatchBChoice(a.getName());
                                match = 2;
                            }
                        }
                        else{
                            red.setMatchAChoice(a.getName());
                            match = 1;
                        }

                        int vote = a.getCurrVote();
                        a.setCurrVote(vote++);
                        while (!broke) {
                            radioButtons[0].setEnabled(false);
                            radioButtons[1].setEnabled(false);
                            finalVoteButton.setEnabled(false);
                            String value = JOptionPane.showInputDialog("Would you LIke to Wager?" + "\n You have " + bill.getFunds() + " coins.", "0");
                            if (value == null) {
                                Runner.logger.info("User Doesn't Want to bet");
                                value = "0";
                            }
                            int totel = Integer.parseInt(value);
                            if (totel > bill.getFunds()) {
                                Runner.logger.warning("User can't bet that much");
                                JOptionPane.showMessageDialog(null, "Insufficent Funds");
                            } else {
                                broke = true;
                                exit = true;
                                bill.setBet(totel);
                                if(match == 1){
                                    red.setMatchABet(totel);
                                }
                                else if(match == 2){
                                    red.setMatchBBet(totel);
                                }
                                else if(match == 3){
                                    red.setMatchCBet(totel);
                                }
                                else if(match == 4){
                                    red.setMatchDBet(totel);
                                }
                                //bill.setFunds(bill.getFunds() - totel);
                                bill.setVoted(true);
                                bill.setCurrentVote(a.getName());
                                UserDAO update = new UserDAO();
                                try {
									update.updateUser(Runner.curUser);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
                                    Runner.logger.severe("Can't Update User!");
									e1.printStackTrace();
								}
                                JOptionPane.showMessageDialog(null, "Total Wager on " + a.getName() + " is:  " + totel + "\n You have " + (bill.getFunds() - totel) + " coins.");
                            }
                        }

                        // yes/no dialog
                    } else if (command == choosingB) {
                        radioButtons[0].setEnabled(false);
                        radioButtons[1].setEnabled(false);
                        radioButtons[2].setEnabled(false);
                        finalVoteButton.setEnabled(false);
                        JOptionPane.showMessageDialog(frame,
                                "Thank you for choosing " + b.getName() + "!");
                        if(loop.length < 10){
                            if(loop[2].equals(b)){
                                red.setMatchAChoice(b.getName());
                                match = 1;
                            }
                            else if(loop[4].equals(b)){
                                red.setMatchBChoice(b.getName());
                                match = 2;
                            }
                            else if(loop[6].equals(b)){
                                red.setMatchCChoice(b.getName());
                                match = 3;
                            }
                            else{
                                red.setMatchDChoice(b.getName());
                                match = 4;
                            }
                        }
                        else if(loop.length < 14){
                            if(loop[10].equals(b)){
                                red.setMatchAChoice(b.getName());
                                match = 1;
                            }
                            else{
                                red.setMatchBChoice(b.getName());
                                match = 2;
                            }
                        }
                        else{
                            red.setMatchAChoice(b.getName());
                            match = 1;
                        }
                        while (!broke) {
                            String value = JOptionPane.showInputDialog("Would you LIke to Wager?" + "\n You have " + bill.getFunds() + " coins.", "0");
                            if (value == null) {
                                value = "0";
                            }
                            int totel = Integer.parseInt(value);
                            if (totel > bill.getFunds()) {
                                Runner.logger.warning("User can't bet that much");
                                JOptionPane.showMessageDialog(null, "Insufficent Funds");
                            } else {
                                broke = true;
                                exit = true;
                                bill.setBet(totel);
                                if(match == 1){
                                    red.setMatchABet(totel);
                                }
                                else if(match == 2){
                                    red.setMatchBBet(totel);
                                }
                                else if(match == 3){
                                    red.setMatchCBet(totel);
                                }
                                else if(match == 4){
                                    red.setMatchDBet(totel);
                                }
                                //bill.setFunds(bill.getFunds() - totel);
                                bill.setVoted(true);
                                bill.setCurrentVote(b.getName());
                                UserDAO update = new UserDAO();
                                try {
									update.updateUser(Runner.curUser);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
                                    Runner.logger.severe("Can't Update User!");
									e1.printStackTrace();
								}
                                //FIXME TRACK NUMBER OF VOTES PER CHARACTER
                                JOptionPane.showMessageDialog(null, "Total Wager on " + b.getName() + " is:  " + totel + "\n You have " + (bill.getFunds() - totel) + " coins.");
                            }
                        }
                    } else {
                        exit = true;
                        radioButtons[0].setEnabled(false);
                        radioButtons[1].setEnabled(false);
                        finalVoteButton.setEnabled(false);
                    }
                }
                //JComponent comp = (JComponent) e.getSource();
                //Window win = SwingUtilities.getWindowAncestor(comp);
                //win.dispose();
                return;
            }

        });
        System.out.println("calling createPane");
        return createPane(simpleDialogDesc, radioButtons, voteButton);
    }

    /**
     * This method always takes two Characters in order to create a VotingBoothDAO
     *
     * @param  description  the description of the pane to come
     * @param  radioButtons The voting buttons
     * @param  showButton the vote button
     * @return      JPanel
     */
    private JPanel createPane(String description, JRadioButton[] radioButtons,
                              JButton showButton) {
        Runner.logger.info("create pane");
        int numChoices = radioButtons.length;
        JPanel box = new JPanel();
        JLabel label = new JLabel(description);

        box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS));
        box.add(label);

        for (int i = 0; i < numChoices; i++) {
            box.add(radioButtons[i]);
        }

        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());
        pane.add(box, BorderLayout.NORTH);
        pane.add(showButton, BorderLayout.SOUTH);
        //pane.add(new DrawMyImgs());
        System.out.println("returning pane");
        return pane;
    }
}
