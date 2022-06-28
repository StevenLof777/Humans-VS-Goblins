import java.util.Scanner;

public class Humans {
    private int strength = 4;
    private int health = 10;
    private String avatar = " :)";
    private int x_axis = 0;
    private int y_axis = 0;
    public int getY_axis() {
        return y_axis;
    }
    public void setY_axis(int y_axis) {
        this.y_axis = y_axis;
    }
    public int getX_axis() {
        return x_axis;
    }
    public void setX_axis(int x_axis) {
        this.x_axis = x_axis;
    }
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
            System.out.println("Human hit for " + g.getStrength() + " points. Run or continue to fight (yes/no)");
        }
    }

    public void move(){
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();
        switch (key) {
//            case "w":
//                north(p);
//                break;
            case "s":
                setY_axis(getY_axis()+1);
                break;
//            case "a":
//                west(p);
//                break;
//            case "d":
//                east(p);
//                break;
        }
    }

}
