public class Game {
    //    Setup
    public Humans human = new Humans();
    public Goblins goblinOne = new Goblins();
    public Goblins goblinTwo = new Goblins();
    public Goblins goblinThree = new Goblins();
    public Land land = new Land();
    public Fight fight = new Fight();
    public Object stats = "";

    public Object[][] generateWorld(){
        human.setX_axis(1); human.setY_axis(1);

        goblinOne.setX_axis(4); goblinOne.setY_axis(3);
        goblinTwo.setX_axis(2); goblinTwo.setY_axis(8);
        goblinThree.setX_axis(7); goblinThree.setY_axis(5);

//        Player position
        land.humanPosition(human);
//        Goblin position
        land.goblinPosition(goblinOne);
        land.goblinPosition(goblinTwo);
        land.goblinPosition(goblinThree);

        return land.print();
    }

    public void play(){

        generateWorld();

//        land.print();
        System.out.println("------------------------------");
        System.out.println(human.toString());
        System.out.println("------------------------------");

        while (human.getHealth()>0){
            human.move();

//            Checks to see if the human is in the same spot as a goblin
            fight.initiate(human, goblinOne);
            fight.initiate(human, goblinTwo);
            fight.initiate(human, goblinThree);
            land.humanPosition(human);
            land.print();
            System.out.println("------------------------------");
            System.out.println(human.toString());
            System.out.println("------------------------------");
        }
    }
}
