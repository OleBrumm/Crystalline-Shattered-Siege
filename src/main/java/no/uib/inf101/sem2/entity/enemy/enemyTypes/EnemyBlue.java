package no.uib.inf101.sem2.entity.enemy.enemyTypes;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class EnemyBlue extends Enemy {
    public EnemyBlue(double x, double y, List<ScreenPosition> waypoints, int currentWaypointIndex, double speed) {
        super(x, y, speed, 1, 25, 3, 30, waypoints, "BLUE", currentWaypointIndex);
    }
}
