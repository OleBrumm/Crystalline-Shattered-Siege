package no.uib.inf101.sem2.entity.tower;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.projectile.Projectile;
import no.uib.inf101.sem2.entity.projectile.projectiletypes.ProjectileApple;
import no.uib.inf101.sem2.entity.projectile.projectiletypes.ProjectileFire;
import no.uib.inf101.sem2.entity.projectile.projectiletypes.ProjectileIce;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.converter.CellPositionToPixelConverter;
import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class Tower implements ITower {

    private int damage;
    private int range;
    private final int cost;
    private int level;
    private int x, y;
    private final String type;
    public Enemy target;
    public CellPositionToPixelConverter converter;
    private final double fireRate;
    private double timeSinceLastShot;

    public Tower(int x, int y, int damage, int range, int fireRate, int cost, String type, CellPositionToPixelConverter converter) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.range = range;
        this.cost = cost;
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
        this.y = pos.row();
        this.x = pos.col();
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
            System.out.println("Firing at " + target + " at " + target.getPosition());
            double direction = Math.toDegrees(Math.atan2(target.getPosition().y() - getScreenPosition().y(), target.getPosition().x() - getScreenPosition().x()));
            switch (type) {
                case "TREE" -> {
                    System.out.println("Tree tower fired at " + direction + " degrees");
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
