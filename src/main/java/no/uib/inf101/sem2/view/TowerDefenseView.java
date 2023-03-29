package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.grid.CellPositionToPixelConverter;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.TowerDefenseField;
import no.uib.inf101.sem2.model.TowerDefenseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static no.uib.inf101.sem2.view.Inf101Graphics.drawCenteredString;
import static no.uib.inf101.sem2.view.Inf101Graphics.loadImageFromResources;


public class TowerDefenseView extends JPanel {

    // Window dimensions
    private final static int windowWidth = 800;
    private final static int windowHeight = 600;
    // Button dimensions
    private final static int buttonWidth = windowWidth / 2;
    private final static int buttonHeight = windowHeight / 6;

    private final Image buttonImage;
    private final Image mainMenuBgImage;
    private final Image pausedBgImage;
    private final Image gameplayBgWithPathImage;
    private final Image sideBarBgImage;
    private final Image bottomBarBgImage;

    // Create buttons
    ImageButton startButton;
    ImageButton restartButton;
    ImageButton pauseButton;
    ImageButton resumeButton;
    ImageButton mainMenuButton;
    ImageButton exitButton;

    private final ViewableTowerDefenseModel model;
    private final File fontFile = new File("src/main/resources/BebasNeue-Regular.ttf");
    private final Font titleFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

    private final List<ImageButton> currentButtons = new ArrayList<>();

    public TowerDefenseView(TowerDefenseModel model) throws IOException, FontFormatException {
        this.model = model;

        // Set up the window
        this.setFocusable(true);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));

        // Load images
        buttonImage = loadImageFromResources("button.png");
        mainMenuBgImage = loadImageFromResources("main-menu-bg.png");
        pausedBgImage = loadImageFromResources("paused-bg.png");
        gameplayBgWithPathImage = loadImageFromResources("gameplay-bg-withpath.png");
        sideBarBgImage = loadImageFromResources("sidebar-bg-image.png");
        bottomBarBgImage = loadImageFromResources("tilable-bg.png");

        // Create buttons
        startButton = new ImageButton(buttonImage, "Start");
        restartButton = new ImageButton(buttonImage, "Restart");
        pauseButton = new ImageButton(buttonImage, "Pause");
        resumeButton = new ImageButton(buttonImage, "Resume");
        mainMenuButton = new ImageButton(buttonImage, "Main Menu");
        exitButton = new ImageButton(buttonImage, "Exit");
        

        // Update button layout based on the initial game state
        updateButtonLayout(model.getGameState());
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

    public void updateButtonLayout(GameState gameState) throws IOException, FontFormatException {

        List<ImageButton> buttons = new ArrayList<>();

        // Remove current buttons from the panel
        for (ImageButton button : currentButtons) {
            remove(button);
        }
        currentButtons.clear();

        // Add button listeners
        startButton.addActionListener(e -> model.startGame());

        restartButton.addActionListener(e -> model.restartGame());

        pauseButton.addActionListener(e -> model.pauseGame());

        resumeButton.addActionListener(e -> model.resumeGame());

        mainMenuButton.addActionListener(e -> model.mainMenu());

        exitButton.addActionListener(e -> System.exit(0));

        // Set visibility of buttons initially
        startButton.setVisible(false);
        restartButton.setVisible(false);
        pauseButton.setVisible(false);
        resumeButton.setVisible(false);
        mainMenuButton.setVisible(false);
        exitButton.setVisible(false);


        // Depending on the game state, create the relevant buttons
        switch (gameState) {
            case MAIN_MENU -> {
                buttons.add(startButton);
                buttons.add(exitButton);
            }
            case PAUSE_SCREEN -> {
                buttons.add(resumeButton);
                buttons.add(restartButton);
                buttons.add(mainMenuButton);
            }
            case GAME_OVER_SCREEN, VICTORY_SCREEN -> {
                buttons.add(restartButton);
                buttons.add(mainMenuButton);
            }
            case IN_GAME -> buttons.add(pauseButton);
        }

        int titleAreaHeight = getHeight() / 4;
        int totalButtonHeight = buttons.size() * buttonHeight;
        int remainingSpace = getHeight() - totalButtonHeight - titleAreaHeight;
        int spacing = remainingSpace / (buttons.size() + 1);

        int yPos;
        if (buttons.size() == 1) {
            // If there's only one button, position it towards the bottom fifth of the screen
            yPos = getHeight() - getHeight() / 10 - buttonHeight/2;
        } else {
            // Position the buttons vertically, centered and evenly spaced
            yPos = titleAreaHeight + spacing;
        }

        for (ImageButton button : buttons) {
            button.setBounds((getWidth() - buttonWidth) / 2, yPos, buttonWidth, buttonHeight);
            yPos += buttonHeight + spacing;
            add(button);
            button.setVisible(true);
        }

        // Add the new buttons to the list of current buttons
        currentButtons.addAll(buttons);

        // Repaint the container to show the new layout
        revalidate();
        repaint();

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

        // Update button layout based on the game state
        try {
            updateButtonLayout(model.getGameState());
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

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
        graphics2D.setFont(new Font("Arial", Font.BOLD, 40));
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
        CellPositionToPixelConverter converter =
                new CellPositionToPixelConverter(
                        gameRectangle,
                        model.getGridDimension(),
                        0);

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
            drawImageRectangle(graphics2D, bottomBarCellRectangle, bottomBarBgImage);
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
     * @param rectangle the rectangle to draw the image in
     * @param image the image to draw
     */
    private void drawImageRectangle(Graphics2D graphics2D, Rectangle2D rectangle, Image image) {
        graphics2D.drawImage(image, (int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight(), null);
    }
}
