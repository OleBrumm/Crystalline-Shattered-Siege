package no.uib.inf101.sem2.entity.enemy;

import no.uib.inf101.sem2.grid.CellPosition;

public class Enemy implements IEnemy {

    public int x;
    public int y;
    public int speed;
    public int health;
    public int reward;
    public int level;


    public Enemy(int x, int y, int speed, int health, int reward, int level) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.health = health;
        this.reward = reward;
        this.level = level;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getReward() {
        return reward;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public CellPosition getPosition() {
        return new CellPosition(x, y);
    }

}
