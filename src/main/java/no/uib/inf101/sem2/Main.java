package no.uib.inf101.sem2;

import no.uib.inf101.sem2.controller.TowerDefenseController;
import no.uib.inf101.sem2.model.TowerDefenseField;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.view.TowerDefenseView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

public class Main {

    private final static int FIELD_WIDTH = 16;
    private final static int FIELD_HEIGHT = 12;

    public static void main(String[] args) throws IOException, FontFormatException {
        TowerDefenseField towerDefenseField = new TowerDefenseField(FIELD_HEIGHT, FIELD_WIDTH);

        TowerDefenseModel towerDefenseModel = new TowerDefenseModel(towerDefenseField);
        TowerDefenseView towerDefenseView = new TowerDefenseView(towerDefenseModel);
        new TowerDefenseController(towerDefenseModel, towerDefenseView);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Crystalline: Shattered Siege");
        frame.setContentPane(towerDefenseView);
        frame.pack();
        frame.setVisible(true);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                maintainAspectRatio(frame);
            }
        });
    }

    private static void maintainAspectRatio(JFrame frame) {
        int newWidth = frame.getWidth();
        int newHeight = frame.getHeight();

        double aspectRatio = (double) Main.FIELD_WIDTH / Main.FIELD_HEIGHT;

        if (newWidth / aspectRatio > newHeight) {
            newWidth = (int) (newHeight * aspectRatio);
        } else {
            newHeight = (int) (newWidth / aspectRatio);
        }

        frame.setSize(newWidth, newHeight);
    }

}
