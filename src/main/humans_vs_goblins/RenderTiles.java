package main.humans_vs_goblins;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RenderTiles {
    public static Component Human(){
        ImageIcon knight = new ImageIcon("C:\\Users\\LofSt\\Desktop\\GenSpark Projects\\Humans-VS-Goblins\\images\\knight.gif");
        JButton tile = new JButton();
        tile.setIcon(knight);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        tile.setBorder(emptyBorder);
        tile.setBackground(new Color(139,69,19));
        return tile;
    }
    public static Component Goblin(){
        ImageIcon goblin = new ImageIcon("C:\\Users\\LofSt\\Desktop\\GenSpark Projects\\Humans-VS-Goblins\\images\\goblin.gif");

        JButton tile = new JButton();
        tile.setIcon(goblin);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        tile.setBorder(emptyBorder);
        tile.setBackground(new Color(139,69,19));
        return tile;
    }
    public static Component EmptyTile(){
        JButton tile = new JButton();
        Border emptyBorder = BorderFactory.createEmptyBorder();
        tile.setBorder(emptyBorder);
        tile.setBackground(new Color(139,69,19));
        return tile;
    }
}
