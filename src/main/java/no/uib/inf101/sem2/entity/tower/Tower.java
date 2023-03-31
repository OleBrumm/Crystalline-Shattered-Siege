package no.uib.inf101.sem2.entity.tower;

public class Tower implements ITower {

    public final String typeOfTower;
    public final String sprite;
    public int damage;
    public int range;
    public int cost;
    public int level;
    public int upgradeCost;

    public Tower(String typeOfTower, int damage, int range, int cost, int upgradeCost, String sprite) {
        this.typeOfTower = typeOfTower;
        this.damage = damage;
        this.range = range;
        this.cost = cost;
        this.upgradeCost = upgradeCost;
        this.sprite = sprite;
    }

    protected Tower newTower(String typeOfTower){
        return switch (typeOfTower) {
            case "FireTower" -> new Tower("FireTower", 1, 50, 200, 0, "Towers/FireTower.png");
            case "TreeTower" -> new Tower("TreeTower", 1, 50, 200, 0, "Towers/TreeTower.png");
            case "IceTower" -> new Tower("IceTower", 1, 50, 200, 0, "Towers/IceTower.png");
            default -> null;
        };
    }

    @Override
    public int getRange() {
        return this.range;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public int getCost() {
        return this.cost;
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
    public int getUpgradeCost() {
        return this.upgradeCost;
    }

    @Override
    public void upgrade() {
        setLevel(getLevel() + 1);
        setDamage(getDamage() + 1);
        setRange(getRange() + 1);
        setUpgradeCost(getUpgradeCost() + 100);
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }
}
