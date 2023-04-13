package no.uib.inf101.sem2.entity.enemy;

import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class EnemyBlue extends Enemy {
    public EnemyBlue(double x, double y, List<ScreenPosition> waypoints) {
        super(x, y, 1, 3, 25, 3, 30, waypoints);
    }
}
