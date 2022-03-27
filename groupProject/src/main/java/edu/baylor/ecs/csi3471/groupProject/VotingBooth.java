package edu.baylor.ecs.csi3471.groupProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VotingBooth extends JPanel {
    JLabel label;

    JFrame frame;

    String simpleDialogDesc = "";

    User bill = new User();


    public VotingBooth(JFrame frame) {
        Character bert = new Character();
        Character gandhi = new Character();
        bert.setName("Bert");
        bert.setWorld("Seasame Street");
        gandhi.setName("Ghandi");
        gandhi.setWorld("Clone High");
        bill.setFunds(1000);
        this.frame = frame;
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
                                bill.setFunds(bill.getFunds() - totel);
                                JOptionPane.showMessageDialog(null, "Total Wager on " + a.getName() + " is:  " + totel + "\n You have " + bill.getFunds() + " coins.");
                            }
                        }

                        // yes/no dialog
                    } else if (command == choosingB) {
                        JOptionPane.showMessageDialog(frame,
                                "Thank you for choosing " + b.getName() + "!");
                        while (!broke) {
                            String value = JOptionPane.showInputDialog("Would you LIke to Wager?" + "\n You have " + bill.getFunds() + " coins.", "0");
                            int totel = Integer.parseInt(value);
                            if (totel > bill.getFunds()) {
                                JOptionPane.showMessageDialog(null, "Insufficent Funds");
                            } else {
                                broke = true;
                                exit = true;
                                bill.setBet(totel);
                                bill.setFunds(bill.getFunds() - totel);
                                JOptionPane.showMessageDialog(null, "Total Wager on " + b.getName() + " is:  " + totel + "\n You have " + bill.getFunds() + " coins.");
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
        pane.add(new DrawMyImgs());
        System.out.println("returning pane");
        return pane;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VoteDialog");


        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(2, 1));
        contentPane.add(new VotingBooth(frame));


        // Exit when the window is closed.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }
}
