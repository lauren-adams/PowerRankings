package edu.baylor.ecs.csi3471.groupProject;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

public class VotingBoothGUI {
    protected void createAndShowGUI(Character a, Character b) throws MalformedURLException {
        JFrame frame = new JFrame("VoteDialog");


        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(2, 2));
        contentPane.add(new VotingBooth(a, b));


        // Exit when the window is closed.
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 600));

        frame.pack();
        frame.setVisible(true);
    }
}
