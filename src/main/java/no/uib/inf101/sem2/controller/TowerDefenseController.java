package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.entity.tower.*;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.view.TowerDefenseView;
import no.uib.inf101.sem2.view.buttons.ButtonManager;
import no.uib.inf101.sem2.view.views.GameRenderer;

import javax.swing.*;
import java.awt.event.*;

public class TowerDefenseController {

    TowerDefenseModel model;
    TowerDefenseView view;
    Timer timer;
    private String selectedTowerType = null;


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

        // Update button actions
        updateButtonActions(buttonManager);
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
        int ticksBetweenSpawns = 60;
        if (tickCounter % ticksBetweenSpawns == 0) {
            model.getWaveManager().spawnEnemiesForWave();
        }

        if (model.getWaveManager().isWaveComplete()) {
            tickCounter = 0; // Reset the tickCounter when the wave is complete
            System.out.println("Wave complete!");
            model.getWaveManager().nextWave();
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
            case "TowerTree" -> new TowerTree(x, y);
            case "TowerIce" -> new TowerIce(x, y);
            case "TowerFire" -> new TowerFire(x, y);
            default -> null;
        };
    }

    public void handleMouseDragged(MouseEvent e) {
        if (selectedTowerType != null) {
            Tower tower = createTower(selectedTowerType, e.getX(), e.getY());
            view.getGameRenderer().setDraggedTower(tower, e.getPoint());
            view.repaint();
        }
    }

    public void handleMouseReleased(MouseEvent e) {
        GameRenderer renderer = view.getGameRenderer();
        if (renderer != null && renderer.isPointInGameRectangle(e.getPoint())) {
            CellPosition closestCell = renderer.getClosestCell(e.getPoint());
            if (!model.isTowerPlacementValid(closestCell)){
                return;
            }
            // Set the position of the dragged tower to the closest cell
            renderer.getDraggedTower().setPosition(closestCell);
            // Add the dragged tower to the model
            model.addTower(renderer.getDraggedTower());
            // Reset the dragged tower
            renderer.setDraggedTower(null, null);
        }
    }
}
