import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class DrawMyImgs extends JPanel {
    public void paint(Graphics g) {
        //Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\18326\\Downloads\\bert.png");
        Image img1 = urlToImage("https://i.imgflip.com/ave7a.jpg");
        g.drawImage(img1, 0, 0, 100, 100, this);


        Image img2 = urlToImage("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/Mahatma-Gandhi%2C_studio%2C_1931.jpg/800px-Mahatma-Gandhi%2C_studio%2C_1931.jpg");
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
