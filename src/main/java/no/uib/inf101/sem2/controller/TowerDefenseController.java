package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.view.TowerDefenseView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        this.timer.start();
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
        model.pauseGame();
        if (model.getGameState() == GameState.PAUSE_SCREEN) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    private void clockTick(ActionEvent actionEvent) {
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
