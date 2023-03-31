package no.uib.inf101.sem2.entity.enemy;

public class Enemy3 implements IEnemy {

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public int getHealth() {
        return 5;
    }

    @Override
    public int getReward() {
        return 15;
    }

    @Override
    public int getLevel() {
        return 3;
    }

    @Override
    public String getSprite() {
        return "Enemies/enemy3.png";
    }
}

