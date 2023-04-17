package no.uib.inf101.sem2.entity.enemy;

import no.uib.inf101.sem2.screen.ScreenPosition;

public interface IEnemy {

    /**
     * Returns the speed of the enemy
     *
     * @return the speed of the enemy
     */
    double getSpeed();

    /**
     * Returns the health of the enemy
     *
     * @return the health of the enemy
     */
    int getHealth();

    /**
     * Returns the amount of gold the player earns for killing the enemy
     *
     * @return the reward of the enemy
     */
    int getReward();

    /**
     * Returns the level of the enemy
     *
     * @return the level of the enemy
     */
    int getDamage();

    /**
     * Returns the x coordinate of the enemy
     *
     * @return the x coordinate of the enemy
     */
    double getX();

    /**
     * Returns the y coordinate of the enemy
     *
     * @return the y coordinate of the enemy
     */
    double getY();

    /**
     * Sets the x coordinate of the enemy
     *
     * @param x the x coordinate of the enemy
     */
    void setX(double x);

    /**
     * Sets the y coordinate of the enemy
     *
     * @param y the y coordinate of the enemy
     */
    void setY(double y);

    /**
     * Returns the position of the enemy
     *
     * @return the position of the enemy
     */
    ScreenPosition getPosition();

    /**
     * Updates the position of the enemy
     */
    void move();

    /**
     * Returns the size of the enemy
     *
     * @return the size of the enemy
     */
    double getSize();

    /**
     * Sets the size of the enemy
     *
     * @param size the size of the enemy
     */
    void setSize(double size);

    /**
     * Sets the speed of the enemy
     *
     * @param speed the speed of the enemy
     */
    void setSpeed(double speed);

    /**
     * Sets the health of the enemy
     *
     * @param health the health of the enemy
     */
    void setHealth(int health);

    /**
     * Returns the type of the enemy
     *
     * @return the type of the enemy
     */
    String getType();

    /**
     * Returns the current waypoint index of the enemy
     *
     * @return the current waypoint index of the enemy
     */
    int getCurrentWaypointIndex();

    /**
     * Returns the effect of the enemy
     *
     * @return the effect of the enemy
     */
    String getEffect();

    /**
     * Sets the effect of the enemy
     *
     * @param effect the effect of the enemy
     */
    void setEffect(String effect);
}
