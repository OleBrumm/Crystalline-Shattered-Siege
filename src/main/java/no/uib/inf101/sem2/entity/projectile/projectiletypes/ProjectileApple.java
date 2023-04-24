package no.uib.inf101.sem2.entity.projectile.projectiletypes;

import no.uib.inf101.sem2.entity.projectile.Projectile;

public class ProjectileApple extends Projectile {

    public ProjectileApple(double x, double y, double dir) {
        super(x, y, dir, 1, 30, 5, "NONE", 1);
    }

}
