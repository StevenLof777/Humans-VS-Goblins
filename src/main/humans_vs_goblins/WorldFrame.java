package main.humans_vs_goblins;

import javax.swing.*;
import java.awt.*;

public class WorldFrame extends JFrame {

    public Component GenerateTile(){
        ImageIcon icon = new ImageIcon("C:\\Users\\LofSt\\Desktop\\GenSpark Projects\\Humans-VS-Goblins\\images\\goblin.gif");

        JButton tile = new JButton();
        tile.setIcon(icon);
        tile.setBackground(Color.green);
        return tile;
    }

    public WorldFrame(){

        JPanel world = new JPanel();
        world.setBackground(Color.red);
        world.setBounds(110,10,500,500);
        world.setLayout(new GridLayout(10,10));

        JPanel hud = new JPanel();
        hud.setBackground(Color.gray);
        hud.setBounds(0,520,720,175);
        hud.setLayout(null);

        JFrame frame = new JFrame();
        frame.setSize(720,720);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(world);
        frame.add(hud);



        for (int i = 1; i<=100; i++){
            world.add(GenerateTile());
        }
//        Add grid

//        for (int i = 0; i < Land.grid.length; i++ ) {
//            for (int k = 0; k < grid.length; k++) {
//                if (Land.grid[i][k] instanceof Humans){
////                    System.out.print(((Humans) Land.grid[i][k]).getAvatar());
//
//                } else if (Land.grid[i][k] instanceof Goblins) {
////                    System.out.print(((Goblins) Land.grid[i][k]).getAvatar());
//                } else {
//                    System.out.print(Land.grid[i][k]);
//                }
//            }
//            System.out.println();
//        }
        frame.setVisible(true);
    }

}