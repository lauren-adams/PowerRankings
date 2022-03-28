package edu.baylor.ecs.csi3471.groupProject;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;


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


public class TournamentBracketFrame
{

    public Character[] initTournamentCharacters()
    {
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
            System.out.println(e);
            System.exit(1);
        }
        return characters;
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
                    yCoord = y;
                }
                URL url = new URL(myChars[i].getPicture());
                Image tempImg = ImageIO.read(url);
                tempImgPanel = new ImagePanel(tempImg);

                tempImgPanel.setBounds(xCoord, yCoord, 75, 75);
                myBigPane.add(tempImgPanel, JLayeredPane.PALETTE_LAYER);
                yCoord += yInc;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
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
        imgPanel.setBounds(0, 0, 550, 550);

        myBigPane.add(imgPanel, JLayeredPane.DEFAULT_LAYER);

        myBigPane = addImagesToBracket(myChars, myBigPane, 0, 10, 450, 140, 0, 8);
        myBigPane = addImagesToBracket(myChars, myBigPane, 175, 80, 120, 300, 8, 12);
        myBigPane = addImagesToBracket(myChars, myBigPane, 175, 275, 100, 200, 12, 14);

//        frame.add(myBigPane);
//        frame.setVisible(true);

        return myBigPane;
    }
//
//    public static void main(String[] args)
//    {
//        TournamentBracketFrame tBracket = new TournamentBracketFrame();
//        JLayeredPane myLayeredPane = tBracket.getBracket();
//
//    }
}





