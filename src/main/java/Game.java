public class Game {
    //    Setup
    public Humans human = new Humans();
    public Goblins goblin = new Goblins();
    public Land land = new Land();

    public Fight fight = new Fight();

    public void generateWorld(){
        human.setX_axis(4); human.setY_axis(0);

        goblin.setX_axis(4); goblin.setY_axis(1);

//        Player position
        land.humanPosition(human);

//        Goblin position
        land.goblinPosition(goblin);
    }

    public void play(){
        generateWorld();
        land.print();
        while (human.getHealth()>0){
            human.move();

//            Checks to see if the human is in the same spot as a goblin
            fight.initiate(human, goblin);

            land.humanPosition(human);
            land.print();
        }
    }

}
