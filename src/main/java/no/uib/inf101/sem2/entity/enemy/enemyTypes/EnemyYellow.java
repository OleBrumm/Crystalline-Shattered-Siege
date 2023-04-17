package no.uib.inf101.sem2.entity.enemy.enemyTypes;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class EnemyYellow extends Enemy {
    public EnemyYellow(double x, double y, List<ScreenPosition> waypoints, int currentWaypointIndex, double speed) {
        super(x, y, speed, 1, 50, 5, 25, waypoints, "YELLOW", currentWaypointIndex);
    }
}

