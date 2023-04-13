package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControllableTowerDefenseModel;
import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.enemy.Wave;
import no.uib.inf101.sem2.entity.enemy.Waypoints;
import no.uib.inf101.sem2.entity.projectile.Projectile;
import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.screen.ScreenPosition;
import no.uib.inf101.sem2.view.ViewableTowerDefenseModel;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TowerDefenseModel class handles the game's state and logic.
 */
public class TowerDefenseModel implements ViewableTowerDefenseModel, ControllableTowerDefenseModel {

    // Window dimensions for scaling
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 480;

    // Fields
    private final TowerDefenseField field;
    private final List<ScreenPosition> waypoints;
    private final Wave waveManager;
    private GameState gameState;
    private int wave;
    private int lives;
    private int gold;
    private List<Enemy> enemies;
    private List<Tower> towers;
    private ArrayList<Projectile> projectiles;
    private final int interval;

    // IllegalPositionManager
    private final IllegalPositionManager illegalPositionManager;

    /**
     * Constructor for TowerDefenseModel.
     *
     * @param field the game field
     */
    public TowerDefenseModel(TowerDefenseField field) {
        this.field = field;
        this.gameState = GameState.MAIN_MENU;
        this.wave = 1;
        this.lives = 100;
        this.gold = 800;
        this.enemies = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.interval = 15;
        this.waypoints = new Waypoints(new Rectangle2D.Double(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT), field, 0).getWaypoints();
        this.waveManager = new Wave(this);

        // Instantiate the IllegalPositionManager in your model
        illegalPositionManager = new IllegalPositionManager();
        initializeIllegalPositions();
    }


    private void initializeIllegalPositions() {
        List<CellPosition> illegalPositions = List.of(
                new CellPosition(0, 0),
                new CellPosition(0, 1),
                new CellPosition(0, 2),
                new CellPosition(0, 3),
                new CellPosition(0, 4),
                new CellPosition(0, 5),
                new CellPosition(0, 6),
                new CellPosition(0, 7),
                new CellPosition(0, 8),
                new CellPosition(0, 9),
                new CellPosition(0, 10),
                new CellPosition(1, 0),
                new CellPosition(1, 1),
                new CellPosition(1, 2),
                new CellPosition(1, 3),
                new CellPosition(1, 4),
                new CellPosition(1, 5),
                new CellPosition(1, 6),
                new CellPosition(1, 7),
                new CellPosition(1, 8),
                new CellPosition(1, 9),
                new CellPosition(2, 0),
                new CellPosition(2, 1),
                new CellPosition(2, 2),
                new CellPosition(2, 3),
                new CellPosition(2, 4),
                new CellPosition(2, 5),
                new CellPosition(2, 6),
                new CellPosition(2, 7),
                new CellPosition(2, 8),
                new CellPosition(2, 9),
                new CellPosition(3, 0),
                new CellPosition(3, 1),
                new CellPosition(3, 2),
                new CellPosition(3, 3),
                new CellPosition(3, 4),
                new CellPosition(3, 5),
                new CellPosition(3, 6),
                new CellPosition(3, 7),
                new CellPosition(3, 8),
                new CellPosition(3, 9),
                new CellPosition(4, 0),
                new CellPosition(4, 1),
                new CellPosition(4, 2),
                new CellPosition(5, 0),
                new CellPosition(5, 1),
                new CellPosition(5, 2),
                new CellPosition(6, 0),
                new CellPosition(7, 2),
                new CellPosition(7, 3),
                new CellPosition(7, 9),
                new CellPosition(8, 8),
                new CellPosition(8, 9),
                new CellPosition(8, 10),
                new CellPosition(9, 7),
                new CellPosition(9, 8),
                new CellPosition(9, 9),
                new CellPosition(9, 10),
                new CellPosition(10, 7),
                new CellPosition(10, 8),
                new CellPosition(10, 9),
                new CellPosition(10, 10),
                new CellPosition(11, 7),
                new CellPosition(11, 8),
                new CellPosition(11, 9),
                new CellPosition(11, 10),
                new CellPosition(2, 13),
                new CellPosition(3, 13),
                new CellPosition(3, 12),
                new CellPosition(8, 15),
                new CellPosition(7, 15),
                new CellPosition(6, 15),
                new CellPosition(7, 14),
                new CellPosition(8, 14),
                new CellPosition(10, 0),
                new CellPosition(10, 1),
                new CellPosition(10, 2),
                new CellPosition(9, 2),
                new CellPosition(8, 2),
                new CellPosition(8, 3),
                new CellPosition(8, 4),
                new CellPosition(8, 5),
                new CellPosition(8, 6),
                new CellPosition(7, 6),
                new CellPosition(6, 6),
                new CellPosition(6, 5),
                new CellPosition(6, 4),
                new CellPosition(6, 3),
                new CellPosition(5, 3),
                new CellPosition(4, 3),
                new CellPosition(4, 4),
                new CellPosition(4, 5),
                new CellPosition(4, 6),
                new CellPosition(4, 7),
                new CellPosition(4, 8),
                new CellPosition(4, 9),
                new CellPosition(5, 9),
                new CellPosition(5, 10),
                new CellPosition(5, 11),
                new CellPosition(6, 11),
                new CellPosition(6, 12),
                new CellPosition(6, 13),
                new CellPosition(6, 14),
                new CellPosition(5, 14),
                new CellPosition(4, 14),
                new CellPosition(3, 14),
                new CellPosition(2, 14),
                new CellPosition(1, 14),
                new CellPosition(1, 13),
                new CellPosition(1, 12),
                new CellPosition(1, 11),
                new CellPosition(0, 11)
        );
        for (CellPosition position : illegalPositions) {
            illegalPositionManager.addIllegalPosition(position);
        }
    }

    // Implemented methods from interfaces

    @Override
    public GridDimension getGridDimension() {
        return field;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return field;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void setGameState(GameState state) {
        this.gameState = state;
    }

    @Override
    public int getTimerInterval() {
        return interval;
    }

    @Override
    public int getGold() {
        return gold;
    }

    @Override
    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public List<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public int getWave() {
        return wave;
    }

    @Override
    public void setWave(int wave) {
        this.wave = wave;
    }

    @Override
    public void clockTick() {
        updateGame();
    }

    // Game state control methods

    @Override
    public void startGame() {
        setGameState(GameState.IN_GAME);
        wave = 1;
    }

    @Override
    public void restartGame() {
        setGameState(GameState.IN_GAME);
        resetGame();
        wave = 1;
    }

    @Override
    public void pauseGame() {
        setGameState(GameState.PAUSE_SCREEN);
    }

    @Override
    public void resumeGame() {
        setGameState(GameState.IN_GAME);
    }

    @Override
    public void mainMenu() {
        setGameState(GameState.MAIN_MENU);
        resetGame();
    }

    @Override
    public void exitGame() {
        System.exit(0);
    }

    // Private helper methods

    /**
     * Resets the game state to the initial conditions.
     */
    private void resetGame() {
        setGold(800);
        setLives(100);
        setWave(1);
        updateEnemies(new ArrayList<>());
        updateTowers(new ArrayList<>());
        updateProjectiles(new ArrayList<>());
        waveManager.reset();
    }

    /**
     * Updates the game state by moving enemies, updating towers and projectiles, and checking for collisions and game over conditions.
     */
    private void updateGame() {
        moveEnemies();
        updateTowers();
        updateProjectiles();
        checkCollisions();
        checkGameOver();
    }

    /**
     * Moves all enemies in the game.
     * Uses an iterator to remove enemies that have reached the end of the path.
     * This iterator is needed because we cannot remove elements from a list while iterating over it.
     */
    private void moveEnemies() {
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.move();
            if (enemy.hasReachedEnd()) { // Make sure the hasReachedEnd method is public in the Enemy class
                enemyReachedEnd(enemy);
                iterator.remove();
            }
        }
    }

    /**
     * Depletes lives by the damage of an enemy that reached the end of the path.
     *
     * @param enemy The enemy that reached the end of the path.
     */
    private void enemyReachedEnd(Enemy enemy) {
        lives -= enemy.getDamage();
    }

    /**
     * Updates all towers in the game, for example, by finding targets and firing projectiles.
     */
    private void updateTowers() {
        // Update towers, e.g., find targets and fire projectiles
    }

    /**
     * Updates all projectiles in the game by moving them.
     */
    private void updateProjectiles() {
        for (Projectile projectile : projectiles) {
            projectile.move();
        }
    }

    /**
     * Checks for collisions between projectiles and enemies, and checks if enemies have reached the end of the path.
     */
    private void checkCollisions() {
        // Check for collisions between projectiles and enemies
        // Check if enemies have reached the end of the path
    }

    /**
     * Checks if the game is over (i.e., if the player has no lives left) and updates the game state accordingly.
     */
    private void checkGameOver() {
        if (lives <= 0) {
            setGameState(GameState.GAME_OVER_SCREEN);
        }
    }

    /**
     * Updates the list of enemies in the game.
     *
     * @param enemies The new list of enemies.
     */
    public void updateEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    /**
     * Updates the list of projectiles in the game.
     *
     * @param projectiles The new list of projectiles.
     */
    public void updateProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    /**
     * Updates the list of towers in the game.
     *
     * @param towers The new list of towers.
     */
    public void updateTowers(List<Tower> towers) {
        this.towers = towers;
    }

    // Game logic methods (examples, you can add more based on your requirements)

    /**
     * Spawns a new enemy in the game.
     *
     * @param enemy The enemy to be spawned.
     */
    public void spawnEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * Adds a tower to the game.
     *
     * @param tower The tower to be added.
     */
    public void addTower(Tower tower) {
        if (tower.getCost() > getGold()) {
            return;
        }
        towers.add(tower);
        illegalPositionManager.addIllegalPosition(tower.getPosition());
        setGold(getGold() - tower.getCost());
        System.out.println("Tower added at " + tower.getPosition());
    }

    /**
     * Checks if a tower can be placed at a given position.
     *
     * @param position The position to be checked.
     * @return True if the tower can be placed at the given position, false otherwise.
     */
    public boolean isTowerPlacementValid(CellPosition position) {
        return illegalPositionManager.isPositionLegal(position);
    }

    /**
     * Gets the list of towers in the game.
     *
     * @return The list of towers.
     */
    public List<Tower> getTowers() {
        return towers;
    }

    /**
     * Gets the list of projectiles in the game.
     *
     * @return The list of projectiles.
     */
    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    /**
     * Adds a projectile to the game.
     *
     * @param projectile The projectile to be added.
     */
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    /**
     * Gets the list of waypoints for the enemy path.
     *
     * @return The list of waypoints.
     */
    public List<ScreenPosition> getWaypoints() {
        return waypoints;
    }

    @Override
    public Wave getWaveManager() {
        return waveManager;
    }

}



