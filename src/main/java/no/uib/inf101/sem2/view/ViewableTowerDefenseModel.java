package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.projectile.Projectile;
import no.uib.inf101.sem2.entity.projectile.projectiletypes.Explosion;
import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.List;

public interface ViewableTowerDefenseModel {

    /**
     * Get the grid dimension.
     *
     * @return the grid dimension
     */
    GridDimension getGridDimension();

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
     * Update the enemies
     *
     * @param enemies the new enemies
     */
    void setEnemies(List<Enemy> enemies);

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
     * Get the towers
     *
     * @return the towers
     */
    List<Tower> getTowers();

    /**
     * Get the waypoints
     *
     * @return the waypoints
     */
    List<ScreenPosition> getWaypoints();

    /**
     * Set the towers
     *
     * @param towers the new towers
     */
    void setTowers(List<Tower> towers);

    /**
     * Get the projectiles
     *
     * @return the projectiles
     */
    List<Projectile> getProjectiles();

    /**
     * Set the projectiles
     * @param projectiles the new projectiles
     */
    void setProjectiles(List<Projectile> projectiles);

    /**
     * Get the explosions
     *
     * @return the explosions
     */
    List<Explosion> getExplosions();
}
