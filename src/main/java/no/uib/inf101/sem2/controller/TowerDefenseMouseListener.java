package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.view.views.GameRenderer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TowerDefenseMouseListener implements MouseListener, MouseMotionListener {

    private final TowerDefenseController controller;

    public TowerDefenseMouseListener(TowerDefenseController controller) {
        this.controller = controller;
        GameRenderer renderer = controller.view.getGameRenderer();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Implement this method to handle mouseClicked event
    }

    @Override
    public void mousePressed(MouseEvent e) {
        controller.handleMousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        controller.handleMouseReleased(e);
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        // Implement this method to handle mouseEntered event
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Implement this method to handle mouseExited event
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        controller.handleMouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Implement this method to handle mouseMoved event
    }
}
