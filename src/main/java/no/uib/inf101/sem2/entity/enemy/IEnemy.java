package no.uib.inf101.sem2.entity.enemy;

public interface IEnemy {

    /**
     * Returns the speed of the enemy
     *
     * @return the speed of the enemy
     */
    public int getSpeed();

    /**
     * Returns the health of the enemy
     *
     * @return the health of the enemy
     */
    public int getHealth();

    /**
     * Returns the amount of gold the player earns for killing the enemy
     *
     * @return the reward of the enemy
     */
    public int getReward();

    /**
     * Returns the level of the enemy
     *
     * @return the level of the enemy
     */
    public int getLevel();

    /**
     * Returns the x coordinate of the enemy
     *
     * @return the x coordinate of the enemy
     */
    public int getX();

    /**
     * Returns the y coordinate of the enemy
     *
     * @return the y coordinate of the enemy
     */
    public int getY();

    /**
     * Sets the x coordinate of the enemy
     *
     * @param x the x coordinate of the enemy
     */
    public void setX(int x);

    /**
     * Sets the y coordinate of the enemy
     *
     * @param y the y coordinate of the enemy
     */
    public void setY(int y);

}
