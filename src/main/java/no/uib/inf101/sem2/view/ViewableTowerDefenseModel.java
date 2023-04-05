package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.GameState;

import java.util.List;

public interface ViewableTowerDefenseModel {

    GridDimension getGridDimension();

    Iterable<GridCell<Character>> getTilesOnBoard();

    GameState getGameState();

    void setTimerInterval(int interval);

    int getGold();

    int getLives();

    List<Enemy> getEnemies();

    int getWave();

    int getInterval();

    void setGameState(GameState state);

    void setGold(int gold);

    void setLives(int lives);

    void setWave(int level);

    void exitGame();

    void updateEnemies(List<Enemy> enemies);

    void startGame();

    void restartGame();

    void pauseGame();

    void resumeGame();

    void mainMenu();

    List<Tower> getTowers();

}
