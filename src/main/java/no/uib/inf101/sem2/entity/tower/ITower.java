package no.uib.inf101.sem2.entity.tower;

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
     * Sets the cost of the tower
     *
     * @param cost the cost of the tower
     */
    void setCost(int cost);

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
}
