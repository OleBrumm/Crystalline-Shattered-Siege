package no.uib.inf101.sem2;

import no.uib.inf101.sem2.controller.TowerDefenseController;
import no.uib.inf101.sem2.model.TowerDefenseField;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.view.TowerDefenseView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    private final static int FIELD_WIDTH = 16;
    private final static int FIELD_HEIGHT = 12;
    private final static int WINDOW_WIDTH = 800;
    private final static int WINDOW_HEIGHT = 600;

    public static void main(String[] args) throws IOException, FontFormatException {
        TowerDefenseField towerDefenseField = new TowerDefenseField(FIELD_HEIGHT, FIELD_WIDTH);

        TowerDefenseModel towerDefenseModel = new TowerDefenseModel(towerDefenseField);
        TowerDefenseView towerDefenseView = new TowerDefenseView(towerDefenseModel);

        new TowerDefenseController(towerDefenseModel, towerDefenseView, towerDefenseView.getButtonManager());

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Crystalline: Shattered Siege");
        frame.setContentPane(towerDefenseView);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

}
