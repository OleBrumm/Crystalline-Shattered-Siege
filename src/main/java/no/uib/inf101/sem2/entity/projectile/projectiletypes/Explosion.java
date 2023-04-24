package no.uib.inf101.sem2.entity.projectile.projectiletypes;

public class Explosion {


    private final double x,y;
    private final int size;
    private int duration;

    public Explosion(double x, double y, int size, int duration) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.duration = duration;
    }

    public void update() {
        duration--;
    }

    public boolean isFinished() {
        return duration <= 0;
    }

    public int getSize() {
        return size;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
