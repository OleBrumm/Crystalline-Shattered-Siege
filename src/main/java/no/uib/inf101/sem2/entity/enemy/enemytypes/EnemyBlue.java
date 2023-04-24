package no.uib.inf101.sem2.entity.enemy.enemytypes;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class EnemyBlue extends Enemy {
    public EnemyBlue(double x, double y, List<ScreenPosition> waypoints, int currentWaypointIndex) {
        super(x, y, 1.4, 1, 2, 2, 30, waypoints, "BLUE", currentWaypointIndex);
    }
}
