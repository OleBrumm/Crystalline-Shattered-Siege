package no.uib.inf101.sem2.entity.projectile.projectileTypes;

import no.uib.inf101.sem2.entity.projectile.Projectile;

public class ProjectileIce extends Projectile {

    public ProjectileIce(double x, double y, double dir) {
        super(x, y, dir, 2, 20, 2, "SLOW");
    }

}
