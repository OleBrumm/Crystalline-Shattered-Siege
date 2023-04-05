package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.grid.CellPositionToPixelConverter;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.view.backgrounds.BackgroundImages;
import no.uib.inf101.sem2.view.buttons.ButtonManager;
import no.uib.inf101.sem2.view.renderers.EnemyRenderer;
import no.uib.inf101.sem2.view.renderers.GameStatusBarRenderer;
import no.uib.inf101.sem2.view.renderers.RenderingUtils;
import no.uib.inf101.sem2.view.renderers.TowerRenderer;
import no.uib.inf101.sem2.view.resources.ImageResources;

import java.util.Map;
import java.util.HashMap;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import static no.uib.inf101.sem2.view.Inf101Graphics.drawCenteredString;

public class TowerDefenseView extends JPanel {

    // Window dimensions
    public final static int windowWidth = 1024;
    public final static int windowHeight = 768;

    // Background images
    private final Image mainMenuBgImage;
    private final Image pausedBgImage;
    private final Image gameplayBgWithPathImage;
    private final Image gameStatusBarBgImage;

    // Game Model
    private final TowerDefenseModel model;

    // Fonts
    private final File fontFile = new File("src/main/resources/misc/BebasNeue-Regular.ttf");
    private final Font titleFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

    // Tower images
    Image towerIceImage;
    Image towerFireImage;
    Image towerTreeImage;

    // Enemy images
    Image enemyRedImage;
    Image enemyBlueImage;
    Image enemyYellowImage;

    // Icon images
    Image iconGoldImage;
    Image iconHeartImage;
    Image iconWaveImage;


    // Initialize button manager
    ButtonManager buttonManager;

    // Initialize image resources
    ImageResources imageResources;

    // Initialize background images
    BackgroundImages backgroundImages;

    public TowerDefenseView(TowerDefenseModel model) throws IOException, FontFormatException {
        this.model = model;

        // Set up the window
        this.setFocusable(true);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));

        // Load images
        backgroundImages = new BackgroundImages();
        mainMenuBgImage = backgroundImages.mainMenuBgImage;
        pausedBgImage = backgroundImages.pausedBgImage;
        gameplayBgWithPathImage = backgroundImages.gameplayBgWithPathImage;
        gameStatusBarBgImage = backgroundImages.gameStatusBarBgImage;

        // Initialize image resources
        imageResources = new ImageResources();

        // Button Image
        Image buttonImage = imageResources.getImage("Button");

        // Tower Images
        towerIceImage = imageResources.getImage("TowerIce");
        towerFireImage = imageResources.getImage("TowerFire");
        towerTreeImage = imageResources.getImage("TowerTree");

        // Enemy Images
        enemyRedImage = imageResources.getImage("EnemyRed");
        enemyBlueImage = imageResources.getImage("EnemyBlue");
        enemyYellowImage = imageResources.getImage("EnemyYellow");

        // Icon Images
        iconGoldImage = imageResources.getImage("GoldIcon");
        iconHeartImage = imageResources.getImage("HeartIcon");
        iconWaveImage = imageResources.getImage("WaveIcon");

        // Initialize button manager
        buttonManager = new ButtonManager(model, buttonImage);
        buttonManager.setBounds(0, 0, windowWidth, windowHeight);
        buttonManager.updateButtonVisibility(model.getGameState());
        this.add(buttonManager);

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
            case MAIN_MENU -> drawMainMenu(graphics2D);
            case IN_GAME -> drawGame(graphics2D);
            case GAME_OVER_SCREEN -> drawGameOver(graphics2D);
            case VICTORY_SCREEN -> drawGameWon(graphics2D);
            case PAUSE_SCREEN -> drawPauseMenu(graphics2D);
        }

        repaint();
    }

    /**
     * Draws the main menu
     *
     * @param graphics2D the graphics object to draw on
     */
    private void drawMainMenu(Graphics2D graphics2D) {
        // Draw background
        drawImageRectangle(graphics2D, new Rectangle2D.Double(0, 0, getWidth(), getHeight() + 100), mainMenuBgImage);

        // Draw title
        graphics2D.setFont(titleFont.deriveFont(75f));
        graphics2D.setColor(Color.WHITE);
        drawCenteredString(graphics2D,
                "Crystalline: Shattered Siege",
                new Rectangle2D.Double(0, 0, getWidth(), (double) getHeight() / 4) {
                });
    }

    /**
     * Draws the game over screen
     *
     * @param graphics2D the graphics object to draw on
     */
    private void drawGameOver(Graphics2D graphics2D) {
        // Draw background
        graphics2D.setColor(Color.RED);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());

        // Draw title
        graphics2D.setFont(titleFont.deriveFont(75f));
        graphics2D.setColor(Color.RED);
        graphics2D.drawString("Game Over", 300, 100);
    }

    /**
     * Draws the victory screen
     *
     * @param graphics2D Graphics2D object to draw on
     */
    private void drawGameWon(Graphics2D graphics2D) {
        // Draw background
        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());

        // Draw title
        graphics2D.setFont(new Font("Arial", Font.BOLD, 40));
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("You Won!", 300, 100);
    }

    /**
     * Draws the pause menu
     *
     * @param graphics2D the graphics object to draw on
     */
    private void drawPauseMenu(Graphics2D graphics2D) {
        // Draw background
        drawImageRectangle(graphics2D, new Rectangle2D.Double(0, 0, getWidth(), getHeight() + 100), pausedBgImage);

        // Draw title
        graphics2D.setFont(titleFont.deriveFont(100f));
        graphics2D.setColor(Color.WHITE);
        drawCenteredString(graphics2D,
                "Paused",
                new Rectangle2D.Double(0, 0, getWidth(), (double) getHeight() / 4) {
                });
    }

    /**
     * Draws the game
     *
     * @param graphics2D the graphics object to draw on
     */
    private void drawGame(Graphics2D graphics2D) {

        // Draw the background
        Rectangle2D gameRectangle =
                new Rectangle2D.Double(0,
                        0,
                        getWidth(),
                        8 * (double) getHeight() / 10);
        drawImageRectangle(graphics2D, gameRectangle, gameplayBgWithPathImage);

        // Draw the grid lines on the game board
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(gameRectangle, model.getGridDimension(), 0);
        RenderingUtils.drawCells(graphics2D, model.getTilesOnBoard(), converter, new Color(0, 0, 0, 0));

        // Add the enemy images to a map
        Map<String, Image> enemyImages = new HashMap<>();
        enemyImages.put("EnemyRed", enemyRedImage);
        enemyImages.put("EnemyBlue", enemyBlueImage);
        enemyImages.put("EnemyYellow", enemyYellowImage);

        // Draw the enemies
        EnemyRenderer enemyRenderer = new EnemyRenderer(enemyImages);
        enemyRenderer.drawEnemies(graphics2D, converter, model.getEnemies());

        // Add the tower images to a map
        Map<String, Image> towerImages = new HashMap<>();
        towerImages.put("TowerTree", towerIceImage);
        towerImages.put("TowerIce", towerFireImage);
        towerImages.put("TowerFire", towerTreeImage);

        // Draw the towers
        TowerRenderer towerRenderer = new TowerRenderer(towerImages);
        towerRenderer.drawTowers(graphics2D, converter, model.getTowers());

        // Add the icon images to a map
        Map<String, Image> iconImages = new HashMap<>();
        iconImages.put("GoldIcon", iconGoldImage);
        iconImages.put("HeartIcon", iconHeartImage);
        iconImages.put("WaveIcon", iconWaveImage);

        // Draw the game status bar
        GameStatusBarRenderer gameStatusBarRenderer = new GameStatusBarRenderer(getWidth(), getHeight(), gameStatusBarBgImage, towerImages, iconImages, model);
        gameStatusBarRenderer.drawGameStatusBar(graphics2D);
    }

    /**
     * Draws an image within a rectangle
     *
     * @param graphics2D the graphics object to draw on
     * @param rectangle  the rectangle to draw the image in
     * @param image      the image to draw
     */
    public static void drawImageRectangle(Graphics2D graphics2D, Rectangle2D rectangle, Image image) {
        graphics2D.drawImage(image, (int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight(), null);
    }
}
