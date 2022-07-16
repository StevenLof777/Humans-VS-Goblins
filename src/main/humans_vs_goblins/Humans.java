package main.humans_vs_goblins;

import java.util.Random;
import java.util.Scanner;

public class Humans {
    private String avatar = " :)";
    private int y_axis = 0, x_axis = 0, clear_x = 0, clear_y = 0, health = 10, strength = 4;
    public int getClear_y() {
        return clear_y;
    }
    public void setClear_y(int clear_y) {
        this.clear_y = clear_y;
    }
    public int getClear_x() {
        return clear_x;
    }
    public void setClear_x(int clear_x) {
        this.clear_x = clear_x;
    }
    public int getY_axis() {
        return y_axis;
    }
    public void setY_axis(int y_axis) {
        this.y_axis = y_axis;
    }
    public int getX_axis() {
        return x_axis;
    }
    public void setX_axis(int x_axis) {
        this.x_axis = x_axis;
    }
    public String getAvatar() {
        return avatar;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public String toString(){
        return "HP: " + this.health + " | STR: " + this.strength;
    }

    public void move(){
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

//        Clear this slot before increments
        setClear_x(getX_axis());
        setClear_y(getY_axis());

        switch (key) {
            case "s":
                setY_axis(getY_axis()+1);
                break;
            case "w":
                setY_axis(getY_axis()-1);
                break;
            case "a":
                setX_axis(getX_axis()-1);
                break;
            case "d":
                setX_axis(getX_axis()+1);
                break;
        }
    }
}
