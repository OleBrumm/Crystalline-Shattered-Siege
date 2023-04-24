package no.uib.inf101.sem2.entity.enemy.enemytypes;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class EnemyYellow extends Enemy {
    public EnemyYellow(double x, double y, List<ScreenPosition> waypoints, int currentWaypointIndex) {
        super(x, y, 1.8, 1, 4, 3, 25, waypoints, "YELLOW", currentWaypointIndex);
    }
}

