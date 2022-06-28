import java.util.Scanner;

public class Fight {
    public Humans attack(Humans h, Goblins g) {
        Scanner sc = new Scanner(System.in);

        String input = "";

        int max = 2, min = -2;
        int range = max - min + 1;

        int rand = (int)(Math.random() * range) + min;

        h.setHealth(h.getHealth() - (g.getStrength() + rand));
        g.setHealth(g.getHealth() - (h.getStrength() + rand));

        if (h.getHealth()<=0){
            System.out.println("Human was hit for " + g.getStrength() + " points. The player is dead.");
        } else if (g.getHealth()<=0) {
            System.out.println("The goblin was hit for " + h.getStrength() + " points. The goblin is dead");
        } else {
            System.out.println("Human hit for " + g.getStrength() + " points" +
                    "\nGoblin hit for " + h.getStrength() + " points" +
                    "\nGoblin has " + g.getHealth() + " hp" +
                    "\nRun or continue to fight (yes/no)");

            System.out.println("------------------------------");
            System.out.print("HP: " + h.getHealth());
            System.out.println(" | STR: " + h.getStrength());
            System.out.println("------------------------------");

            input = sc.next();

            if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")){
                attack(h, g);
            }
        }
        return h;
    }
    public void initiate(Humans h, Goblins g){
        if (h.getY_axis() == g.getY_axis()){
            attack(h, g);
        }
    }
}
