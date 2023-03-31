package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.entity.enemy.Enemy1;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.GameState;

import java.util.ArrayList;

public interface ViewableTowerDefenseModel {

    GridDimension getGridDimension();

    Iterable<GridCell<Character>> getTilesOnBoard();

    GameState getGameState();

    void setTimerInterval(int interval);

    int getGold();

    int getLives();

    ArrayList<Enemy1> getEnemies();

    int getEnemiesKilled();

    int getLevel();

    int getInterval();

    void setGameState(GameState state);

    void setGold(int gold);

    void setLives(int lives);

    void setEnemiesKilled(int enemiesKilled);

    void setLevel(int level);

    void exitGame();

    void updateEnemies(ArrayList<Enemy1> enemies);

    void startGame();

    void restartGame();

    void pauseGame();

    void resumeGame();

    void mainMenu();

}