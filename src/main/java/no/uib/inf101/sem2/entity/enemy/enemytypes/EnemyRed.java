package no.uib.inf101.sem2.entity.enemy.enemytypes;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class EnemyRed extends Enemy {
    public EnemyRed(double x, double y, List<ScreenPosition> waypoints, int currentWaypointIndex) {
        super(x, y, 1, 1, 1, 1, 25, waypoints, "RED", currentWaypointIndex);
    }
}
