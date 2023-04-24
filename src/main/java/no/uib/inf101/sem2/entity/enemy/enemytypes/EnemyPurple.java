package no.uib.inf101.sem2.entity.enemy.enemytypes;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class EnemyPurple extends Enemy {

    public EnemyPurple(double x, double y, List<ScreenPosition> waypoints, int currentWaypointIndex) {
        super(x, y, 3.5, 1, 5, 5, 30, waypoints, "PURPLE", currentWaypointIndex);
    }

}
