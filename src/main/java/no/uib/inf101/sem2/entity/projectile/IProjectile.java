package no.uib.inf101.sem2.entity.projectile;

public interface IProjectile {

    /**
     * Returns the x-coordinate of the projectile.
     *
     * @return the x-coordinate of the projectile
     */
    public double getX();

    /**
     * Returns the y-coordinate of the projectile.
     *
     * @return the y-coordinate of the projectile
     */
    public double getY();

    /**
     * Returns the direction of the projectile.
     *
     * @return the direction of the projectile
     */
    public double getDir();

    /**
     * Sets the x-coordinate of the projectile.
     *
     * @param x the new x-coordinate of the projectile
     */
    public void setX(double x);

    /**
     * Sets the y-coordinate of the projectile.
     *
     * @param y the new y-coordinate of the projectile
     */
    public void setY(double y);

    /**
     * Sets the direction of the projectile.
     *
     * @param dir the new direction of the projectile
     */
    public void setDir(double dir);

}
