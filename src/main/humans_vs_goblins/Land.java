package main.humans_vs_goblins;

import java.util.Scanner;
public class Land {
    public static Object[][] grid = {
//            {" ^ "," ^ "," ^ "," ^ "," ^ "," ^ "," ^ "," ^ "," ^ "," ^ "," ^ ",},
//            {" ^ ","   ","   ","   ","   ","   ","   ","   ","   "," ^ ",},
//            {" ^ ","   ","   ","   ","   ","   ","   ","   ","   "," ^ ",},
//            {" ^ ","   ","   ","   ","   ","   ","   ","   ","   "," ^ ",},
//            {" ^ ","   ","   ","   ","   ","   ","   ","   ","   "," ^ ",},
//            {" ^ ","   ","   ","   ","   ","   ","   ","   ","   "," ^ ",},
//            {" ^ ","   ","   ","   ","   ","   ","   ","   ","   "," ^ ",},
//            {" ^ ","   ","   ","   ","   ","   ","   ","   ","   "," ^ ",},
//            {" ^ ","   ","   ","   ","   ","   ","   ","   ","   "," ^ ",},
//            {" ^ "," ^ "," ^ "," ^ "," ^ "," ^ "," ^ "," ^ "," ^ "," ^ ",},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 0,},
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 0,},
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 0,},
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 0,},
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 0,},
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 0,},
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 0,},
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 0,},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
    };
    public void humanPosition(Humans p){
        try {
            grid[p.getClear_y()][p.getClear_x()] = "   ";
            grid[p.getY_axis()][p.getX_axis()] = p;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You have reached the end of the map");
        }
    }
    public void goblinPosition(Goblins g){
        try {
            grid[g.getY_axis()][g.getX_axis()] = g;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Cannot place goblin there");
        }
    }
    public Object[][] print(){
//        Print out the land with 10 x 10 tiles
        for (int i = 0; i < grid.length; i++ ) {
            for (int k = 0; k < grid.length; k++) {
                if (grid[i][k] instanceof Humans){
                    System.out.print(((Humans) grid[i][k]).getAvatar());
                } else if (grid[i][k] instanceof Goblins) {
                    System.out.print(((Goblins) grid[i][k]).getAvatar());
                } else {
                    System.out.print(grid[i][k]);
                }
            }
            System.out.println();
        }
        return grid;
    }

}

