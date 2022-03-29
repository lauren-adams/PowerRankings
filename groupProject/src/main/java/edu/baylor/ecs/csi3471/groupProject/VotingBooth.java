package edu.baylor.ecs.csi3471.groupProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class VotingBooth extends JPanel {
    JLabel label;

    JFrame frame;

    String simpleDialogDesc = "";

    User bill = Main.curUser;

    Integer fightA = 0;
    Integer fightB = 1;

    public VotingBooth() throws FileNotFoundException {
        //FIXME get the current characters for current round from the file instead
        //Character bert = new Character();
        //Character gandhi = new Character();
        //bert.setName("Bert");
        //bert.setWorld("Seasame Street");
        //gandhi.setName("Ghandi");
        //gandhi.setWorld("Clone High");
        //this.frame = frame;
        JLabel title;
        Scanner scanner = new Scanner(new File("CharacterFile.csv"));
        Scanner dataScanner = null;
        int index = 0;
        System.out.println("---");
        List<Character> fighterList= new ArrayList<>();
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            dataScanner = new Scanner(scanner.nextLine());
            dataScanner.useDelimiter("\t");
            Character dude = new Character();
            while(dataScanner.hasNext()){
                String line = dataScanner.next();
                if(line.equals("")){
                    break;
                }
                if(index == 0){
                    dude.setName(line);
                }
                if(index == 1){
                    dude.setWorld(line);
                }
                if(index == 2){
                    dude.setDesc(line);
                }
                if(index == 3){
                    dude.setWin(Integer.parseInt(line));
                }
                if(index == 4){
                    dude.setWin(Integer.parseInt(line));
                }
                if(index == 6){
                    dude.setPicture(line);
                }
                if(index == 7){
                    dude.setOwner(line);
                }
                index++;
            }
            if(index > 7){
                fighterList.add(dude);
            }
            index = 0;
        }

        Character a = fighterList.get(fightA);
        Character b = fighterList.get(fightB);


        // Create the components.
        JPanel choicePanel = createSimpleDialogBox(a, b);

        System.out.println("passed createSimpleDialogBox");






        title = new JLabel("Click the \"Vote\" button"
                + " once you have selected a candidate.", JLabel.CENTER);
        /*
        label = new JLabel("Vote now!", JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        choicePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
        */
        // Set the layout.
        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);
        //add(label, BorderLayout.SOUTH);
        add(choicePanel, BorderLayout.CENTER);
        //add(pane, BorderLayout.AFTER_LAST_LINE);



        if(Main.curUser.isAdmin()){
            JButton endRound = new JButton("End Round");
            endRound.setPreferredSize(new Dimension(100, 30));
            endRound.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fightA += 2;
                    fightB += 2;
                    if(fighterList.get(fightB) != null){
                        try {
                            createSimpleDialogBox(fighterList.get(fightA), fighterList.get(fightB));
                            createAndShowGUI();
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
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

    private JPanel createSimpleDialogBox(final Character a, final Character b) {
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

        radioButtons[1] = new JRadioButton(
                b.getName() + " from " + b.getWorld());
        radioButtons[1].setActionCommand(choosingB);

        radioButtons[2] = new JRadioButton(
                "SKIP");
        radioButtons[2].setActionCommand(Skip);

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        // Select the first button by default.
        radioButtons[0].setSelected(true);

        voteButton = new JButton("Vote");

        JButton betButton = new JButton("Wager");

        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img1 = urlToImage(a.getPicture());
                g.drawImage(img1, 0, 30, 100, 100, this);


                Image img2 = urlToImage(b.getPicture());
                g.drawImage(img2, 200, 30, 100, 100, this);
            }
        };

        betButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean broke = false;
                Character x = new Character();
                String[] options = new String[] {a.getName(), b.getName()};
                int response = JOptionPane.showOptionDialog(null, "Who are you wagering for?",
                        "TYPE", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if(response == 0){
                    x = a;
                }
                else if(response == 1){
                    x = b;
                }
                else{
                    broke = true;
                }
                while (!broke) {
                    String value = JOptionPane.showInputDialog("How much are you Wagering?" + "\n You have " + bill.getFunds() + " coins.", "0");
                    if (value == null) {
                        value = "0";
                    }
                    int totel = Integer.parseInt(value);
                    if (totel > bill.getFunds() || totel < 0) {
                        JOptionPane.showMessageDialog(null, "Insufficent Funds");
                    } else {
                        broke = true;
                        bill.setBet(totel);
                        bill.setFunds(bill.getFunds() - totel);
                        JOptionPane.showMessageDialog(null, "Total Wager on " + x.getName() + " is:  " + totel + "\n You have " + bill.getFunds() + " coins.");
                    }
                }
                return;
            }
        });

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
        return createPane(simpleDialogDesc, radioButtons, voteButton, betButton, a, b);
    }

    private JPanel createPane(String description, JRadioButton[] radioButtons,
                              JButton showButton, JButton wager, Character a, Character b) {
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
        pane.add(wager, BorderLayout.EAST);

        //pane.add(new DrawMyImgs(a, b));
        System.out.println("returning pane");
        return pane;
    }

    public Image urlToImage(String b){
        Image i = null;
        URL url = null;
        try {
            url  = new URL(b);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Image img1 = ImageIO.read(url);
            return img1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    protected void createAndShowGUI() throws FileNotFoundException {
        JFrame frame = new JFrame("VoteDialog");


        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(2, 1));
        contentPane.add(new VotingBooth());


        // Exit when the window is closed.
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 600));

        frame.pack();
        frame.setVisible(true);
    }
}
