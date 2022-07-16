package main.humans_vs_goblins;

import javax.swing.*;
public class Main extends JFrame {

//    public Main(){
//        add(new Model());
//    }

    public static void main(String[] args){
        Main pac = new Main();
        pac.setVisible(true);
        pac.setTitle("Humans VS Goblins");
        pac.setSize(375, 460);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);
    }
}
