package no.uib.inf101.sem2.view.renderers;

import no.uib.inf101.sem2.grid.CellPositionToPixelConverter;
import no.uib.inf101.sem2.model.TowerDefenseField;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.view.Inf101Graphics;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


public class GameStatusBarRenderer {

    private final int windowWidth;
    private final int windowHeight;
    private final Image gameStatusBarBgImage;
    private final Map<String, Image> towerImages;
    private final Map<String, Image> iconImages;
    private final TowerDefenseModel model;

    public GameStatusBarRenderer(int windowWidth, int windowHeight, Image gameStatusBarBgImage, Map<String, Image> towerImages, Map<String, Image> iconImages, TowerDefenseModel model) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.gameStatusBarBgImage = gameStatusBarBgImage;
        this.towerImages = towerImages;
        this.iconImages = iconImages;
        this.model = model;
    }

    /**
     * Draws the bottom bar
     *
     * @param graphics2D the graphics object to draw on
     */
    public void drawGameStatusBar(Graphics2D graphics2D) {

        // Draw the bottom bar
        for (int i = 0; i < 4; i++) {
            Rectangle2D bottomBarCellRectangle =
                    new Rectangle2D.Double(i * (double) windowWidth / 4,
                            8 * (double) windowHeight / 10,
                            (double) windowWidth / 4,
                            2 * Math.ceil((double) windowHeight / 10));
            RenderingUtils.drawImageRectangle(graphics2D, bottomBarCellRectangle, gameStatusBarBgImage);
        }

        // Draw game information
        Rectangle2D gameInfoRectangle =
                new Rectangle2D.Double((double) windowWidth / 4,
                        8 * (double) windowHeight / 10,
                        (double) windowWidth / 4,
                        2 * (double) windowHeight / 10);

        Rectangle2D gameInfoGoldRectangle =
                new Rectangle2D.Double(gameInfoRectangle.getX(),
                        gameInfoRectangle.getY(),
                        gameInfoRectangle.getWidth() / 4,
                        gameInfoRectangle.getHeight()/3);

        Rectangle2D gameInfoLivesRectangle =
                new Rectangle2D.Double(gameInfoRectangle.getX(),
                        gameInfoRectangle.getY() + gameInfoRectangle.getHeight() / 3,
                        gameInfoRectangle.getWidth() / 4,
                        gameInfoRectangle.getHeight()/3);

        Rectangle2D gameInfoWaveRectangle =
                new Rectangle2D.Double(gameInfoRectangle.getX(),
                        gameInfoRectangle.getY() + 2 * gameInfoRectangle.getHeight() / 3,
                        gameInfoRectangle.getWidth() / 4,
                        gameInfoRectangle.getHeight()/3);

        RenderingUtils.drawImageRectangle(graphics2D, gameInfoGoldRectangle, iconImages.get("GoldIcon"));
        RenderingUtils.drawImageRectangle(graphics2D, gameInfoLivesRectangle, iconImages.get("HeartIcon"));
        RenderingUtils.drawImageRectangle(graphics2D, gameInfoWaveRectangle, iconImages.get("WaveIcon"));

        // Draw the game information text
        Rectangle2D gameInfoTextRectangle =
                new Rectangle2D.Double(gameInfoRectangle.getX() + gameInfoRectangle.getWidth() / 4,
                        gameInfoRectangle.getY(),
                        3 * gameInfoRectangle.getWidth() / 4,
                        gameInfoRectangle.getHeight());

        Rectangle2D gameInfoGoldTextRectangle =
                new Rectangle2D.Double(gameInfoTextRectangle.getX(),
                        gameInfoTextRectangle.getY(),
                        gameInfoTextRectangle.getWidth(),
                        gameInfoTextRectangle.getHeight()/3);

        Rectangle2D gameInfoLivesTextRectangle =
                new Rectangle2D.Double(gameInfoTextRectangle.getX(),
                        gameInfoTextRectangle.getY() + gameInfoTextRectangle.getHeight() / 3,
                        gameInfoTextRectangle.getWidth(),
                        gameInfoTextRectangle.getHeight()/3);

        Rectangle2D gameInfoWaveTextRectangle =
                new Rectangle2D.Double(gameInfoTextRectangle.getX(),
                        gameInfoTextRectangle.getY() + 2 * gameInfoTextRectangle.getHeight() / 3,
                        gameInfoTextRectangle.getWidth(),
                        gameInfoTextRectangle.getHeight()/3);

        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("Helvetica", Font.BOLD, 30));
        Inf101Graphics.drawCenteredString(graphics2D, "Gold: " + model.getGold(), gameInfoGoldTextRectangle);
        Inf101Graphics.drawCenteredString(graphics2D, "Lives: " + model.getLives(), gameInfoLivesTextRectangle);
        Inf101Graphics.drawCenteredString(graphics2D, "Wave: " + model.getWave(), gameInfoWaveTextRectangle);


        // Draw the shop
        Rectangle2D shopRectangle =
                new Rectangle2D.Double((double) windowWidth / 2,
                        8 * (double) windowHeight / 10,
                        (double) windowWidth / 2,
                        2 * (double) windowHeight / 10);

        // Draw the shop cells
        TowerDefenseField shopField = new TowerDefenseField(1, 3);
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(shopRectangle, shopField, (double) windowWidth / 40);

        RenderingUtils.drawImageRectangle(graphics2D, shopRectangle, gameStatusBarBgImage);
        RenderingUtils.drawCells(graphics2D, shopField, converter, new Color(0x6A0DAD));

        // Draw the shop towers
        List<Image> towerImages = new ArrayList<>();
        towerImages.add(this.towerImages.get("TowerFire"));
        towerImages.add(this.towerImages.get("TowerTree"));
        towerImages.add(this.towerImages.get("TowerIce"));

        RenderingUtils.drawImageCells(graphics2D, shopField, converter, towerImages);

    }

}
