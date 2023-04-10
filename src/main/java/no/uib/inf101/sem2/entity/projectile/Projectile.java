package no.uib.inf101.sem2.entity.projectile;

import no.uib.inf101.sem2.screen.ScreenPosition;

public class Projectile implements IProjectile {

    private double x;
    private double y;
    private double dir;

    public Projectile(double x, double y, double dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
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

    public void setDir(double dir) {
        this.dir = dir;
    }

    public ScreenPosition getPosition() {
        return new ScreenPosition(x, y);
    }

    public void move() {
        x += Math.cos(Math.toRadians(dir));
        y += Math.sin(Math.toRadians(dir));
    }

}
