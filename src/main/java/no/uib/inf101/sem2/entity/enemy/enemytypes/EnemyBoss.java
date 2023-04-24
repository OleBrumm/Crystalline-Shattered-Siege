package no.uib.inf101.sem2.entity.enemy.enemytypes;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class EnemyBoss extends Enemy {

    public EnemyBoss(double x, double y, List<ScreenPosition> waypoints, int currentWaypointIndex) {
        super(x, y, 1, 300, 1000, 100, 75, waypoints, "BOSS", currentWaypointIndex);
    }

}
