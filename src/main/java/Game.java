import java.util.Scanner;

public class Game {
    //    Setup
    public Humans human = new Humans();
    public Land land = new Land();

    public void play(){
        land.position(0,4);
        while (human.getHealth()>0){
            land.move();
        }
    }

}
