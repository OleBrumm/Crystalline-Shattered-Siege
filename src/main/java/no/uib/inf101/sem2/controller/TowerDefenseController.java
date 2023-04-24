package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.entity.projectile.projectiletypes.Explosion;
import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.entity.tower.towertypes.TowerFire;
import no.uib.inf101.sem2.entity.tower.towertypes.TowerIce;
import no.uib.inf101.sem2.entity.tower.towertypes.TowerTree;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.converter.CellPositionToPixelConverter;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.view.TowerDefenseView;
import no.uib.inf101.sem2.view.ui.buttons.ButtonManager;
import no.uib.inf101.sem2.view.views.GameRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

public class TowerDefenseController {

    private final TowerDefenseModel model;
    private final TowerDefenseView view;
    final Timer timer;
    private final CellPositionToPixelConverter converter;
    private final GameRenderer renderer;

    String selectedTowerType = null;
    private int tickCounter = 0;

    public TowerDefenseController(TowerDefenseModel model, TowerDefenseView view, ButtonManager buttonManager) {
        this.model = model;
        this.view = view;

        TowerDefenseMouseListener mouseListener = new TowerDefenseMouseListener(this);
        view.addMouseListener(mouseListener);
        view.addMouseMotionListener(mouseListener);

        timer = new Timer(model.getTimerInterval(), this::clockTick);

        converter = view.getGameRenderer().getCellPositionConverter();
        renderer = view.getGameRenderer();

        updateButtonActions(buttonManager);
    }

    void startGame() {
        model.startGame();
        timer.start();
    }

    void pauseGame() {
        if (model.getGameState() == GameState.IN_GAME) {
            model.pauseGame();
            timer.stop();
        } else if (model.getGameState() == GameState.PAUSE_SCREEN) {
            resumeGame();
        }
    }

    void resumeGame() {
        model.resumeGame();
        timer.start();
    }

    void restartGame() {
        System.out.println("Restarting game from controller");
        model.restartGame();
        timer.restart();
    }

    void mainMenu() {
        model.mainMenu();
        timer.stop();
    }

    void clockTick(ActionEvent event) {
        model.clockTick();
        view.repaint();

        int ticksBetweenSpawns = 25;
        if (++tickCounter % ticksBetweenSpawns == 0) {
            model.getWaveManager().spawnEnemiesForWave();
        }

        if (model.getWaveManager().isWaveComplete()) {
            tickCounter = 0;
            System.out.println("Wave complete!");
            model.getWaveManager().nextWave();
        }

        List<Explosion> activeExplosions = model.getExplosions();
        Iterator<Explosion> iterator = activeExplosions.iterator();
        while (iterator.hasNext()) {
            Explosion explosion = iterator.next();
            explosion.update();

            if (explosion.isFinished()) {
                iterator.remove();
            }
        }
    }

    public void updateButtonActions(ButtonManager buttonManager) {
        buttonManager.startButton.addActionListener(e -> {
            startGame();
            buttonManager.updateButtonVisibility(model.getGameState());
            System.out.println("Starting game from button");
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
            System.out.println("Restarting game from button");
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

    public void handleMouseDragged(MouseEvent event) {
        if (selectedTowerType == null) {
            return;
        }
        Tower tower = createTower(selectedTowerType, event.getX(), event.getY());
        renderer.setDraggedTower(tower, event.getPoint());
        view.repaint();
    }

    public void handleMouseReleased(MouseEvent event) {
        if (renderer == null || !renderer.isPointInGameRectangle(event.getPoint())) {
            return;
        }
        CellPosition closestCell = renderer.getClosestCell(event.getPoint());
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

    public void handleMousePressed(MouseEvent event) {
        Point clickPoint = event.getPoint();
        setSelectedTowerType(renderer.getTowerTypeForPoint(clickPoint));
    }

    private Tower createTower(String towerType, int x, int y) {
        return switch (towerType) {
            case "TowerTree" -> new TowerTree(x, y, converter);
            case "TowerIce" -> new TowerIce(x, y, converter);
            case "TowerFire" -> new TowerFire(x, y, converter);
            default -> null;
        };
    }
}
