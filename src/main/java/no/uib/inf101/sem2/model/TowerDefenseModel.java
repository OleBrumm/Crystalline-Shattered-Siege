package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControllableTowerDefenseModel;
import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.enemy.Wave;
import no.uib.inf101.sem2.entity.enemy.Waypoints;
import no.uib.inf101.sem2.entity.enemy.enemytypes.*;
import no.uib.inf101.sem2.entity.projectile.Projectile;
import no.uib.inf101.sem2.entity.projectile.projectiletypes.Explosion;
import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.grid.converter.CellPositionToPixelConverter;
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

    private Rectangle2D gameRectangle = new Rectangle2D.Double(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Fields
    private final TowerDefenseField field;
    private final List<ScreenPosition> waypoints;
    private final Wave waveManager;
    private final int interval;
    // IllegalPositionManager
    private final IllegalPositionManager illegalPositionManager;
    private final List<Explosion> explosions;
    private GameState gameState;
    private int wave;
    private int lives;
    private int gold;
    private List<Enemy> enemies;
    private List<Tower> towers;
    private List<Projectile> projectiles;

    private CellPositionToPixelConverter converter;

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
        this.explosions = new ArrayList<>();
        this.interval = 15;
        this.waypoints = new Waypoints(gameRectangle, field, 0).getWaypoints();
        this.waveManager = new Wave(this);

        this.converter = new CellPositionToPixelConverter(gameRectangle, field, 0);

        // Instantiate the IllegalPositionManager and initialize the illegal positions
        illegalPositionManager = new IllegalPositionManager();
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
    }

    @Override
    public void restartGame() {
        System.out.println("Restarting game from model");
        setGameState(GameState.IN_GAME);
        resetGame();
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
        System.out.println("Game reset");
        illegalPositionManager.reset();
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
        checkGameFinish();
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
            if (enemy.hasReachedEnd()) {
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

    /**
     * Checks for collisions between projectiles and enemies.
     * If a collision is detected, the enemy is damaged and the projectile is removed.
     * If the enemy is dead, it is removed from the game.
     */
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

    /**
     * Checks if a projectile collides with an enemy.
     * If a collision is detected, the enemy is damaged and the projectile is removed.
     *
     * @param projectile         The projectile that is checked for collisions.
     * @param projectileIterator An iterator over the projectiles in the game.
     */
    private void checkProjectileCollisions(Projectile projectile, Iterator<Projectile> projectileIterator) {
        for (Enemy enemy : enemies) {
            if (projectile.collidesWith(enemy)) {
                handleCollision(projectile, enemy);
                if (projectile.isDead()) {
                    projectileIterator.remove();
                    System.out.println("Projectile removed");
                    break;
                }
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
        projectile.setHealth(projectile.getHealth() - enemy.getHealth());
        enemy.setHealth(enemy.getHealth() - projectile.getDamage());
        System.out.println("Enemy health: " + enemy.getHealth());
        System.out.println("Projectile health: " + projectile.getHealth());

        switch (projectile.getEffect()) {
            case "SLOW", "FREEZE", "POISON" -> enemy.setEffect(projectile.getEffect());
            case "EXPLOSION" ->
                    explodePoint(projectile.getX(), projectile.getY(), projectile.getEffectRadius(), projectile.getEffectDamage());
        }
    }

    /**
     * Explodes a point, damaging all enemies within a certain radius.
     * The explosion is added to the list of explosions.
     * The damage is dealt to all enemies within the radius.
     *
     * @param x            the x coordinate of the explosion
     * @param y            the y coordinate of the explosion
     * @param effectRadius the radius of the explosion
     * @param effectDamage the damage of the explosion
     */
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
                    newEnemies.add(new EnemyRed(enemy.getX(), enemy.getY(), waypoints, enemy.getCurrentWaypointIndex()));
            case "YELLOW" ->
                    newEnemies.add(new EnemyBlue(enemy.getX(), enemy.getY(), waypoints, enemy.getCurrentWaypointIndex()));
            case "GREEN" ->
                    newEnemies.add(new EnemyYellow(enemy.getX(), enemy.getY(), waypoints, enemy.getCurrentWaypointIndex()));
            case "PURPLE" ->
                    newEnemies.add(new EnemyGreen(enemy.getX(), enemy.getY(), waypoints, enemy.getCurrentWaypointIndex()));
            case "BOSS" ->
                    newEnemies.add(new EnemyPurple(enemy.getX(), enemy.getY(), waypoints, enemy.getCurrentWaypointIndex()));
        }

        return newEnemies;
    }


    /**
     * Checks if the game is over.
     * Either if the player has lost all lives or if the last wave has been completed.
     */
    private void checkGameFinish() {
        if (lives <= 0) {
            setGameState(GameState.GAME_OVER_SCREEN);
        }
        if (waveManager.getWaveNumber() > waveManager.getNumberOfWaves()){
            setGameState(GameState.VICTORY_SCREEN);
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
            System.out.println("Not enough gold to buy tower");
            return;
        }
        if (!isTowerPlacementValid(tower.getCellPosition())) {
            System.out.println("Tower cannot be placed at " + tower.getCellPosition());
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
    private void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }


    public List<ScreenPosition> getWaypoints() {
        return waypoints;
    }

    @Override
    public Wave getWaveManager() {
        return waveManager;
    }

    public CellPositionToPixelConverter getConverter() {
        return converter;
    }
}



