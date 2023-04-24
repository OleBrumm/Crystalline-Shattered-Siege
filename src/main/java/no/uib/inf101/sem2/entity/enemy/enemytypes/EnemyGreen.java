package no.uib.inf101.sem2.entity.enemy.enemytypes;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class EnemyGreen extends Enemy {

    public EnemyGreen(double x, double y, List<ScreenPosition> waypoints, int currentWaypointIndex) {
        super(x, y, 3.2, 1, 4, 4, 30, waypoints, "GREEN", currentWaypointIndex);
    }

}
