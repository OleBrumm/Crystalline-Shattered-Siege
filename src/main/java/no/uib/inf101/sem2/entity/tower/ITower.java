package no.uib.inf101.sem2.entity.tower;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.projectile.Projectile;

import java.util.List;

public interface ITower {

    /**
     * Returns the range of the tower
     *
     * @return the range of the tower
     */
    int getRange();

    /**
     * Sets the range of the tower
     *
     * @param range the range of the tower
     */
    void setRange(int range);

    /**
     * Returns the damage output of the tower
     *
     * @return the damage output of the tower
     */
    int getDamage();

    /**
     * Sets the damage output of the tower
     *
     * @param damage the damage output of the tower
     */
    void setDamage(int damage);

    /**
     * Returns the cost of the tower
     *
     * @return the cost of the tower
     */
    int getCost();


    /**
     * Returns the level of the tower
     *
     * @return the level of the tower
     */
    int getLevel();

    /**
     * Sets the level of the tower
     *
     * @param level the level of the tower
     */
    void setLevel(int level);

    /**
     * Returns the upgrade cost of the tower
     *
     * @return the upgrade cost of the tower
     */
    int getUpgradeCost();

    /**
     * Sets the upgrade cost of the tower
     *
     * @param upgradeCost the upgrade cost of the tower
     */
    void setUpgradeCost(int upgradeCost);

    /**
     * Upgrades the tower
     */
    void upgrade();

    /**
     * Fires a projectile
     *
     * @return the projectile
     */
    Projectile fire();

    /**
     * Returns the target of the tower
     * @return the target of the tower
     */
    Enemy getTarget();

    /**
     * Sets the target of the tower based on the enemies
     * The target is the enemy that is closest the tower
     *
     * @param enemies the target of the tower
     */
    void setTarget(List<Enemy> enemies);

    /**
     * Returns the fire rate of the tower
     *
     * @return the fire rate of the tower
     */
    double getFireRate();
}
