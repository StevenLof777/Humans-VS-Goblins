public class Humans {
    private int strength = 4;
    private int health = 10;
    private String avatar = ":)";

    public void Player(int strength, int health, String avatar){
        this.avatar = avatar;
        this.strength = strength;
        this.health = health;
    }

    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
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


}
