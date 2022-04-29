package edu.baylor.ecs.csi3471.groupProject.UI;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Persistence.VotingBoothDAO;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.util.logging.Logger;

public class VotingBoothGUI {
    /**
     *
     * <p>
     * This method always returns immediately, whether or not the
     * image exists. When this applet attempts to draw the image on
     * the screen, the data will be loaded. The graphics primitives
     * that draw the image will incrementally paint on the screen.
     *
     * @param  a  an absolute URL giving the base location of the image
     * @param  b the location of the image, relative to the url argument
     * @return      void
     * @see         Image
     */
    protected void createAndShowGUI(Character a, Character b) throws Exception {
        JFrame frame = new JFrame("VoteDialog");
        Runner.logger.info("create GUI invoked");


        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(2, 2));
        contentPane.add(new VotingBoothDAO(a, b));


        // Exit when the window is closed.
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 600));

        frame.pack();
        frame.setVisible(true);
    }
}
