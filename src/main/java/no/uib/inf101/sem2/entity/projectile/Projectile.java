package no.uib.inf101.sem2.entity.projectile;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.screen.ScreenPosition;

public class Projectile implements IProjectile {

    private double x;
    private double y;
    private final double dir;
    private final int damage;
    private final int size;
    private final double speed;
    private final String effect;
    private final int effectRadius;
    private final int effectDamage;

    public Projectile(double x, double y, double dir, int damage, int size, int speed, String effect) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.damage = damage;
        this.size = size;
        this.speed = speed;
        this.effect = effect;
        this.effectRadius = 50;
        this.effectDamage = 100;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public double getDir() {
        return dir;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getEffectRadius() {
        return effectRadius;
    }

    @Override
    public int getEffectDamage() {
        return effectDamage;
    }

    public ScreenPosition getPosition() {
        return new ScreenPosition(x, y);
    }

    public String getEffect() {
    	return effect;
    }

    public void move() {
        x += Math.cos(Math.toRadians(dir)) * speed;
        y += Math.sin(Math.toRadians(dir)) * speed;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public boolean collidesWith(Enemy enemy) {
        return enemy.getPosition().distanceTo(getPosition()) < enemy.getSize();
    }
}
