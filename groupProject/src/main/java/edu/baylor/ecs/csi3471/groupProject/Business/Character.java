package edu.baylor.ecs.csi3471.groupProject.Business;

import java.util.Objects;

import edu.baylor.ecs.csi3471.groupProject.UI.CharacterLayout;

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

    public Character(){
        id = id++;
    }

    public Character(String name, String world, String desc, Integer win, Integer loss, String picture, String owner){
        this.name = name;
        this.world = world;
        this.desc = desc;
        this.win = win;
        this.loss = loss;
        this.picture = picture;
        this.owner = owner;
        id = id++;
    }

    public Character(String line){
        String[] split = line.split("\t");
        this.name = split[0];
        this.world = split[1];
        this.desc = split[2];
        this.win = Integer.valueOf(split[3]);
        this.loss = Integer.valueOf(split[4]);
        this.id = Integer.valueOf(split[5]);
        this.picture = split[6];
        this.owner = split[7];

        if (loss != 0) {
            ratio = Double.valueOf((1.0 * win) /(1.0 * (win + loss)));
        } else {
            if(win == 0){
                ratio = 0.0;
            } else {
                ratio = 1.0;
            }
        }
        ratio = Math.round(ratio * 100.0) / 100.0;
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
        if (loss != 0) {
            ratio = Double.valueOf((1.0 * win) /(1.0 * (win + loss)));
        } else {
            if(win == 0){
                ratio = 0.0;
            } else {
                ratio = 1.0;
            }
        }
        ratio = Math.round(ratio * 100.0) / 100.0;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
        if (loss != 0) {
            ratio = Double.valueOf((1.0 * win) /(1.0 * (win + loss)));
        } else {
            if(win == 0){
                ratio = 0.0;
            } else {
                ratio = 1.0;
            }
        }
        ratio = Math.round(ratio * 100.0) / 100.0;
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

    public void displayChar(){
        new CharacterLayout(this);
        //This may open a dialog box to see character data
    }

    public String charToCSV(){
        String ret = "\n";
        ret += name + "\t" + world + "\t" + desc + "\t";
        ret += win + "\t" + loss + "\t" + id + "\t" + picture + "\t" + owner;
        return ret;
    }
}


