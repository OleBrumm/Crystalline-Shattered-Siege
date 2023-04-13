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

/**
 * The TowerDefenseView class represents the view component of the Tower Defense game.
 * It is responsible for rendering the game state and managing buttons and images.
 */
public class TowerDefenseView extends JPanel {

    // Window dimensions
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    // Game Model
    private final TowerDefenseModel model;

    // Button manager
    private final ButtonManager buttonManager;

    // Image resources
    private final ImageResources imageResources;

    // Background images
    private final BackgroundImages backgroundImages;

    // View renderers
    private final MainMenuRenderer mainMenuRenderer;
    private final GameOverRenderer gameOverRenderer;
    private final GameRenderer gameRenderer;
    private final PausedScreenRenderer pausedScreenRenderer;
    private final VictoryScreenRenderer victoryScreenRenderer;


    /**
     * Constructs a new TowerDefenseView with the specified game model.
     *
     * @param model The game model.
     * @throws IOException          If an error occurs while reading image files.
     * @throws FontFormatException If an error occurs while reading font files.
     */
    public TowerDefenseView(TowerDefenseModel model) throws IOException, FontFormatException {
        this.model = model;

        // Set up the window
        this.setFocusable(true);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        // Load background images
        backgroundImages = new BackgroundImages();

        // Initialize image resources
        imageResources = new ImageResources();

        // Load button image
        Image buttonImage = imageResources.getImage("Button");

        // Initialize button manager
        buttonManager = new ButtonManager(buttonImage);
        buttonManager.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        buttonManager.updateButtonVisibility(model.getGameState());
        this.add(buttonManager);

        // Load fonts
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        // Enable antialiasing for smoother graphics
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Draw the appropriate view based on the game state
        switch (model.getGameState()) {
            case MAIN_MENU -> mainMenuRenderer.drawMainMenu(graphics2D);
            case IN_GAME -> gameRenderer.drawGame(graphics2D);
            case PAUSE_SCREEN -> pausedScreenRenderer.drawPauseScreen(graphics2D);
            case GAME_OVER_SCREEN -> gameOverRenderer.drawGameOver(graphics2D);
            case VICTORY_SCREEN -> victoryScreenRenderer.drawVictoryScreen(graphics2D);
        }
        repaint();
    }

    /**
     * Returns the button manager, which can be used by the controller to add action listeners to the buttons.
     *
     * @return The button manager.
     */
    public ButtonManager getButtonManager() {
        return buttonManager;
    }

    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }
}


