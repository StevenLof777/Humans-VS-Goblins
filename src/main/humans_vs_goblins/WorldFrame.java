package main.humans_vs_goblins;

import javax.swing.*;
import java.awt.*;

public class WorldFrame extends JFrame {

    public WorldFrame(){

        JPanel world = new JPanel();
        world.setBackground(Color.red);
        world.setBounds(110,10,500,500);
        world.setLayout(new GridLayout(10,10));

        JLabel hp = new JLabel(), gold = new JLabel();
        hp.setText("HP: 100"); hp.setForeground(Color.red);
        hp.setFont(new Font("SansSerif", Font.BOLD, 20));

        gold.setText("GOLD: 0"); gold.setForeground(Color.yellow);
        gold.setFont(new Font("SansSerif", Font.BOLD, 15));

        hp.setBounds(13,-13,75,75);
        gold.setBounds(400,-13,75,75);

        JPanel hud = new JPanel();
        hud.setBackground(Color.gray);
        hud.setBounds(110,510,500,50);
        hud.add(hp);
        hud.add(gold);
        hud.setLayout(null);

        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.black);
        frame.setSize(720,720);
        frame.setTitle("Humans vs Goblins");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(world);
        frame.add(hud);

//        for (int i = 1; i<=100; i++){
//            world.add(GenerateTile());
//        }

//        Add grid

        for (int i = 0; i < Land.grid.length; i++ ) {
            for (int k = 0; k < Land.grid.length; k++) {
                if (Land.grid[i][k] instanceof Humans){
                    world.add(RenderTiles.Human());
                } else if (Land.grid[i][k] instanceof Goblins) {
                    world.add(RenderTiles.Goblin());
                } else {
                    world.add(RenderTiles.EmptyTile());
                }
            }
        }

        frame.setVisible(true);
    }

}