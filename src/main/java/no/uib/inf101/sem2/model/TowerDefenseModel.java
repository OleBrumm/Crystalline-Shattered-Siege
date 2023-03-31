package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControllableTowerDefenseModel;
import no.uib.inf101.sem2.entity.enemy.Enemy1;
import no.uib.inf101.sem2.entity.Projectile;
import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.view.ViewableTowerDefenseModel;

import java.util.ArrayList;


public class TowerDefenseModel implements ViewableTowerDefenseModel, ControllableTowerDefenseModel {
    // Fields
    private final TowerDefenseField field;
    private TowerDefenseField shopField;
    private int enemiesKilled;
    private GameState gameState;
    private int level;
    private int lives;
    private int gold;
    private ArrayList<Enemy1> enemies;
    private ArrayList<Object> towers;
    private ArrayList<Projectile> projectiles;
    private int interval;

    // Constructor
    public TowerDefenseModel(TowerDefenseField field) {
        this.field = field;
        this.gameState = GameState.MAIN_MENU;
        this.level = 1;
        this.lives = 10;
        this.gold = 100;
        this.enemies = new ArrayList<>();
        this.enemiesKilled = 0;
        this.towers = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.interval = 1000;
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
    public ArrayList<Enemy1> getEnemies() {
        return enemies;
    }

    @Override
    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    @Override
    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
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
        spawnEnemiesForLevel(level);
        startGameLoop();
    }

    @Override
    public void restartGame() {
        setGameState(GameState.IN_GAME);
        resetGame();
        spawnEnemiesForLevel(level);
        startGameLoop();
    }

    @Override
    public void pauseGame() {
        setGameState(GameState.PAUSE_SCREEN);
        stopGameLoop();
    }

    @Override
    public void resumeGame() {
        setGameState(GameState.IN_GAME);
        startGameLoop();
    }

    @Override
    public void mainMenu() {
        setGameState(GameState.MAIN_MENU);
        stopGameLoop();
        resetGame();
    }

    @Override
    public void exitGame() {
        System.exit(0);
    }

    // Private helper methods
    private void resetGame() {
        setGold(800);
        setLives(100);
        setEnemiesKilled(0);
        setLevel(1);
        updateEnemies(new ArrayList<>());
        updateTowers(new ArrayList<>());
        updateProjectiles(new ArrayList<>());
    }

    private void startGameLoop() {
        // Start a timer or game loop to update game state regularly
    }

    private void stopGameLoop() {
        // Stop the timer or game loop
    }

    private void spawnEnemiesForLevel(int level) {
        // Spawn enemies based on the current level
    }

    private void updateGame() {
        // Update the game state on each game loop iteration
        moveEnemies();
        updateTowers();
        updateProjectiles();
        checkCollisions();
        checkGameOver();
    }

    private void moveEnemies() {
        // Move enemies along the path
    }

    private void updateTowers() {
        // Update towers, e.g., find targets and fire projectiles
    }

    private void updateProjectiles() {
        // Move projectiles and check for collisions with enemies
    }

    private void checkCollisions() {
        // Check for collisions between projectiles and enemies
        // Check if enemies have reached the end of the path
    }

    private void checkGameOver() {
        // Check for game over conditions, e.g., no more lives or all enemies defeated
    }

    public void updateEnemies(ArrayList<Enemy1> enemies) {
        this.enemies = enemies;
    }

    public void updateProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public void updateTowers(ArrayList<Object > towers) {
        this.towers = towers;
    }

    // Game logic methods (examples, you can add more based on your requirements)
    public void spawnEnemy(Enemy1 enemy) {
        enemies.add(enemy);
    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public void fireProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }
}
