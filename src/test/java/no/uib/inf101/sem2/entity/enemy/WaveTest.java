package no.uib.inf101.sem2.entity.enemy;

import no.uib.inf101.sem2.model.TowerDefenseField;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaveTest {

    private Wave wave;
    private TowerDefenseField field;
    private TowerDefenseModel model;

    @BeforeEach
    void setUp() {
        field = new TowerDefenseField(10, 10);
        model = new TowerDefenseModel(field);
        wave = new Wave(model);
    }

    @Test
    void testSpawnEnemiesForWave() {
        wave.spawnEnemiesForWave();
        assertFalse(model.getEnemies().isEmpty());
    }

    @Test
    void testIsWaveComplete() {
        wave.spawnEnemiesForWave();
        model.clockTick();
        assertFalse(wave.isWaveComplete());
    }

    @Test
    void testNextWave() {
        wave.spawnEnemiesForWave();
        wave.nextWave();
        assertEquals(2, wave.getWaveNumber());
    }

    @Test
    void testReset() {
        wave.spawnEnemiesForWave();
        wave.nextWave();
        wave.reset();
        assertEquals(1, wave.getWaveNumber());
    }

    @Test
    void testGetWaveNumber() {
        assertEquals(1, wave.getWaveNumber());
    }

    @Test
    void testGetNumberOfWaves() {
        assertEquals(19, wave.getNumberOfWaves());
    }

}
