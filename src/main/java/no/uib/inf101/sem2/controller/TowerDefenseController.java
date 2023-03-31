package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.view.TowerDefenseView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class TowerDefenseController implements KeyListener {

    ControllableTowerDefenseModel model;
    TowerDefenseView view;
    Timer timer;
    public TowerDefenseController(TowerDefenseModel towerDefenseModel, TowerDefenseView towerDefenseView) {
        // Set the model and view
        this.model = towerDefenseModel;
        this.view = towerDefenseView;
        // Add the controller as a key listener to the view
        view.addKeyListener(this);
        // Create a new timer
        this.timer = new Timer(towerDefenseModel.getTimerInterval(), this::clockTick);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_E) {
            System.out.println(model.getGameState());
        }
        switch (model.getGameState()) {
            case MAIN_MENU -> {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER -> startGame();
                    case KeyEvent.VK_ESCAPE -> System.exit(0);
                }
            }
            case IN_GAME -> {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    model.pauseGame();
                    timer.stop();
                }
            }
            case PAUSE_SCREEN -> {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    model.resumeGame();
                    timer.start();
                }
            }
        }
        view.repaint();
    }

    // Start the game and start the timer
    public void startGame() {
        model.startGame();
        timer.start();
    }

    // Pause/unpause the game and stop/start the timer
    public void pauseGame() {
        model.pauseGame();
        if (model.getGameState() == GameState.PAUSE_SCREEN) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    private void clockTick(ActionEvent actionEvent) {
        if (model.getGameState() == GameState.GAME_OVER_SCREEN) {
            timer.stop();
            return;
        } else if (model.getGameState() == GameState.PAUSE_SCREEN) {
            timer.stop();
            return;
        }
        setTimerInterval(model.getTimerInterval());
        model.clockTick();
        view.repaint();
    }

    private void setTimerInterval(int timerInterval) {
        timer.setDelay(timerInterval);
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
