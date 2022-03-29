package edu.baylor.ecs.csi3471.groupProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class VotingBooth extends JPanel {
    JLabel label;

    JFrame frame;

    String simpleDialogDesc = "";

    User bill = Main.curUser;


    public VotingBooth(Character[] myCharacters) throws MalformedURLException {
        //FIXME get the current characters for current round from the file instead
        Character bert = new Character();
        Character gandhi = new Character();
        bert.setName("Bert");
        bert.setWorld("Seasame Street");
        bert.setPicture("https://alchetron.com/cdn/bert-sesame-street-77a70cdc-09ba-406a-87c7-f3eed5e0952-resize-750.jpeg");
        gandhi.setName("Ghandi");
        gandhi.setWorld("Clone High");
        gandhi.setPicture("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/Mahatma-Gandhi%2C_studio%2C_1931.jpg/800px-Mahatma-Gandhi%2C_studio%2C_1931.jpg");
        //this.frame = frame;
        JLabel title;


        // Create the components.
        JPanel choicePanel = createSimpleDialogBox(bert, gandhi);

        System.out.println("passed createSimpleDialogBox");

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

        if(Main.curUser.isAdmin()){
            JButton endRound = new JButton("End Round");
            endRound.setPreferredSize(new Dimension(100, 30));
            endRound.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //FIXME GO TO NEXT ROUND FUNCTIONALITY
                    Integer a = 0;
                    if(bert.getRatio() > gandhi.getRatio()){
                        a = bert.getWin();
                        bert.setWin(a++);
                        a = gandhi.getLoss();
                        gandhi.setLoss(a++);
                        JOptionPane.showMessageDialog(null, bert.getName() + " won the match!");
                        if(bill.isVoted()){
                            if(bill.getBet() != 0) {
                                if (bill.getCurrentVote() == bert.getName()) {
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
                        JOptionPane.showMessageDialog(null, gandhi.getName() + " won the match!");
                        if(bill.isVoted()){
                            if(bill.getBet() != 0) {
                                if (bill.getCurrentVote() == bert.getName()) {
                                    bill.setFunds(bill.getFunds() + (bill.getBet() * 2));
                                    JOptionPane.showMessageDialog(null, "Congrats! You won " + bill.getBet() + "!\n Your current funds are: " + bill.getFunds());
                                }
                            }
                        }
                    }
                }
            });
            add(endRound, BorderLayout.WEST);
        }
    }

    void setLabel(String newText) {
        label.setText(newText);
    }

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

        voteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean exit = false;
                while (!exit) {
                    String command = group.getSelection().getActionCommand();
                    boolean broke = false;


                // ok dialog

                    if (command == choosingA) {
                        JOptionPane.showMessageDialog(frame,
                                "Thank you for choosing " + a.getName() + "!");
                        while (!broke) {
                            String value = JOptionPane.showInputDialog("Would you LIke to Wager?" + "\n You have " + bill.getFunds() + " coins.", "0");
                            if (value == null) {
                                value = "0";
                            }
                            int totel = Integer.parseInt(value);
                            if (totel > bill.getFunds()) {
                                JOptionPane.showMessageDialog(null, "Insufficent Funds");
                            } else {
                                broke = true;
                                exit = true;
                                bill.setBet(totel);
                                //bill.setFunds(bill.getFunds() - totel);
                                bill.setVoted(true);
                                bill.setCurrentVote(a.getName());
                                UpdateCSV update = new UpdateCSV();
                                update.updateFunds();
                                JOptionPane.showMessageDialog(null, "Total Wager on " + a.getName() + " is:  " + totel + "\n You have " + (bill.getFunds() - totel) + " coins.");
                            }
                        }

                        // yes/no dialog
                    } else if (command == choosingB) {
                        JOptionPane.showMessageDialog(frame,
                                "Thank you for choosing " + b.getName() + "!");
                        while (!broke) {
                            String value = JOptionPane.showInputDialog("Would you LIke to Wager?" + "\n You have " + bill.getFunds() + " coins.", "0");
                            if (value == null) {
                                value = "0";
                            }
                            int totel = Integer.parseInt(value);
                            if (totel > bill.getFunds()) {
                                JOptionPane.showMessageDialog(null, "Insufficent Funds");
                            } else {
                                broke = true;
                                exit = true;
                                bill.setBet(totel);
                                //bill.setFunds(bill.getFunds() - totel);
                                bill.setVoted(true);
                                bill.setCurrentVote(b.getName());
                                UpdateCSV update = new UpdateCSV();
                                update.updateFunds();
                                //FIXME TRACK NUMBER OF VOTES PER CHARACTER
                                JOptionPane.showMessageDialog(null, "Total Wager on " + b.getName() + " is:  " + totel + "\n You have " + (bill.getFunds() - totel) + " coins.");
                            }
                        }
                    } else {
                        exit = true;
                    }
                }
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                return;
            }

        });
        System.out.println("calling createPane");
        return createPane(simpleDialogDesc, radioButtons, voteButton);
    }
/*
    public ImageIcon urlToImage(String b){
        URL url = null;
        try {
            url  = new URL(b);
            ImageIcon img1 = new ImageIcon(url, "Option");
            return img1;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
*/
    private JPanel createPane(String description, JRadioButton[] radioButtons,
                              JButton showButton) {
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

    protected void createAndShowGUI(Character[] myCharacters) throws MalformedURLException {
        JFrame frame = new JFrame("VoteDialog");


        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(2, 2));
        contentPane.add(new VotingBooth(myCharacters));


        // Exit when the window is closed.
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 600));

        frame.pack();
        frame.setVisible(true);
    }
}
