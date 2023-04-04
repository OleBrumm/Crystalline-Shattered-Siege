package no.uib.inf101.sem2.controller;

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
     * Exit the game.
     */
    void exitGame();
}
