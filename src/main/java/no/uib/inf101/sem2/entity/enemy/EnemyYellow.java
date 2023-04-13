package no.uib.inf101.sem2.entity.enemy;

import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public class EnemyYellow extends Enemy {
    public EnemyYellow(double x, double y, List<ScreenPosition> waypoints) {
        super(x, y, 1.5, 5, 50, 5, 25, waypoints);
    }
}

