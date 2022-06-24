import java.util.Arrays;
import java.util.Scanner;
public class Land {
    Humans human = new Humans();

    String p = human.getAvatar();

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

    public void position(int y, int x){
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
    public void clearPosition(int y, int x){
        grid[y][x] = "  ";
    }

    public void north(){
        clearPosition(y_axis, x_axis);
        if(y_axis>=0){
            position(--y_axis, x_axis);
        } else {

            System.out.println("You have reached the end of the map");
        }
        System.out.println("You have moved north one slot");
    }

    public void south(){
        clearPosition(y_axis, x_axis);
        position(++y_axis, x_axis);
        System.out.println("You have moved north one slot");
    }
    public void print(){
        for (int i = 0; i < grid.length; i++ ) {
            System.out.println(Arrays.toString(grid[i]));
        }
    }
    public void move(){
        print();
        System.out.println("y axis = " + y_axis);
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();
        switch (key) {
            case "w":
                north();
                break;
            case "s":
                south();
                break;

        }
    }
}
