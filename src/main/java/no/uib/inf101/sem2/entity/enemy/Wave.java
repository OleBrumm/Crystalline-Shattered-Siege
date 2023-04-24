package no.uib.inf101.sem2.entity.enemy;

import no.uib.inf101.sem2.entity.enemy.enemytypes.*;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.screen.ScreenPosition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wave {
    private final TowerDefenseModel model;
    private final List<ScreenPosition> waypoints;
    private int waveNumber;
    private final List<List<Integer>> waves = new ArrayList<>();
    private int enemiesSpawned;
    private List<Enemy> waveEnemies;

    public Wave(TowerDefenseModel model) {
        this.model = model;
        this.waypoints = model.getWaypoints();
        this.waveNumber = 1;
        this.enemiesSpawned = 0;
        initializeWaves();
        createWaveEnemies(waves.get(waveNumber - 1));
    }

    public void initializeWaves() {
        waves.add(List.of(20, 0, 0, 0, 0, 0));
        waves.add(List.of(35, 0, 0, 0, 0, 0));
        waves.add(List.of(25, 5, 0, 0, 0, 0));
        waves.add(List.of(35, 18, 0, 0, 0, 0));
        waves.add(List.of(5, 27, 0, 0, 0, 0));
        waves.add(List.of(15, 15, 4, 0, 0, 0));
        waves.add(List.of(20, 20, 5, 0, 0, 0));
        waves.add(List.of(10, 20, 14, 0, 0, 0));
        waves.add(List.of(0, 0, 0, 30, 0, 0));
        waves.add(List.of(0, 102, 0, 0, 0, 0));
        waves.add(List.of(6, 12, 12, 3, 0, 0));
        waves.add(List.of(0, 15, 10, 5, 0, 0));
        waves.add(List.of(0, 50, 23, 0, 0, 0));
        waves.add(List.of(49, 15, 10, 9, 0, 0));
        waves.add(List.of(20, 15, 12, 10, 5, 0));
        waves.add(List.of(0, 0, 40, 8, 0, 0));
        waves.add(List.of(0, 0, 80, 0, 0, 0));
        waves.add(List.of(0, 0, 0, 40, 14, 0));
        waves.add(List.of(0, 0, 0, 0, 0, 1));
    }

    private void createWaveEnemies(List<Integer> wave) {
        waveEnemies = new ArrayList<>();

        for (int i = 0; i < wave.get(0); i++){
            waveEnemies.add(new EnemyRed(waypoints.get(0).x(), waypoints.get(0).y(), waypoints, 0));
        }
        for (int i = 0; i < wave.get(1); i++) {
            waveEnemies.add(new EnemyBlue(waypoints.get(0).x(), waypoints.get(0).y(), waypoints, 0));
        }
        for (int i = 0; i < wave.get(2); i++) {
            waveEnemies.add(new EnemyYellow(waypoints.get(0).x(), waypoints.get(0).y(), waypoints, 0));
        }
        for (int i = 0; i < wave.get(3); i++) {
            waveEnemies.add(new EnemyGreen(waypoints.get(0).x(), waypoints.get(0).y(), waypoints, 0));
        }
        for (int i = 0; i < wave.get(4); i++) {
            waveEnemies.add(new EnemyPurple(waypoints.get(0).x(), waypoints.get(0).y(), waypoints, 0));
        }
        for (int i = 0; i < wave.get(5); i++) {
            waveEnemies.add(new EnemyBoss(waypoints.get(0).x(), waypoints.get(0).y(), waypoints, 0));
        }

        Collections.shuffle(waveEnemies);
    }

    public void spawnEnemiesForWave() {
        if (isWaveComplete()) {
            return;
        }
        if (waveNumber > waves.size()) {
            return;
        }
        createWaveEnemies(waves.get(waveNumber - 1));
        if (enemiesSpawned < waveEnemies.size()) {
            Enemy enemy = waveEnemies.get(enemiesSpawned);
            model.spawnEnemy(enemy);
            enemiesSpawned++;
        }
    }

    public void nextWave() {
        waveNumber++;
        enemiesSpawned = 0;
        model.setWave(waveNumber);
    }

    public boolean isWaveComplete() {
        return model.getEnemies().isEmpty() && enemiesSpawned == waveEnemies.size();
    }

    public void reset() {
        waveNumber = 1;
        enemiesSpawned = 0;
    }

    public int getWaveNumber() {
        return waveNumber;
    }

    public int getNumberOfWaves() {
        return waves.size();
    }

}
