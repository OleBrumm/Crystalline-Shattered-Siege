package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.view.TowerDefenseView;
import no.uib.inf101.sem2.view.buttons.ButtonManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TowerDefenseController implements KeyListener {

    ControllableTowerDefenseModel model;
    TowerDefenseView view;
    Timer timer;

    private int tickCounter = 0;
    private final int ticksBetweenSpawns = 60; // Adjust this value to change the frequency of enemy spawns


    public TowerDefenseController(TowerDefenseModel towerDefenseModel, TowerDefenseView towerDefenseView, ButtonManager buttonManager) {
        // Set the model and view
        this.model = towerDefenseModel;
        this.view = towerDefenseView;
        // Add the controller as a key listener to the view
        view.addKeyListener(this);
        // Create a new timer
        this.timer = new Timer(towerDefenseModel.getTimerInterval(), this::clockTick);

        // Update button actions
        updateButtonActions(buttonManager);
    }

    @Override
    public void keyPressed(KeyEvent e) {
       // TODO: Implement this method
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



    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
