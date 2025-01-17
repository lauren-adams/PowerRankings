package edu.baylor.ecs.csi3471.groupProject.UI;


import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Business.Runner;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.logging.Logger;


class ImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Image img;
    private Image scaled;

    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public ImagePanel(Image img) {
        this.img = img;
    }

    @Override
    public void invalidate() {
        super.invalidate();
        int width = getWidth();
        int height = getHeight();

        if (width > 0 && height > 0) {
            scaled = img.getScaledInstance(getWidth(), getHeight(),
                    Image.SCALE_SMOOTH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return img == null ? new Dimension(200, 200) : new Dimension(
                img.getWidth(this), img.getHeight(this));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(scaled, 0, 0, null);
    }
}


public class TournamentBracketPanel
{
    //public static Logger logger = Logger.getLogger(Runner.class.getName());
    public Character[] initTournamentCharacters()
    {
        Runner.logger.info("Initializing tournament characters");
        Character[] characters = new Character[15];
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader("CharacterRounds.csv"));
            reader.readLine();

            int i = 0;
            for(String temp = reader.readLine(); temp != null; temp = reader.readLine())
            {
                characters[i] = new Character(temp);
                i++;
            }
            reader.close();
        }
        catch(Exception e)
        {
            Runner.logger.severe("unable to read characters from characterRounds.cdv file");
            System.exit(1);
        }
        return characters;
    }


    public Character[] getBracketCharacters()
    {
        Runner.logger.info("returning tournament characters");
        Character[] myChars = initTournamentCharacters();
        return myChars;
    }


    public JLayeredPane addImagesToBracket(Character[] myChars, JLayeredPane myBigPane, int x, int y, int xInc, int yInc, int iBeg, int iEnd)
    {
        int xCoord = x, yCoord = y;
        ImagePanel tempImgPanel = null;
        try
        {
            for(int i = iBeg; i < iEnd; i++)
            {
                if(i == (iBeg + iEnd)/2)
                {
                    xCoord += xInc;

                    if(i!=13)
                    {
                        yCoord = y;
                    }

                }
                URL url = new URL(myChars[i].getPicture());
                Image tempImg = ImageIO.read(url);
                tempImgPanel = new ImagePanel(tempImg);

                Runner.logger.info("image " + i + " is being added to the bracket");
                tempImgPanel.setBounds(xCoord, yCoord, 75, 75);
                myBigPane.add(tempImgPanel, JLayeredPane.PALETTE_LAYER);
                yCoord += yInc;
            }
        }
        catch(Exception e)
        {
            Runner.logger.severe("could not load the images onto the tournament bracket");
            System.exit(1);
        }

        return myBigPane;
    }



    public JLayeredPane getBracket()
    {
//        JFrame frame = new JFrame("TEMP");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(550, 550);
//        //frame size
//        frame.setLocationRelativeTo(null);


        Runner.logger.info("tournament bracket being generated");
        ImagePanel imgPanel = null;
        try
        {
            Image img = ImageIO.read(new File("bracket_template.png"));
            imgPanel = new ImagePanel(img);
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.exit(1);
        }

        Character[] myChars = initTournamentCharacters();

        JLayeredPane myBigPane = new JLayeredPane();
        //imgPanel.setBounds(0, 0, 550, 550);
        imgPanel.setBounds(0, 100, 550, 650);

        myBigPane.add(imgPanel, JLayeredPane.DEFAULT_LAYER);


        Runner.logger.info("adding round one to the tournament bracket");
        myBigPane = addImagesToBracket(myChars, myBigPane, 0, 125, 450, 175, 0, 8);


//        logger.info("adding round two to the tournament bracket");
//        myBigPane = addImagesToBracket(myChars, myBigPane, 175, 230, 120, 350, 8, 12);
//
//        logger.info("adding round three to the tournament bracket");
//        //myBigPane = addImagesToBracket(myChars, myBigPane, 175, 420, 120, -80, 12, 14);

//        frame.add(myBigPane);
//        frame.setVisible(true);

        return myBigPane;
    }


    public JLayeredPane getRound2(JLayeredPane myBigPane)
    {
//        JFrame frame = new JFrame("TEMP");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(550, 550);
//        //frame size
//        frame.setLocationRelativeTo(null);

        Character[] myChars = initTournamentCharacters();

        Runner.logger.info("adding round two to the tournament bracket");
        myBigPane = addImagesToBracket(myChars, myBigPane, 175, 230, 120, 350, 8, 12);

//        frame.add(myBigPane);
//        frame.setVisible(true);

        return myBigPane;
    }

    public JLayeredPane getRound3(JLayeredPane myBigPane)
    {
//        JFrame frame = new JFrame("TEMP");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(550, 550);
//        //frame size
//        frame.setLocationRelativeTo(null);

        Character[] myChars = initTournamentCharacters();

        Runner.logger.info("adding round three to the tournament bracket");
        myBigPane = addImagesToBracket(myChars, myBigPane, 175, 420, 120, -80, 12, 14);

//        frame.add(myBigPane);
//        frame.setVisible(true);

        return myBigPane;
    }
}






