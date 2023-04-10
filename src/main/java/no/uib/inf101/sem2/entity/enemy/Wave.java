package no.uib.inf101.sem2.entity.enemy;

import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.ArrayList;
import java.util.List;

public class Wave {
    private final TowerDefenseModel model;
    private final List<ScreenPosition> waypoints;
    private int waveNumber;
    private int enemiesSpawned;
    private List<Enemy> waveEnemies;

    public Wave(TowerDefenseModel model) {
        this.model = model;
        this.waypoints = model.getWaypoints();
        this.waveNumber = 1;
        this.enemiesSpawned = 0;
        createWaveEnemies();
    }

    private void createWaveEnemies() {
        waveEnemies = new ArrayList<>();
        for (int i = 0; i < waveNumber * 2; i++) {
            waveEnemies.add(new EnemyRed(waypoints.get(0).x(), waypoints.get(0).y(), waypoints));
        }
        for (int i = 0; i < waveNumber; i++) {
            waveEnemies.add(new EnemyBlue(waypoints.get(0).x(), waypoints.get(0).y(), waypoints));
        }
        for (int i = 0; i < waveNumber / 2; i++) {
            waveEnemies.add(new EnemyYellow(waypoints.get(0).x(), waypoints.get(0).y(), waypoints));
        }
    }

    public void spawnEnemiesForWave() {
        if (isWaveComplete()) {
            return;
        }
        if (enemiesSpawned < waveEnemies.size()) {
            Enemy enemy = waveEnemies.get(enemiesSpawned);
            model.spawnEnemy(enemy);
            enemiesSpawned++;
            System.out.println("Spawned enemy " + enemiesSpawned + " of " + waveEnemies.size());
            System.out.println("This enemy was a " + enemy.getClass().getSimpleName());
            System.out.println("\n");
        }
    }

    public void nextWave() {
        waveNumber++;
        enemiesSpawned = 0;
        createWaveEnemies();
    }

    public boolean isWaveComplete() {
        return model.getEnemies().isEmpty() && enemiesSpawned == waveEnemies.size();
    }

    public void reset() {
        waveNumber = 1;
        enemiesSpawned = 0;
        createWaveEnemies();
    }
}
