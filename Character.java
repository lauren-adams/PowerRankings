package org.example;

import java.util.Objects;

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
        //This may open a dialog box to see character data
    }


}
