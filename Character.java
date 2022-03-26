package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Character {
    String name = "";
    String world = "";
    String desc = "";
    Integer win = 0;
    Integer loss = 0;
    static Integer id = 0;
    String picture = "";
    Double ratio = 0.0;

    Character(){
        id = id++;
    }

    Character(String name, String world, String desc, Integer win, Integer loss, String picture){
        this.name = name;
        this.world = world;
        this.desc = desc;
        this.win = win;
        this.loss = loss;
        this.picture = picture;
        id = id++;
    }

    public Integer getId() {
        return id;
    }

    public Double getRatio() {
        return ratio;
    }

    public Integer getLoss() {
        return loss;
    }

    public void setLoss(Integer loss) {
        this.loss = loss;
        ratio = Double.valueOf(win/loss);
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
        if(loss != 0) {
            ratio = Double.valueOf(win / loss);
        }
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(name, character.name) && Objects.equals(world, character.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, world);
    }

    void displayChar(){
        new PizzaGridBagLayout(this);
        //This may open a dialog box to see character data
    }


}


class PizzaGridBagLayout extends JFrame{
    /*public static void main(String[] args) {
        new PizzaGridBagLayout();
    }*/

    JTextField nameW = new JTextField(20), worldW = new JTextField(10), descW = new JTextField(20);
    JTextField recordW = new JTextField(20);

    JButton closeButton = new JButton("Close");

    public PizzaGridBagLayout(Character c) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        addItem(panel1, new JLabel("Name:"), 0, 0, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("World:"), 0, 1, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("Description:"), 0, 2, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("Record:"), 0, 3, 1, 1, GridBagConstraints.EAST);


        addItem(panel1, nameW, 1, 0, 2, 1, GridBagConstraints.WEST);
        nameW.setText(c.getName());
        nameW.setEditable(false);
        addItem(panel1, worldW, 1, 1, 1, 1, GridBagConstraints.WEST);
        worldW.setText(c.getWorld());
        worldW.setEditable(false);
        addItem(panel1, descW, 1, 2, 2, 1, GridBagConstraints.WEST);
        descW.setText(c.getDesc());
        descW.setEditable(false);
        addItem(panel1, recordW, 1, 3, 2, 1, GridBagConstraints.WEST);
        String rec = "";
        rec += c.getWin();
        rec += "/";
        rec += c.getLoss();
        recordW.setText(rec);
        recordW.setEditable(false);

        Image image = null;/* w  ww .  ja  v  a 2 s.c o m*/
        try {
            URL url = new URL(c.getPicture());
            image = ImageIO.read(url);
        }
        catch (IOException e) {
        }

        JFrame frame = new JFrame();
        Image newImage = image.getScaledInstance(300, 300, Image.SCALE_DEFAULT);

        JLabel lblimage = new JLabel(new ImageIcon(newImage));


        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(lblimage);
        addItem(panel1, buttonBox, 2, 4, 1, 1, GridBagConstraints.NORTH);

        this.add(panel1);
        this.pack();
        this.setVisible(true);
    }

    private void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = 100.0;
        gc.weighty = 100.0;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        p.add(c, gc);
    }
};

