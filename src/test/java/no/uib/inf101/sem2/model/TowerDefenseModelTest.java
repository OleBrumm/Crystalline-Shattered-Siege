package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.enemy.enemytypes.EnemyRed;
import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.entity.tower.towertypes.TowerFire;
import no.uib.inf101.sem2.entity.tower.towertypes.TowerTree;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.screen.ScreenPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TowerDefenseModelTest {

    private TowerDefenseModel towerDefenseModel;

    @BeforeEach
    void setUp() {
        TowerDefenseField field = new TowerDefenseField(10, 10);
        towerDefenseModel = new TowerDefenseModel(field);
    }

    @Test
    void testInitialGameState() {
        assertEquals(GameState.MAIN_MENU, towerDefenseModel.getGameState());
        assertEquals(1, towerDefenseModel.getWave());
        assertEquals(100, towerDefenseModel.getLives());
        assertEquals(800, towerDefenseModel.getGold());
        assertTrue(towerDefenseModel.getEnemies().isEmpty());
        assertTrue(towerDefenseModel.getTowers().isEmpty());
        assertTrue(towerDefenseModel.getProjectiles().isEmpty());
        assertTrue(towerDefenseModel.getExplosions().isEmpty());
    }

    @Test
    void testEnemyMovement() {
        List<ScreenPosition> waypoints = towerDefenseModel.getWaypoints();
        ScreenPosition spawnPosition = waypoints.get(0);
        Enemy enemy = new EnemyRed(spawnPosition.x(), spawnPosition.y(), waypoints, 0);
        towerDefenseModel.spawnEnemy(enemy);

        for (int i = 0; i < 10; i++) {
            towerDefenseModel.clockTick();
        }

        ScreenPosition enemyPosition = enemy.getPosition();
        assertNotEquals(waypoints.get(1), new ScreenPosition(enemyPosition.x(), enemyPosition.y()));
    }

    @Test
    void testTowerValidPlacement() {
        // Create a tower at position (9, 1)
        CellPosition position1 = new CellPosition(9, 1);

        // Position should be valid
        assertTrue(towerDefenseModel.isTowerPlacementValid(position1));

        Tower tower1 = new Tower(position1.col(), position1.row(), 1, 1, 1, 800, "", null);
        towerDefenseModel.addTower(tower1);

        // Tower should be placed
        assertEquals(tower1, towerDefenseModel.getTowers().get(0));

        // Tower should be remove gold from player
        // As the tower costs 800 and the player starts with 800 gold,
        // the player should have 0 gold after placing the tower
        assertEquals(0, towerDefenseModel.getGold());
    }

    @Test
    void testAddTowerNotEnoughGold() {
        towerDefenseModel.setGold(0);
        Tower tower = new TowerFire(0, 0, null);
        towerDefenseModel.addTower(tower);
        List<Tower> towers = towerDefenseModel.getTowers();
        assertTrue(towers.isEmpty());
    }

    @Test
    void testTowerInvalidPlacement() {
        // Create a tower at position (5, 1000)
        CellPosition position2 = new CellPosition(5, 1000);
        Tower tower2 = new Tower(position2.row(), position2.col(), 1, 1, 1, 800, "", null);
        towerDefenseModel.addTower(tower2);

        // Tower should not be placed
        assertTrue(towerDefenseModel.getTowers().isEmpty());

        // Position should not be valid
        assertFalse(towerDefenseModel.isTowerPlacementValid(position2));

        // Tower should not remove gold from player
        // As the tower costs 800 and the player starts with 800 gold,
        // the player should still have 800 gold after trying to place the tower
        assertEquals(800, towerDefenseModel.getGold());
    }

    @Test
    void testProjectileCollision() {
        // Define waypoints as default waypoints
        List<ScreenPosition> waypoints = towerDefenseModel.getWaypoints();

        // Spawn enemy at first waypoint
        Enemy enemy = new EnemyRed(waypoints.get(0).x(), waypoints.get(0).y(), waypoints, 0);
        towerDefenseModel.spawnEnemy(enemy);

        // Spawn tower at first waypoint and fire at nearest enemy (the one we just spawned)
        int towerX = towerDefenseModel.getConverter().getCellPositionFromScreenPosition(waypoints.get(0)).col()-1;
        int towerY = towerDefenseModel.getConverter().getCellPositionFromScreenPosition(waypoints.get(0)).row();
        Tower tower = new TowerTree(towerX, towerY, towerDefenseModel.getConverter());
        towerDefenseModel.addTower(tower);
        tower.setTarget(towerDefenseModel.getEnemies());
        tower.fire();

        // Run the game for 1000 ticks
        for (int i = 0; i < 1000; i++) {
            towerDefenseModel.clockTick();
        }

        // Enemy should have been shot and removed from the game
        assertTrue(towerDefenseModel.getEnemies().isEmpty());
    }

}
