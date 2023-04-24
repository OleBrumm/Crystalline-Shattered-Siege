package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.entity.tower.towertypes.TowerIce;
import no.uib.inf101.sem2.entity.tower.towertypes.TowerTree;
import no.uib.inf101.sem2.grid.converter.CellPositionToPixelConverter;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.TowerDefenseField;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.view.Inf101Graphics;
import no.uib.inf101.sem2.view.TowerDefenseView;
import no.uib.inf101.sem2.view.ui.ImageButton;
import no.uib.inf101.sem2.view.ui.buttons.ButtonManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TowerDefenseControllerTest {

    private TowerDefenseModel model;
    private TowerDefenseController controller;
    private ButtonManager buttonManager;

    @BeforeEach
    public void setUp() throws IOException, FontFormatException {
        TowerDefenseField field = new TowerDefenseField(10, 10);
        model = new TowerDefenseModel(field);
        TowerDefenseView view = new TowerDefenseView(model);
        buttonManager = new ButtonManager(Inf101Graphics.loadImageFromResources("misc/button.png"));
        controller = new TowerDefenseController(model, view, buttonManager);
    }

    @Test
    public void testStartGame() {
        assertEquals(GameState.MAIN_MENU, model.getGameState());
        controller.startGame();
        assertEquals(GameState.IN_GAME, model.getGameState());
        assertTrue(controller.timer.isRunning());
    }

    @Test
    public void testPauseGame() {
        assertEquals(GameState.MAIN_MENU, model.getGameState());
        controller.startGame();
        assertEquals(GameState.IN_GAME, model.getGameState());
        assertTrue(controller.timer.isRunning());

        controller.pauseGame();
        assertEquals(GameState.PAUSE_SCREEN, model.getGameState());
        assertFalse(controller.timer.isRunning());
    }

    @Test
    public void testResumeGame() {
        assertEquals(GameState.MAIN_MENU, model.getGameState());
        controller.startGame();
        assertEquals(GameState.IN_GAME, model.getGameState());
        assertTrue(controller.timer.isRunning());

        controller.pauseGame();
        assertEquals(GameState.PAUSE_SCREEN, model.getGameState());
        assertFalse(controller.timer.isRunning());

        controller.resumeGame();
        assertEquals(GameState.IN_GAME, model.getGameState());
        assertTrue(controller.timer.isRunning());
    }

    @Test
    public void testRestartGame() {
        assertEquals(GameState.MAIN_MENU, model.getGameState());
        controller.startGame();
        assertEquals(GameState.IN_GAME, model.getGameState());
        assertTrue(controller.timer.isRunning());

        // add some towers to the game state
        List<Tower> towers = new ArrayList<>();
        towers.add(new TowerTree(0, 0, new CellPositionToPixelConverter(null, null, 0)));
        towers.add(new TowerIce(1, 1, new CellPositionToPixelConverter(null, null, 0)));
        model.setTowers(towers);

        controller.restartGame();

        Assertions.assertEquals(GameState.IN_GAME, model.getGameState());
        Assertions.assertEquals(1, model.getWave());
        Assertions.assertEquals(100, model.getLives());
        Assertions.assertEquals(800, model.getGold());
        Assertions.assertTrue(model.getEnemies().isEmpty());
        Assertions.assertTrue(model.getTowers().isEmpty());
        Assertions.assertTrue(model.getProjectiles().isEmpty());
        Assertions.assertTrue(model.getExplosions().isEmpty());

    }

    @Test
    public void testMainMenu() {
        assertEquals(GameState.MAIN_MENU, model.getGameState());
        controller.startGame();
        assertEquals(GameState.IN_GAME, model.getGameState());
        assertTrue(controller.timer.isRunning());

        controller.mainMenu();

        assertEquals(GameState.MAIN_MENU, model.getGameState());
        assertFalse(controller.timer.isRunning());
    }

    @Test
    public void testClockTick() {
        controller.clockTick(new ActionEvent(new Object(), 0, ""));
        System.out.println(model.getEnemies());
        assertEquals(0, model.getEnemies().size());

        // move the enemies
        for (int i = 0; i < 10; i++) {
            controller.clockTick(new ActionEvent(new Object(), 0, ""));
        }
        assertTrue(model.getEnemies().isEmpty());
        assertEquals(1, model.getWaveManager().getWaveNumber());

        // jump to next wave
        model.getWaveManager().nextWave();
        assertEquals(2, model.getWaveManager().getWaveNumber());
    }

    @Test
    public void testUpdateButtonActions() {
        ImageButton startButton = buttonManager.startButton;
        ImageButton pauseButton = buttonManager.pauseButton;
        ImageButton resumeButton = buttonManager.resumeButton;
        ImageButton restartButton = buttonManager.restartButton;
        ImageButton mainMenuButton = buttonManager.mainMenuButton;
        ImageButton exitButton = buttonManager.exitButton;

        // Checks if the length of the action listeners is 2
        // Does this because the buttons are initialized with 2 action listener
        assertEquals(2, startButton.getActionListeners().length);
        assertEquals(2, pauseButton.getActionListeners().length);
        assertEquals(2, resumeButton.getActionListeners().length);
        assertEquals(2, restartButton.getActionListeners().length);
        assertEquals(2, mainMenuButton.getActionListeners().length);
        assertEquals(2, exitButton.getActionListeners().length);


        controller.updateButtonActions(buttonManager);

        // Checks if the length of the action listeners is 3
        // Does this because the buttons should now be updated to have one more action listener
        assertEquals(3, startButton.getActionListeners().length);
        assertEquals(3, pauseButton.getActionListeners().length);
        assertEquals(3, resumeButton.getActionListeners().length);
        assertEquals(3, restartButton.getActionListeners().length);
        assertEquals(3, mainMenuButton.getActionListeners().length);
        assertEquals(3, exitButton.getActionListeners().length);

    }

    @Test
    public void testSetSelectedTowerType() {
        // asserts that the selected tower type is null
        assertNull(controller.selectedTowerType);

        // sets the selected tower type to TowerFire
        controller.setSelectedTowerType("TowerFire");

        // asserts that the selected tower type is TowerFire
        assertEquals("TowerFire", controller.selectedTowerType);
    }

    @Test
    public void testHandleMouseDragged() {

    }

    @Test
    public void testHandleMouseReleased() {
        // TODO: Implement test for handleMouseReleased() method
    }

    @Test
    public void testHandleMousePressed() {
        // TODO: Implement test for handleMousePressed() method
    }
}
