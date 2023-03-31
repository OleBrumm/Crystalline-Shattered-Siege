package no.uib.inf101.sem2.entity.tower;

public class IceTower extends Tower {

    public int level;
    public int cost;
    public int range;
    public int damage;
    public int upgradeCost;
    public String sprite;

    public IceTower() {
        super("IceTower", 1, 50, 200, 0, "icetower.png");
        this.level = 1;
        this.cost = 200;
        this.range = 50;
        this.damage = 1;
        this.upgradeCost = 0;
        this.sprite = "icetower.png";
    }

    @Override
    public int getRange() {
        return this.range;
    }

    @Override
    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String getSprite() {
        return this.sprite;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int getUpgradeCost() {
        return this.upgradeCost;
    }

    @Override
    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    @Override
    public void upgrade() {
        this.level++;
        this.damage++;
        this.range++;
        this.upgradeCost += 100;
    }

    @Override
    public String toString() {
        return "Name: " + this.getClass().getSimpleName() + "" + "level: " + this.level + " cost: " + this.cost + " range: " + this.range + " damage: " + this.damage + " upgradeCost: " + this.upgradeCost + " sprite: " + this.sprite;
    }

}
