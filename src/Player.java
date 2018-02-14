public class Player {

    private String name;
    private Item[] items;

    private double attack;
    private double defense;

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    private double health;
    private double level;

    public String getName() {
        return name;
    }

    public Item[] getItems() {
        return items;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public double getHealth() {
        return health;
    }

    public double getLevel() {
        return level;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setLevel(double level) {
        this.level = level;
    }
}
