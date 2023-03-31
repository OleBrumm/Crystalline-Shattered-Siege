package no.uib.inf101.sem2.entity.enemy;

public class Enemy1 implements IEnemy {

    @Override
    public int getSpeed() {
        return 3;
    }

    @Override
    public int getHealth() {
        return 1;
    }

    @Override
    public int getReward() {
        return 5;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public String getSprite() {
        return "Enemies/enemy1.png";
    }
}
