package no.uib.inf101.sem2.entity.enemy;

import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class EnemyRed extends Enemy {
    public EnemyRed(double x, double y, List<ScreenPosition> waypoints) {
        super(x, y, 0.5, 1, 10, 0, 25, waypoints);
    }
}
