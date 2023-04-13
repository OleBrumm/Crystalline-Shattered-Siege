package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.GameState;

import java.util.List;

public interface ViewableTowerDefenseModel {

    /**
     * Get the grid dimension.
     *
     * @return the grid dimension
     */
    GridDimension getGridDimension();

    /**
     * Get the tiles on the board.
     *
     * @return the tiles on the board
     */
    Iterable<GridCell<Character>> getTilesOnBoard();

    /**
     * Get the game state.
     *
     * @return the game state
     */
    GameState getGameState();

    /**
     * Set the game state
     *
     * @param state the new game state
     */
    void setGameState(GameState state);

    /**
     * Get the current gold.
     *
     * @return the current gold
     */
    int getGold();

    /**
     * Set the gold
     *
     * @param gold the new gold
     */
    void setGold(int gold);

    /**
     * Get the current lives.
     *
     * @return the current lives
     */
    int getLives();

    /**
     * Set the lives
     *
     * @param lives the new lives
     */
    void setLives(int lives);

    /**
     * Get the enemies.
     *
     * @return the enemies
     */
    List<Enemy> getEnemies();

    /**
     * Get the current wave.
     *
     * @return the current wave
     */
    int getWave();

    /**
     * Set the wave
     *
     * @param level the new wave
     */
    void setWave(int level);

    /**
     * Update the enemies
     *
     * @param enemies the new enemies
     */
    void updateEnemies(List<Enemy> enemies);

    /**
     * Get the towers
     *
     * @return the towers
     */
    List<Tower> getTowers();

}
