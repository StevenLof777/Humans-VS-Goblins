package main.humans_vs_goblins;

import javax.swing.*;
public class Main extends JFrame {
    public static void main(String[] args){
        new Game().generateWorld();
        new WorldFrame();
    }
}
