public class Game {
    //    Setup
    public Humans human = new Humans();
    public Goblins golbin1 = new Goblins();
    public Goblins golbin2 = new Goblins();
    public Goblins golbin3 = new Goblins();
    public Land land = new Land();


    public void play(){
//        human.attack(golbin1);


//        Player
        land.humanPosition(0,4, human);
//        Goblins
        land.goblinPosition(1,4, golbin1);
        land.goblinPosition(1,8, golbin2);
        land.goblinPosition(7,1, golbin3);
        land.print();
        while (human.getHealth()>0){
            land.move(human);
        }

    }

}
