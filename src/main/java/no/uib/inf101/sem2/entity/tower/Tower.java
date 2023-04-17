package no.uib.inf101.sem2.entity.tower;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.projectile.Projectile;
import no.uib.inf101.sem2.entity.projectile.projectileTypes.ProjectileApple;
import no.uib.inf101.sem2.entity.projectile.projectileTypes.ProjectileFire;
import no.uib.inf101.sem2.entity.projectile.projectileTypes.ProjectileIce;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.CellPositionToPixelConverter;
import no.uib.inf101.sem2.screen.ScreenPosition;

import javax.swing.*;
import java.util.List;

public class Tower implements ITower {

    private int damage;
    private int range;
    private int cost;
    private int level;
    private int upgradeCost;
    private int x, y;
    private final String type;
    public Enemy target;
    public CellPositionToPixelConverter converter;
    private final double fireRate;
    private double timeSinceLastShot;

    public Tower(int x, int y, int damage, int range, int fireRate, int cost, int upgradeCost, String type, CellPositionToPixelConverter converter) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.range = range;
        this.cost = cost;
        this.upgradeCost = upgradeCost;
        this.type = type;
        this.target = null;
        this.converter = converter;

        this.timeSinceLastShot = 0;
        this.fireRate = fireRate;
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
        setLevel(getLevel() + 1);
        setDamage(getDamage() + 1);
        setRange(getRange() + 20);
        setUpgradeCost(getUpgradeCost() + 100);
    }

    public String getType() {
        return this.type;
    }

    public CellPosition getCellPosition() {
        return new CellPosition(x, y);
    }
    public ScreenPosition getScreenPosition() {
        return converter.getCenterForCell(getCellPosition());
    }

    public void setPosition(CellPosition pos) {
        this.y = pos.col();
        this.x = pos.row();
    }

    public void setTarget(List<Enemy> enemies) {
        Enemy closestEnemy = null;
        double minDistance = Double.MAX_VALUE;
        ScreenPosition towerPos = converter.getCenterForCell(getCellPosition());

        for (Enemy enemy : enemies) {
            double distance = towerPos.distanceTo(enemy.getPosition());
            if (distance < minDistance && distance <= this.getRange()) {
                minDistance = distance;
                closestEnemy = enemy;
            }
        }

        this.target = closestEnemy;
    }

    @Override
    public Projectile fire() {
        if (canFire()){
            resetTimeSinceLastShot();
            double direction = Math.toDegrees(Math.atan2(target.getPosition().y() - getScreenPosition().y(), target.getPosition().x() - getScreenPosition().x()));
            System.out.println("Firing at " + target + " with damage " + this.damage + " and range " + this.range + " and type " + this.type + " and direction " + direction);
            switch (type) {
                case "TREE" -> {
                    return new ProjectileApple(getScreenPosition().x(), getScreenPosition().y(), direction);
                }
                case "ICE" -> {
                    return new ProjectileIce(getScreenPosition().x(), getScreenPosition().y(), direction);
                }
                case "FIRE" -> {
                    return new ProjectileFire(getScreenPosition().x(), getScreenPosition().y(), direction);
                }
            }
        } else {
            return null;
        }
        return null;
    }

    @Override
    public Enemy getTarget() {
        return target;
    }

    public void incrementTimeSinceLastShot(double deltaTime) {
        this.timeSinceLastShot += deltaTime;
    }

    public boolean canFire() {
        return this.timeSinceLastShot >= this.fireRate;
    }

    public void resetTimeSinceLastShot() {
        this.timeSinceLastShot = 0;
    }

    @Override
    public double getFireRate() {
        return this.fireRate;
    }
}
