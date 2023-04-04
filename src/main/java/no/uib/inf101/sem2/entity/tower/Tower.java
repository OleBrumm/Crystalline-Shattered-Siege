package no.uib.inf101.sem2.entity.tower;

import no.uib.inf101.sem2.grid.CellPosition;

public class Tower implements ITower {

    public int damage;
    public int range;
    public int cost;
    public int level;
    public int upgradeCost;
    public int x, y;

    public Tower(int x, int y, int damage, int range, int cost, int upgradeCost) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.range = range;
        this.cost = cost;
        this.upgradeCost = upgradeCost;
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
        setRange(getRange() + 20);
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

    public CellPosition getPosition() {
        return new CellPosition(x, y);
    }
}
