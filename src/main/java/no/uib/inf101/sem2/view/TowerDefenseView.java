package no.uib.inf101.sem2.view;


import no.uib.inf101.sem2.grid.CellPositionToPixelConverter;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.TowerDefenseField;
import no.uib.inf101.sem2.model.TowerDefenseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import static no.uib.inf101.sem2.view.Inf101Graphics.drawCenteredString;
import static no.uib.inf101.sem2.view.Inf101Graphics.loadImageFromResources;


public class TowerDefenseView extends JPanel {

    // Window dimensions
    private final static int windowWidth = 800;
    private final static int windowHeight = 600;
    // Button dimensions
    private final static int buttonWidth = windowWidth / 2;
    private final static int buttonHeight = windowHeight / 6;

    private final Image mainMenuBgImage;
    private final Image pausedBgImage;
    private final Image gameplayBgWithPathImage;
    private final Image sideBarBgImage;
    private final ViewableTowerDefenseModel model;
    private final File fontFile = new File("src/main/resources/misc/BebasNeue-Regular.ttf");
    private final Font titleFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
    // Create buttons
    ImageButton startButton;
    ImageButton restartButton;
    ImageButton pauseButton;
    ImageButton resumeButton;
    ImageButton mainMenuButton;
    ImageButton exitButton;

    public TowerDefenseView(TowerDefenseModel model) throws IOException, FontFormatException {
        this.model = model;

        // Set up the window
        this.setFocusable(true);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));

        // Load images
        Image buttonImage = loadImageFromResources("misc/button.png");
        mainMenuBgImage = loadImageFromResources("backgrounds/main-menu-bg.png");
        pausedBgImage = loadImageFromResources("backgrounds/paused-bg.png");
        gameplayBgWithPathImage = loadImageFromResources("backgrounds/gameplay-bg-withpath.png");
        sideBarBgImage = loadImageFromResources("backgrounds/tilable-bg.png");

        // Create buttons
        startButton = new ImageButton(buttonImage, "Start", e -> {
            model.startGame();
            updateButtonVisibility(model.getGameState());
        });
        restartButton = new ImageButton(buttonImage, "Restart", e -> {
            model.restartGame();
            updateButtonVisibility(model.getGameState());
        });
        pauseButton = new ImageButton(buttonImage, "Pause", e -> {
            model.pauseGame();
            updateButtonVisibility(model.getGameState());
        });
        resumeButton = new ImageButton(buttonImage, "Resume", e -> {
            model.resumeGame();
            updateButtonVisibility(model.getGameState());
        });
        mainMenuButton = new ImageButton(buttonImage, "Main Menu", e -> {
            model.mainMenu();
            updateButtonVisibility(model.getGameState());
        });
        exitButton = new ImageButton(buttonImage, "Exit", e -> {
            model.exitGame();
            updateButtonVisibility(model.getGameState());
        });


        // Add buttons to the panel
        add(startButton);
        add(restartButton);
        add(pauseButton);
        add(resumeButton);
        add(mainMenuButton);
        add(exitButton);

        // Set initial visibility of buttons
        updateButtonVisibility(model.getGameState());

        // Add a component listener to call positionButtons when the component is resized
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                positionButtons();
            }
        });

    }

    private static void drawCells(Graphics2D graphics2D,
                                  Iterable<GridCell<Character>> cells,
                                  CellPositionToPixelConverter converter,
                                  Color backgroundColor) {
        for (GridCell<Character> cell : cells) {
            drawCell(graphics2D, cell, converter, backgroundColor);
        }
    }

    private static void drawCell(Graphics2D graphics2D,
                                 GridCell<Character> cell,
                                 CellPositionToPixelConverter converter,
                                 Color backgroundColor) {
        Rectangle2D cellRectangle = converter.getBoundsForCell(cell.pos());
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(cellRectangle);
        graphics2D.setColor(backgroundColor);
        graphics2D.fill(cellRectangle);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        /* https://stackoverflow.com/questions/13236797/anti-aliasing-in-paintcomponent-method */
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

    private void updateButtonVisibility(GameState gameState) {
        startButton.setVisible(false);
        restartButton.setVisible(false);
        pauseButton.setVisible(false);
        resumeButton.setVisible(false);
        mainMenuButton.setVisible(false);
        exitButton.setVisible(false);

        switch (gameState) {
            case MAIN_MENU -> {
                startButton.setVisible(true);
                exitButton.setVisible(true);
            }
            case IN_GAME -> pauseButton.setVisible(true);
            case PAUSE_SCREEN -> {
                resumeButton.setVisible(true);
                restartButton.setVisible(true);
                mainMenuButton.setVisible(true);
            }
            case GAME_OVER_SCREEN, VICTORY_SCREEN -> {
                restartButton.setVisible(true);
                mainMenuButton.setVisible(true);
            }
        }
    }


    private void positionButtons() {
        double titleHeight = getHeight() / 4.0;

        positionButton(startButton, 0, 2, titleHeight);
        positionButton(restartButton, 1, 3, titleHeight);
        positionButton(pauseButton, 0, 1, titleHeight);
        positionButton(resumeButton, 0, 3, titleHeight);
        positionButton(mainMenuButton, 2, 3, titleHeight);
        positionButton(exitButton, 1, 2, titleHeight);
    }

    private void positionButton(ImageButton button, int index, int totalButtons, double titleHeight) {
        double screenWidth = getWidth();
        double screenHeight = getHeight();
        double spacing = (screenHeight - titleHeight) / (totalButtons + 1);
        int buttonX = (int) ((screenWidth - buttonWidth) / 2);
        int buttonY = (int) (titleHeight + (index + 1) * (spacing));

        button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight); // Set the bounds for the button
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
        graphics2D.setFont(new Font("Arial", Font.BOLD, 40));
        graphics2D.setColor(Color.WHITE);
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
        graphics2D.setFont(titleFont.deriveFont(75f));
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("Paused", 330, 100);
    }

    /**
     * Draws the game
     *
     * @param graphics2D the graphics object to draw on
     */
    private void drawGame(Graphics2D graphics2D) {
        Rectangle2D gameRectangle =
                new Rectangle2D.Double(0,
                        0,
                        8 * (double) getWidth() / 10,
                        8 * (double) getHeight() / 10);

        drawImageRectangle(graphics2D, gameRectangle, gameplayBgWithPathImage);

        drawShop(graphics2D);
        drawBottomBar(graphics2D);
    }

    /**
     * Draws the bottom bar
     *
     * @param graphics2D the graphics object to draw on
     */
    private void drawBottomBar(Graphics2D graphics2D) {
        for (int i = 0; i < 4; i++) {
            Rectangle2D bottomBarCellRectangle =
                    new Rectangle2D.Double(i * (double) getWidth() / 4,
                            8 * (double) getHeight() / 10,
                            (double) getWidth() / 4,
                            2 * (double) getHeight() / 10);
            drawImageRectangle(graphics2D, bottomBarCellRectangle, sideBarBgImage);
        }

    }

    /**
     * Draws the shop
     *
     * @param graphics2D the graphics object to draw on
     */
    private void drawShop(Graphics2D graphics2D) {
        Rectangle2D shopTitleRectangle =
                new Rectangle2D.Double(8 * (double) windowWidth / 10,
                        0,
                        2 * (double) windowWidth / 10,
                        (double) windowHeight / 10);
        Rectangle2D shopRectangle =
                new Rectangle2D.Double(8 * (double) windowWidth / 10,
                        (double) windowHeight / 10,
                        2 * (double) windowWidth / 10,
                        7 * (double) windowHeight / 10);
        graphics2D.setColor(new Color(0x6A0DAD));
        graphics2D.fill(shopTitleRectangle);
        graphics2D.setColor(new Color(0xFAF0E6));
        graphics2D.setFont(titleFont.deriveFont(35f));
        drawCenteredString(graphics2D, "Shop", shopTitleRectangle);

        TowerDefenseField shopField = new TowerDefenseField(3, 1);
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(shopRectangle, shopField, (double) windowHeight / 20);

        drawImageRectangle(graphics2D, shopRectangle, sideBarBgImage);
        drawCells(graphics2D, shopField, converter, new Color(0x6A0DAD));
    }

    /**
     * Draws an image within a rectangle
     *
     * @param graphics2D the graphics object to draw on
     * @param rectangle  the rectangle to draw the image in
     * @param image      the image to draw
     */
    private void drawImageRectangle(Graphics2D graphics2D, Rectangle2D rectangle, Image image) {
        graphics2D.drawImage(image, (int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight(), null);
    }
}
