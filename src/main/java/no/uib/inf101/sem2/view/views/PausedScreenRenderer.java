package no.uib.inf101.sem2.view.views;

import no.uib.inf101.sem2.util.RenderingUtils;
import no.uib.inf101.sem2.view.resources.ImageResources;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static no.uib.inf101.sem2.view.Inf101Graphics.drawCenteredString;

public class PausedScreenRenderer {
    ImageResources imageResources;
    Font titleFont;
    int screenWidth, screenHeight;

    public PausedScreenRenderer(ImageResources imageResources, Font titleFont, int screenWidth, int screenHeight) {
        this.imageResources = imageResources;
        this.titleFont = titleFont;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * Draws the pause menu
     *
     * @param graphics2D the graphics object to draw on
     */
    public void drawPauseScreen(Graphics2D graphics2D) {
        // Draw background
        RenderingUtils.drawImageRectangle(graphics2D, new Rectangle2D.Double(0, 0, screenWidth, screenHeight + 100), imageResources.getImage("PausedBG"));

        // Draw title
        graphics2D.setFont(titleFont.deriveFont(100f));
        graphics2D.setColor(Color.WHITE);
        drawCenteredString(graphics2D,
                "Paused",
                new Rectangle2D.Double(0, 0, screenWidth, (double) screenHeight / 4) {
                });
    }
}
