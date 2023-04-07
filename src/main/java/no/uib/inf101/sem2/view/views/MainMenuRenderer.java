package no.uib.inf101.sem2.view.views;

import no.uib.inf101.sem2.view.Inf101Graphics;
import no.uib.inf101.sem2.view.backgrounds.BackgroundImages;
import no.uib.inf101.sem2.view.renderers.RenderingUtils;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MainMenuRenderer {
    private final BackgroundImages backgroundImages;
    private final Font titleFont;
    private final double screenWidth;
    private final double screenHeight;

    public MainMenuRenderer(BackgroundImages backgroundImages, Font titleFont, double screenWidth, double screenHeight) {
        this.backgroundImages = backgroundImages;
        this.titleFont = titleFont;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void drawMainMenu(Graphics2D graphics2D) {
        // Draw background
        RenderingUtils.drawImageRectangle(graphics2D, new Rectangle2D.Double(0, 0, screenWidth, screenHeight + 100), backgroundImages.mainMenuBgImage);

        // Draw title
        graphics2D.setFont(titleFont.deriveFont(75f));
        graphics2D.setColor(Color.WHITE);
        Inf101Graphics.drawCenteredString(graphics2D,
                "Crystalline: Shattered Siege",
                new Rectangle2D.Double(0, 0, screenWidth, screenHeight / 4) {
                });
    }
}
