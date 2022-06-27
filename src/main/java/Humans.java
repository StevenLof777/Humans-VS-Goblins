public class Humans {
    private int strength = 4;
    private int health = 10;
    private String avatar = ":)";

    public String getAvatar() {
        return avatar;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void attack(Goblins g) {
        setHealth(getHealth() - g.getStrength());

        if (getHealth()<=0){
            System.out.println("Human was hit for " + g.getStrength() + " points. The player is dead.");
        } else if (g.getHealth()<=0) {
            System.out.println("The goblin was hit for " + strength  + " points. The goblin is dead");
        } else {
            System.out.println("Human hit for " + g.getStrength() + " points. Run or continure to fight (yes/no)");
        }
    }

}
