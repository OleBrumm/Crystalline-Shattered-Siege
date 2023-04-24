package no.uib.inf101.sem2.entity.tower;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.enemy.enemytypes.EnemyRed;
import no.uib.inf101.sem2.grid.converter.CellPositionToPixelConverter;
import no.uib.inf101.sem2.model.TowerDefenseField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class TowerTest {

    private Tower tower;
    private Enemy enemy;
    private CellPositionToPixelConverter converter;

    @BeforeEach
    void setUp() {
        converter = new CellPositionToPixelConverter(new Rectangle2D.Double(0,0,800,640), new TowerDefenseField(10, 10), 0); // Assuming cell size is 50x50
        tower = new Tower(0, 0, 10, 100, 1, 100, "TREE", converter);
        enemy = new EnemyRed(0, 0, new ArrayList<>(), 0);
    }

    @Test
    void testSetTarget() {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        tower.setTarget(enemies);
        assertEquals(enemy, tower.getTarget());
    }

    @Test
    void testFire() {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);
        tower.setTarget(enemies);

        // Updating the time since last shot to allow firing
        tower.incrementTimeSinceLastShot(1.0);

        assertNotNull(tower.fire());
    }

}
