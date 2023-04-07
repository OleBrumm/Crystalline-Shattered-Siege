package no.uib.inf101.sem2.view.views;

import no.uib.inf101.sem2.view.Inf101Graphics;

import java.awt.*;

public class VictoryScreenRenderer {

    Font titleFont;
    int screenWidth, screenHeight;

    public VictoryScreenRenderer(Font titleFont, int screenWidth, int screenHeight) {
        this.titleFont = titleFont;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * Draws the victory screen
     *
     * @param graphics2D Graphics2D object to draw on
     */
    public void drawVictoryScreen(Graphics2D graphics2D) {
        // Draw background
        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(0, 0, screenWidth, screenHeight);

        // Draw title
        graphics2D.setFont(titleFont.deriveFont(70f));
        graphics2D.setColor(Color.WHITE);
        Inf101Graphics.drawCenteredString(graphics2D,
                "You won!",
                new Rectangle(0, 0, screenWidth, screenHeight / 4) {
                });
    }

}
