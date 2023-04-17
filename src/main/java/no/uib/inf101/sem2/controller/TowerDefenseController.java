package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.entity.projectile.projectileTypes.Explosion;
import no.uib.inf101.sem2.entity.tower.*;
import no.uib.inf101.sem2.entity.tower.towerTypes.TowerFire;
import no.uib.inf101.sem2.entity.tower.towerTypes.TowerIce;
import no.uib.inf101.sem2.entity.tower.towerTypes.TowerTree;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.CellPositionToPixelConverter;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.view.TowerDefenseView;
import no.uib.inf101.sem2.view.buttons.ButtonManager;
import no.uib.inf101.sem2.view.views.GameRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.List;

public class TowerDefenseController {

    TowerDefenseModel model;
    TowerDefenseView view;
    Timer timer;
    private String selectedTowerType = null;
    private final CellPositionToPixelConverter converter;
    private final GameRenderer renderer;

    private int tickCounter = 0;


    public TowerDefenseController(TowerDefenseModel towerDefenseModel, TowerDefenseView towerDefenseView, ButtonManager buttonManager) {
        // Set the model and view
        this.model = towerDefenseModel;
        this.view = towerDefenseView;

        // Add the controller as a mouse listener to the view
        TowerDefenseMouseListener mouseListener = new TowerDefenseMouseListener(this);
        view.addMouseListener(mouseListener);
        view.addMouseMotionListener(mouseListener);

        // Create a new timer
        this.timer = new Timer(towerDefenseModel.getTimerInterval(), this::clockTick);

        // Set the converter
        this.converter = view.getGameRenderer().getCellPositionConverter();

        // Update button actions
        updateButtonActions(buttonManager);

        // Set the renderer
        this.renderer = view.getGameRenderer();
    }

    // Start the game and start the timer
    public void startGame() {
        model.startGame();
        timer.start();
    }

    // Pause/unpause the game and stop/start the timer
    public void pauseGame() {
        if (model.getGameState() == GameState.IN_GAME) {
            model.pauseGame();
            timer.stop();
        } else if (model.getGameState() == GameState.PAUSE_SCREEN) {
            resumeGame();
        }
    }

    // Resume the game and start the timer
    public void resumeGame() {
        model.resumeGame();
        timer.start();
    }

    // Restart the game and start the timer
    private void restartGame() {
        model.restartGame();
        timer.restart();
    }

    // Return to the main menu and stop the timer
    private void mainMenu() {
        model.mainMenu();
        timer.stop();
    }

    private void clockTick(ActionEvent actionEvent) {
        model.clockTick();
        view.repaint();

        // Adjust this value to change the frequency of enemy spawns
        int ticksBetweenSpawns = 25;
        if (tickCounter % ticksBetweenSpawns == 0) {
            model.getWaveManager().spawnEnemiesForWave();
        }

        if (model.getWaveManager().isWaveComplete()) {
            tickCounter = 0; // Reset the tickCounter when the wave is complete
            System.out.println("Wave complete!");
            model.getWaveManager().nextWave();
        }

        // Update explosions and remove the ones that are finished
        List<Explosion> activeExplosions = model.getExplosions();
        for (Iterator<Explosion> iterator = activeExplosions.iterator(); iterator.hasNext(); ) {
            Explosion explosion = iterator.next();
            explosion.update();

            if (explosion.isFinished()) {
                iterator.remove();
            }
        }

        tickCounter++;
    }

    public void updateButtonActions(ButtonManager buttonManager) {
        buttonManager.startButton.addActionListener(e -> {
            startGame();
            buttonManager.updateButtonVisibility(model.getGameState());
        });
        buttonManager.pauseButton.addActionListener(e -> {
            pauseGame();
            buttonManager.updateButtonVisibility(model.getGameState());
        });
        buttonManager.resumeButton.addActionListener(e -> {
            resumeGame();
            buttonManager.updateButtonVisibility(model.getGameState());
        });
        buttonManager.restartButton.addActionListener(e -> {
            restartGame();
            buttonManager.updateButtonVisibility(model.getGameState());
        });
        buttonManager.mainMenuButton.addActionListener(e -> {
            mainMenu();
            buttonManager.updateButtonVisibility(model.getGameState());
        });
        buttonManager.exitButton.addActionListener(e -> model.exitGame());
    }

    public void setSelectedTowerType(String selectedTowerType) {
        this.selectedTowerType = selectedTowerType;
    }

    private Tower createTower(String towerType, int x, int y) {
        return switch (towerType) {
            case "TowerTree" -> new TowerTree(x, y, converter);
            case "TowerIce" -> new TowerIce(x, y, converter);
            case "TowerFire" -> new TowerFire(x, y, converter);
            default -> null;
        };
    }

    public void handleMouseDragged(MouseEvent e) {
        if (selectedTowerType == null) {
            return;
        }
        Tower tower = createTower(selectedTowerType, e.getX(), e.getY());
        view.getGameRenderer().setDraggedTower(tower, e.getPoint());
        view.repaint();
    }

    public void handleMouseReleased(MouseEvent e) {
        if (renderer == null || !renderer.isPointInGameRectangle(e.getPoint())) {
            return;
        }
        CellPosition closestCell = renderer.getClosestCell(e.getPoint());
        if (!model.isTowerPlacementValid(closestCell)) {
            renderer.setDraggedTower(null, null);
            return;
        }
        if (renderer.getDraggedTower() == null) {
            return;
        }
        renderer.getDraggedTower().setPosition(closestCell);
        model.addTower(renderer.getDraggedTower());
        renderer.setDraggedTower(null, null);
    }

    public void handleMousePressed(MouseEvent e) {
        Point clickPoint = new Point(e.getX(), e.getY());
        setSelectedTowerType(renderer.getTowerTypeForPoint(clickPoint));
    }
}
