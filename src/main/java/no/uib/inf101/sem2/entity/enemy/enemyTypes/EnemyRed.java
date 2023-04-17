package no.uib.inf101.sem2.entity.enemy.enemyTypes;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class EnemyRed extends Enemy {
    public EnemyRed(double x, double y, List<ScreenPosition> waypoints, int currentWaypointIndex, double speed) {
        super(x, y, speed, 1, 10, 1, 25, waypoints, "RED", currentWaypointIndex);
    }
}
