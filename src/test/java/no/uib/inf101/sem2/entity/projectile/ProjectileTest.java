package no.uib.inf101.sem2.entity.projectile;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.enemy.enemytypes.EnemyRed;
import no.uib.inf101.sem2.screen.ScreenPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectileTest {

    private Projectile projectile;
    private Enemy enemy;
    private List<ScreenPosition> waypoints;

    @BeforeEach
    void setUp() {
        waypoints = new ArrayList<>();
        waypoints.add(new ScreenPosition(0, 0));
        waypoints.add(new ScreenPosition(100, 0));

        projectile = new Projectile(0, 0, 0, 10, 5, 1, "NONE", 1);
        enemy = new EnemyRed(0, 0, waypoints, 0);
    }

    @Test
    void testMove() {
        double initialX = projectile.getX();
        double initialY = projectile.getY();
        projectile.move();
        assertEquals(initialX + 1, projectile.getX());
        assertEquals(initialY, projectile.getY());
    }

    @Test
    void testIsDead() {
        projectile.setHealth(0);
        assertTrue(projectile.isDead());
    }

    @Test
    void testCollidesWith() {
        assertFalse(projectile.collidesWith(enemy));
        projectile.setX(enemy.getX());
        projectile.setY(enemy.getY());
        assertTrue(projectile.collidesWith(enemy));
    }

}
