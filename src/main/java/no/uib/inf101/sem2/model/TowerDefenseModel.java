package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControllableTowerDefenseModel;
import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.enemy.Wave;
import no.uib.inf101.sem2.entity.enemy.Waypoints;
import no.uib.inf101.sem2.entity.enemy.enemyTypes.EnemyBlue;
import no.uib.inf101.sem2.entity.enemy.enemyTypes.EnemyRed;
import no.uib.inf101.sem2.entity.projectile.Projectile;
import no.uib.inf101.sem2.entity.projectile.projectileTypes.Explosion;
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
    private final int interval;
    // IllegalPositionManager
    private final IllegalPositionManager illegalPositionManager;
    private GameState gameState;
    private int wave;
    private int lives;
    private int gold;
    private List<Enemy> enemies;
    private List<Tower> towers;
    private List<Projectile> projectiles;
    private List<Explosion> explosions;

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
        this.gold = 10000;
        this.enemies = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.explosions = new ArrayList<>();
        this.interval = 15;
        this.waypoints = new Waypoints(new Rectangle2D.Double(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT), field, 0).getWaypoints();
        this.waveManager = new Wave(this);

        // Instantiate the IllegalPositionManager in your model
        illegalPositionManager = new IllegalPositionManager();
        initializeIllegalPositions();
    }


    private void initializeIllegalPositions() {
        int[][] illegalGrid = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0},
                {1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},

        };

        for (int i = 0; i < illegalGrid.length; i++) {
            for (int j = 0; j < illegalGrid[i].length; j++) {
                if (illegalGrid[i][j] == 1) {
                    illegalPositionManager.addIllegalPosition(new CellPosition(i, j));
                }
            }
        }
    }

    // Implemented methods from interfaces

    @Override
    public GridDimension getGridDimension() {
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
    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
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
    public List<Explosion> getExplosions() {
        return explosions;
    }

    // Game state control methods

    @Override
    public void clockTick() {
        updateGame();
    }

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

    // Private helper methods

    @Override
    public void exitGame() {
        System.exit(0);
    }

    /**
     * Resets the game state to the initial conditions.
     */
    private void resetGame() {
        setGold(800);
        setLives(100);
        setWave(1);
        setEnemies(new ArrayList<>());
        setTowers(new ArrayList<>());
        setProjectiles(new ArrayList<>());
        illegalPositionManager.reset();
        initializeIllegalPositions();
        waveManager.reset();
    }

    /**
     * Updates the game state by moving enemies, updating towers and projectiles, and checking for collisions and game over conditions.
     */
    private void updateGame() {
        moveEnemies();
        updateTowers();
        setProjectiles();
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

    private void updateTowers() {
        shootClosestEnemy();
    }

    /**
     * Updates all towers in the game, for example, by finding targets and firing projectiles.
     */
    private void shootClosestEnemy() {
        for (Tower tower : towers) {
            tower.incrementTimeSinceLastShot(10);
            tower.setTarget(enemies);
            Enemy closestEnemy = tower.getTarget();
            if (closestEnemy != null && tower.canFire()) {
                Projectile projectile = tower.fire();
                if (projectile != null) {
                    addProjectile(projectile);
                }
            }
        }
    }

    /**
     * Updates all projectiles in the game by moving them.
     */
    private void setProjectiles() {
        for (Projectile projectile : projectiles) {
            projectile.move();
        }
    }

    private void checkCollisions() {
        List<Enemy> newEnemies = new ArrayList<>();
        Iterator<Projectile> projectileIterator = projectiles.iterator();

        while (projectileIterator.hasNext()) {
            Projectile projectile = projectileIterator.next();
            checkProjectileCollisions(projectile, projectileIterator);
        }

        // Iterate through enemies and remove dead ones
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            if (enemy.isDead()) {
                gold += enemy.getReward();
                newEnemies.addAll(transformEnemy(enemy));
                enemyIterator.remove();
            }
        }

        enemies.addAll(newEnemies);
    }

    private void checkProjectileCollisions(Projectile projectile, Iterator<Projectile> projectileIterator) {
        Iterator<Enemy> enemyIterator = enemies.iterator();
        boolean projectileHit = false;

        while (enemyIterator.hasNext() && !projectileHit) {
            Enemy enemy = enemyIterator.next();
            if (projectile.collidesWith(enemy)) {
                handleCollision(projectile, enemy);
                projectileHit = true;
                projectileIterator.remove();
            }
        }
    }

    /**
     * Decreases the health of an enemy by the damage of a projectile.
     *
     * @param projectile The projectile that hit the enemy.
     * @param enemy      The enemy that was hit.
     */
    private void handleCollision(Projectile projectile, Enemy enemy) {
        enemy.setHealth(enemy.getHealth() - projectile.getDamage());
        switch (projectile.getEffect()) {
            case "SLOW", "FREEZE", "POISON" -> enemy.setEffect(projectile.getEffect());
            case "EXPLOSION" ->
                    explodePoint(projectile.getX(), projectile.getY(), projectile.getEffectRadius(), projectile.getEffectDamage());
        }
    }

    private void explodePoint(double x, double y, int effectRadius, int effectDamage) {
        explosions.add(new Explosion(x, y, effectRadius, 15));
        for (Enemy enemy : enemies) {
            if (enemy.getDistance(x, y) <= effectRadius) {
                enemy.setHealth(enemy.getHealth() - effectDamage);
            }
        }
    }

    /**
     * Transforms an enemy into a new lesser enemy when it is killed.
     */
    private List<Enemy> transformEnemy(Enemy enemy) {
        List<Enemy> newEnemies = new ArrayList<>();

        switch (enemy.getType()) {
            case "BLUE" ->
                    newEnemies.add(new EnemyRed(enemy.getX(), enemy.getY(), waypoints, enemy.getCurrentWaypointIndex(), enemy.getSpeed()));
            case "YELLOW" ->
                    newEnemies.add(new EnemyBlue(enemy.getX(), enemy.getY(), waypoints, enemy.getCurrentWaypointIndex(), enemy.getSpeed()));
        }

        return newEnemies;
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
        illegalPositionManager.addIllegalPosition(tower.getCellPosition());
        setGold(getGold() - tower.getCost());
        System.out.println("Tower added at " + tower.getCellPosition());
    }

    // Game logic methods (examples, you can add more based on your requirements)

    /**
     * Checks if a tower can be placed at a given position.
     *
     * @param position The position to be checked.
     * @return True if the tower can be placed at the given position, false otherwise.
     */
    public boolean isTowerPlacementValid(CellPosition position) {
        return illegalPositionManager.isPositionLegal(position);
    }

    @Override
    public List<Tower> getTowers() {
        return towers;
    }

    @Override
    public void setTowers(List<Tower> towers) {
        this.towers = towers;
    }

    @Override
    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    @Override
    public void setProjectiles(List<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    /**
     * Adds a projectile to the game.
     *
     * @param projectile The projectile to be added.
     */
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }


    public List<ScreenPosition> getWaypoints() {
        return waypoints;
    }

    @Override
    public Wave getWaveManager() {
        return waveManager;
    }

}



