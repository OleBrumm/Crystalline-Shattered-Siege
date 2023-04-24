package no.uib.inf101.sem2.entity.enemy;

import no.uib.inf101.sem2.screen.ScreenPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    private Enemy enemy;
    private List<ScreenPosition> waypoints;

    @BeforeEach
    public void setUp() {
        waypoints = List.of(
                new ScreenPosition(5, 0),
                new ScreenPosition(15, 0)
        );

        enemy = new Enemy(0,
                0,
                5,
                100,
                10,
                20,
                15,
                waypoints,
                "basic",
                0);
    }

    @Test
    public void testGetSpeed() {
        assertEquals(5, enemy.getSpeed());
    }

    @Test
    public void testSetSpeed() {
        enemy.setSpeed(10);
        assertEquals(10, enemy.getSpeed());
    }

    @Test
    public void testGetHealth() {
        assertEquals(100, enemy.getHealth());
    }

    @Test
    public void testSetHealth() {
        enemy.setHealth(80);
        assertEquals(80, enemy.getHealth());
    }

    @Test
    public void testGetReward() {
        assertEquals(10, enemy.getReward());
    }

    @Test
    public void testGetDamage() {
        assertEquals(20, enemy.getDamage());
    }

    @Test
    public void testGetType() {
        assertEquals("basic", enemy.getType());
    }

    @Test
    public void testIsDead() {
        assertFalse(enemy.isDead());
        enemy.setHealth(0);
        assertTrue(enemy.isDead());
    }

    @Test
    public void testMove() {
        enemy.move();
        assertEquals(5, enemy.getX());
        assertEquals(0, enemy.getY());
        assertEquals(1, enemy.getCurrentWaypointIndex());
    }

    @Test
    public void testHasReachedEnd() {
        assertFalse(enemy.hasReachedEnd());
        enemy.move();
        enemy.move();
        enemy.move();
        assertTrue(enemy.hasReachedEnd());
    }

    @Test
    public void testSetEffect() {
        assertEquals("NONE", enemy.getEffect());
        enemy.setEffect("SLOW");
        assertEquals("SLOW", enemy.getEffect());
        assertEquals((double) 5 / 3, enemy.getSpeed());
    }

    @Test
    public void testGetDistance() {
        double distance = enemy.getDistance(0, 0);
        assertEquals(0, distance);
        enemy.setX(3);
        enemy.setY(4);
        distance = enemy.getDistance(0, 0);
        assertEquals(5, distance);
    }


}
