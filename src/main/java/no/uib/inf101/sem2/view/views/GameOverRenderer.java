package no.uib.inf101.sem2.view.views;

import java.awt.*;

public class GameOverRenderer {

    private final Font titleFont;
    private final int screenWidth;
    private final int screenHeight;

    public GameOverRenderer(Font titleFont, int screenWidth, int screenHeight) {
        this.titleFont = titleFont;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * Draws the game over screen
     *
     * @param graphics2D the graphics object to draw on
     */
    public void drawGameOver(Graphics2D graphics2D) {
        // Draw background
        graphics2D.setColor(Color.RED);
        graphics2D.fillRect(0, 0, screenWidth, screenHeight);

        // Draw title
        graphics2D.setFont(titleFont.deriveFont(75f));
        graphics2D.setColor(Color.RED);
        graphics2D.drawString("Game Over", 300, 100);
    }

}
