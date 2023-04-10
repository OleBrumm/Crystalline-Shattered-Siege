package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControllableTowerDefenseModel;
import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.enemy.Waypoints;
import no.uib.inf101.sem2.entity.projectile.Projectile;
import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.screen.ScreenPosition;
import no.uib.inf101.sem2.view.ViewableTowerDefenseModel;
import no.uib.inf101.sem2.entity.enemy.Wave;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
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
    private GameState gameState;
    private int wave;
    private int lives;
    private int gold;
    private List<Enemy> enemies;
    private List<Tower> towers;
    private ArrayList<Projectile> projectiles;
    private int interval;
    private final List<ScreenPosition> waypoints;
    private final Wave waveManager;

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
    public void setTimerInterval(int interval) {
        this.interval = interval;
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
    public int getInterval() {
        return interval;
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
     */
    private void moveEnemies() {
        for (Enemy enemy : enemies) {
            enemy.move();
        }
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
        towers.add(tower);
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



