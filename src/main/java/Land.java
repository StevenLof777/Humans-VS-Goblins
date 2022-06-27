import java.util.Arrays;
import java.util.Scanner;
public class Land {
    public int y_axis = 0;
    public int x_axis = 4;

    Object[][] grid = {
            {"  ","  ","  ","  ","  ","  ","  ","  ","  ","  ",},
            {"  ","  ","  ","  ","  ","  ","  ","  ","  ","  ",},
            {"  ","  ","  ","  ","  ","  ","  ","  ","  ","  ",},
            {"  ","  ","  ","  ","  ","  ","  ","  ","  ","  ",},
            {"  ","  ","  ","  ","  ","  ","  ","  ","  ","  ",},
            {"  ","  ","  ","  ","  ","  ","  ","  ","  ","  ",},
            {"  ","  ","  ","  ","  ","  ","  ","  ","  ","  ",},
            {"  ","  ","  ","  ","  ","  ","  ","  ","  ","  ",},
            {"  ","  ","  ","  ","  ","  ","  ","  ","  ","  ",},
            {"  ","  ","  ","  ","  ","  ","  ","  ","  ","  ",}
    };

    public void humanPosition(int y, int x, Humans p){
//        if (grid[x_axis][y_axis].equals(g)){
//            System.out.println("Fight begins");
//        }

        try {
            grid[y][x] = p;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You have reached the end of the map");
        }
        if (y_axis >= 10){y_axis--;}
        if (y_axis < 0){y_axis++;}
        if (x_axis >= 10){x_axis++;}
        if (x_axis < 0){x_axis++;}
    }
    public void goblinPosition(int y, int x, Goblins g){
        try {
            grid[y][x] = g;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Cannot place goblin there");
        }
    }
    public void clearPosition(int y, int x){
        grid[y][x] = "  ";
    }

    public void north(Humans p){
        clearPosition(y_axis, x_axis);
        if(y_axis>=0){
            humanPosition(--y_axis, x_axis, p);
        } else {
            System.out.println("You have reached the end of the map");
        }
        print();
        System.out.println("You have moved north one slot");
    }

    public void south(Humans p){
        clearPosition(y_axis, x_axis);
        humanPosition(++y_axis, x_axis, p);
        print();
        System.out.println("You have moved south one slot");
    }

    public void east(Humans p){
        clearPosition(y_axis, x_axis);
        if(y_axis>=0){
            humanPosition(y_axis, ++x_axis, p);
        } else {
            System.out.println("You have reached the end of the map");
        }
        print();
        System.out.println("You have moved east one slot");
    }

    public void west(Humans p){
        clearPosition(y_axis, x_axis);
        humanPosition(y_axis, --x_axis, p);
        print();
        System.out.println("You have moved west one slot");
    }

    public void print(){
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
    }
    public void move(Humans p){
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();
        switch (key) {
            case "w":
                north(p);
                break;
            case "s":
                south(p);
                break;
            case "a":
                west(p);
                break;
            case "d":
                east(p);
                break;

        }
    }
}

