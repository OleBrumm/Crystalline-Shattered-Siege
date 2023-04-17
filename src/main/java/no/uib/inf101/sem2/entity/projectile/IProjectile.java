package no.uib.inf101.sem2.entity.projectile;

import no.uib.inf101.sem2.entity.enemy.Enemy;

public interface IProjectile {

    /**
     * Returns the x-coordinate of the projectile.
     *
     * @return the x-coordinate of the projectile
     */
    double getX();

    /**
     * Returns the y-coordinate of the projectile.
     *
     * @return the y-coordinate of the projectile
     */
    double getY();

    /**
     * Sets the x-coordinate of the projectile.
     *
     * @param x the new x-coordinate of the projectile
     */
    void setX(double x);

    /**
     * Sets the y-coordinate of the projectile.
     *
     * @param y the new y-coordinate of the projectile
     */
    void setY(double y);

    /**
     * Gets the damage of the projectile.
     * @return the damage of the projectile
     */
    int getDamage();

    /**
     * Checks if the projectile collides with the enemy.
     */
    boolean collidesWith(Enemy enemy);

    /**
     * Gets the size of the projectile.
     *
     * @return the size of the projectile
     */
    int getSize();

    /**
     * Gets the radius of the effect of the projectile.
     * Used in the splash damage of the fire tower
     *
     * @return the speed of the projectile
     */
    int getEffectRadius();

    /**
     * Gets the damage of the effect of the projectile.
     * Used in the splash damage of the fire tower
     *
     * @return the speed of the projectile
     */
    int getEffectDamage();
}
