package no.uib.inf101.sem2.entity.enemy;

public interface IEnemy {
    /**
     * Returns the speed of the enemy
     * @return the speed of the enemy
     */
    public int getSpeed();

    /**
     * Returns the health of the enemy
     * @return the health of the enemy
     */
    public int getHealth();

    /**
     * Returns the amount of gold the player earns for killing the enemy
     * @return the reward of the enemy
     */
    public int getReward();

    /**
     * Returns the level of the enemy
     * @return the level of the enemy
     */
    public int getLevel();

    /**
     * Returns the sprite of the enemy
     * @return the sprite of the enemy
     */
    public String getSprite();

    /**
     * Returns the enemy as a String to read
     * @return the enemy as a String to read
     */
    public String toString();
}
