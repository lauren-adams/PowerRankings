package edu.baylor.ecs.csi3471.groupProject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

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
    String owner = "";

    Character(){
        id = id++;
    }

    Character(String name, String world, String desc, Integer win, Integer loss, String picture, String owner){
        this.name = name;
        this.world = world;
        this.desc = desc;
        this.win = win;
        this.loss = loss;
        this.picture = picture;
        this.owner = owner;
        id = id++;
    }

    Character(String line){
        String[] split = line.split("\t");
        this.name = split[0];
        this.world = split[1];
        this.desc = split[2];
        this.win = Integer.valueOf(split[3]);
        this.loss = Integer.valueOf(split[4]);
        this.id = Integer.valueOf(split[5]);
        this.picture = split[6];
        this.owner = split[7];
        ratio = Double.valueOf(win/loss);
        id = id++;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public Character findChar(String name, String world){
        File file = new File("CharacterFile.csv");

        try {
            Scanner scanner = new Scanner(file);

            //now read the file line by line...
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[]split = line.split("\t");
                lineNum++;
                if(split[0].equals(name) && split[1].equals(world)) {
                    Character c = new Character(line);
                    return c;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Character c = new Character();
        return c;
    }

    public String charToCSV(){
        String ret = "\n";
        ret += name + "\t" + world + "\t" + desc + "\t";
        ret += win + "\t" + loss + "\t" + id + "\t" + picture + "\t" + owner;
        return ret;
    }


}


class PizzaGridBagLayout extends JFrame{
    /*public static void main(String[] args) {
        new PizzaGridBagLayout();
    }*/

    JTextField nameW = new JTextField(20), worldW = new JTextField(10), descW = new JTextField(20);
    JTextField recordW = new JTextField(20);
    JTextField winsW = new JTextField(20);
    JTextField lossesW = new JTextField(20);
    JTextField ownerW = new JTextField(20);

    JButton closeButton = new JButton("Close");

    public PizzaGridBagLayout(Character c) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        addItem(panel1, new JLabel("Name:"), 0, 0, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("World:"), 0, 1, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("Description:"), 0, 2, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("Record:"), 0, 3, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("Wins"), 0, 4, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("Losses:"), 0, 5, 1, 1, GridBagConstraints.EAST);
        addItem(panel1, new JLabel("Owner:"), 0, 6, 1, 1, GridBagConstraints.EAST);




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
        addItem(panel1, winsW, 1, 4, 2, 1, GridBagConstraints.WEST);
        winsW.setText(String.valueOf(c.getWin()));
        winsW.setEditable(false);
        addItem(panel1, lossesW, 1, 5, 2, 1, GridBagConstraints.WEST);
        lossesW.setText(String.valueOf(c.getLoss()));
        lossesW.setEditable(false);
        addItem(panel1, ownerW, 1, 6, 2, 1, GridBagConstraints.WEST);
        ownerW.setText(c.getOwner());
        ownerW.setEditable(false);


        Image image = null;
        try {
            image = ImageIO.read(new File("noImage.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        addItem(panel1, buttonBox, 2, 8, 1, 1, GridBagConstraints.NORTH);

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

