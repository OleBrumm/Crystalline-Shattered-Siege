package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.enemy.Wave;
import no.uib.inf101.sem2.model.GameState;

public interface ControllableTowerDefenseModel {


    /**
     * Get the current game state.
     *
     * @return The current game state.
     */
    GameState getGameState();

    /**
     * Set the current game state.
     *
     * @param state The new game state.
     */
    void setGameState(GameState state);

    /**
     * Get the time between each tick.
     *
     * @return The current tetromino.
     */
    int getTimerInterval();

    /**
     * Set the time between each tick.
     */
    void clockTick();

    /**
     * Start a new game.
     */
    void startGame();

    /**
     * Pause the game.
     */
    void pauseGame();

    /**
     * Resume the game.
     */
    void resumeGame();

    /**
     * Restart the game.
     */
    void restartGame();

    /**
     * Exits to the main menu.
     */
    void mainMenu();

    /**
     * Exit the game.
     */
    void exitGame();

    /**
     * Spawns an enemy.
     */
    void spawnEnemy(Enemy enemy);

    /**
     * Get the wave manager.
     *
     * @return The wave manager.
     */
    Wave getWaveManager();
}
