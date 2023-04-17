package no.uib.inf101.sem2.entity.projectile.projectileTypes;

import no.uib.inf101.sem2.entity.projectile.Projectile;

public class ProjectileFire extends Projectile {
    public ProjectileFire(double x, double y, double dir) {
        super(x, y, dir, 1, 15, 5, "EXPLOSION");
    }
}
