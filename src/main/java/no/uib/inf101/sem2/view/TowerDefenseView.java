package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.view.backgrounds.BackgroundImages;
import no.uib.inf101.sem2.view.buttons.ButtonManager;
import no.uib.inf101.sem2.view.resources.ImageResources;
import no.uib.inf101.sem2.view.views.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class TowerDefenseView extends JPanel {

    // Window dimensions
    public final static int WINDOW_WIDTH = 800;
    public final static int WINDOW_HEIGHT = 600;

    // Game Model
    private final TowerDefenseModel model;

    // Initialize button manager
    private final ButtonManager buttonManager;

    // Initialize image resources
    private final ImageResources imageResources;

    // Initialize background images
    private final BackgroundImages backgroundImages;

    // Initialize renderers
    private final MainMenuRenderer mainMenuRenderer;
    private final GameOverRenderer gameOverRenderer;
    private final GameRenderer gameRenderer;
    private final PausedScreenRenderer pausedScreenRenderer;
    private final VictoryScreenRenderer victoryScreenRenderer;

    public TowerDefenseView(TowerDefenseModel model) throws IOException, FontFormatException {
        this.model = model;

        // Set up the window
        this.setFocusable(true);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        // Load images
        backgroundImages = new BackgroundImages();

        // Initialize image resources
        imageResources = new ImageResources();

        // Button Image
        Image buttonImage = imageResources.getImage("Button");

        // Initialize button manager
        buttonManager = new ButtonManager(model, buttonImage);
        buttonManager.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        buttonManager.updateButtonVisibility(model.getGameState());
        this.add(buttonManager);

        // Fonts
        File fontFile = new File("src/main/resources/misc/BebasNeue-Regular.ttf");
        Font titleFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        // Initialize view renderers
        mainMenuRenderer = new MainMenuRenderer(backgroundImages, titleFont, WINDOW_WIDTH, WINDOW_HEIGHT);
        gameOverRenderer = new GameOverRenderer(titleFont, WINDOW_WIDTH, WINDOW_HEIGHT);
        gameRenderer = new GameRenderer(model, backgroundImages, imageResources, WINDOW_WIDTH, WINDOW_HEIGHT);
        pausedScreenRenderer = new PausedScreenRenderer(backgroundImages, titleFont, WINDOW_WIDTH, WINDOW_HEIGHT);
        victoryScreenRenderer = new VictoryScreenRenderer(titleFont, WINDOW_WIDTH, WINDOW_HEIGHT);


    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        // https://stackoverflow.com/questions/13236797/anti-aliasing-in-paintcomponent-method
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        switch (model.getGameState()) {
            case MAIN_MENU -> mainMenuRenderer.drawMainMenu(graphics2D);
            case IN_GAME -> gameRenderer.drawGame(graphics2D);
            case GAME_OVER_SCREEN -> gameOverRenderer.drawGameOver(graphics2D);
            case VICTORY_SCREEN -> victoryScreenRenderer.drawVictoryScreen(graphics2D);
            case PAUSE_SCREEN -> pausedScreenRenderer.drawPauseScreen(graphics2D);
        }
    }
}
