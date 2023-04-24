package no.uib.inf101.sem2.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TowerDefenseMouseListener implements MouseListener, MouseMotionListener {

    private final TowerDefenseController controller;

    public TowerDefenseMouseListener(TowerDefenseController controller) {
        this.controller = controller;
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
    public void mouseDragged(MouseEvent e) {
        controller.handleMouseDragged(e);
    }

    // These methods are not used
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
}
