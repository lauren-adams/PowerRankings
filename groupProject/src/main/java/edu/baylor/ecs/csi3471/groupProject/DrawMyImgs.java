/*
package edu.baylor.ecs.csi3471.groupProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.AttributedCharacterIterator;


public class DrawMyImgs extends JPanel {
    DrawMyImgs(Character a, Character b){

    }
    public void paint(Graphics g) {
        //Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\18326\\Downloads\\bert.png");
        Image img1 = urlToImage(a.getPicture());
        System.out.println(a.getPicture());
        g.drawImage(img1, 0, 0, 100, 100, this);


        Image img2 = urlToImage(b.getPicture());
        System.out.println(b.getPicture());
        g.drawImage(img2, 200, 0, 100, 100, this);

        //Image img2 = Toolkit.getDefaultToolkit().getImage("")
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
}


 */
