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

    private Rectangle2D gameInfoRectangle;
    private Rectangle2D gameInfoGoldRectangle;
    private Rectangle2D gameInfoLivesRectangle;
    private Rectangle2D gameInfoWaveRectangle;
    private Rectangle2D gameInfoTextRectangle;
    private Rectangle2D gameInfoGoldTextRectangle;
    private Rectangle2D gameInfoLivesTextRectangle;
    private Rectangle2D gameInfoWaveTextRectangle;
    private Rectangle2D shopRectangle;
    private Rectangle2D shopImagesRectangle;
    private Rectangle2D shopTextRectangle;


    public GameStatusBarRenderer(int windowWidth, int windowHeight, Image gameStatusBarBgImage, Map<String, Image> towerImages, Map<String, Image> iconImages, TowerDefenseModel model) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.gameStatusBarBgImage = gameStatusBarBgImage;
        this.towerImages = towerImages;
        this.iconImages = iconImages;
        this.model = model;

        initRectangles();
    }

    /**
     * Initializes the rectangles used to draw the game status bar
     * and the shop
     */
    private void initRectangles(){
        gameInfoRectangle =
                new Rectangle2D.Double((double) windowWidth / 4,
                        8 * (double) windowHeight / 10,
                        (double) windowWidth / 4,
                        2 * (double) windowHeight / 10);

        gameInfoGoldRectangle =
                new Rectangle2D.Double(gameInfoRectangle.getX(),
                        gameInfoRectangle.getY(),
                        gameInfoRectangle.getWidth() / 4,
                        gameInfoRectangle.getHeight()/3);

        gameInfoLivesRectangle =
                new Rectangle2D.Double(gameInfoRectangle.getX(),
                        gameInfoRectangle.getY() + gameInfoRectangle.getHeight() / 3,
                        gameInfoRectangle.getWidth() / 4,
                        gameInfoRectangle.getHeight()/3);

        gameInfoWaveRectangle =
                new Rectangle2D.Double(gameInfoRectangle.getX(),
                        gameInfoRectangle.getY() + 2 * gameInfoRectangle.getHeight() / 3,
                        gameInfoRectangle.getWidth() / 4,
                        gameInfoRectangle.getHeight()/3);

        gameInfoTextRectangle =
                new Rectangle2D.Double(gameInfoRectangle.getX() + gameInfoRectangle.getWidth() / 4,
                        gameInfoRectangle.getY(),
                        3 * gameInfoRectangle.getWidth() / 4,
                        gameInfoRectangle.getHeight());

        gameInfoGoldTextRectangle =
                new Rectangle2D.Double(gameInfoTextRectangle.getX(),
                        gameInfoTextRectangle.getY(),
                        gameInfoTextRectangle.getWidth(),
                        gameInfoTextRectangle.getHeight()/3);

        gameInfoLivesTextRectangle =
                new Rectangle2D.Double(gameInfoTextRectangle.getX(),
                        gameInfoTextRectangle.getY() + gameInfoTextRectangle.getHeight() / 3,
                        gameInfoTextRectangle.getWidth(),
                        gameInfoTextRectangle.getHeight()/3);

        gameInfoWaveTextRectangle =
                new Rectangle2D.Double(gameInfoTextRectangle.getX(),
                        gameInfoTextRectangle.getY() + 2 * gameInfoTextRectangle.getHeight() / 3,
                        gameInfoTextRectangle.getWidth(),
                        gameInfoTextRectangle.getHeight()/3);

        shopRectangle =
                new Rectangle2D.Double((double) windowWidth / 2,
                        8 * (double) windowHeight / 10,
                        (double) windowWidth / 2,
                        2 * (double) windowHeight / 10);

        shopImagesRectangle =
                new Rectangle2D.Double((double) windowWidth / 2,
                        16 * (double) windowHeight / 20,
                        (double) windowWidth / 2,
                        3 * (double) windowHeight / 20);

        shopTextRectangle =
                new Rectangle2D.Double((double) windowWidth / 2,
                        19 * (double) windowHeight / 20,
                        (double) windowWidth / 2,
                        (double) windowHeight / 20);
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

        RenderingUtils.drawImageRectangle(graphics2D, gameInfoGoldRectangle, iconImages.get("GoldIcon"));
        RenderingUtils.drawImageRectangle(graphics2D, gameInfoLivesRectangle, iconImages.get("HeartIcon"));
        RenderingUtils.drawImageRectangle(graphics2D, gameInfoWaveRectangle, iconImages.get("WaveIcon"));

        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("Helvetica", Font.BOLD, 30));
        Inf101Graphics.drawCenteredString(graphics2D, "Gold: " + model.getGold(), gameInfoGoldTextRectangle);
        Inf101Graphics.drawCenteredString(graphics2D, "Lives: " + model.getLives(), gameInfoLivesTextRectangle);
        Inf101Graphics.drawCenteredString(graphics2D, "Wave: " + model.getWave(), gameInfoWaveTextRectangle);

        // Draw the shop cells
        TowerDefenseField shopField = new TowerDefenseField(1, 3);
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(shopImagesRectangle, shopField, 0);

        RenderingUtils.drawImageRectangle(graphics2D, shopRectangle, gameStatusBarBgImage);
        RenderingUtils.drawCells(graphics2D, shopField, converter, new Color(0x6A0DAD));

        // Draw the shop towers
        List<Image> towerImages = new ArrayList<>();
        towerImages.add(this.towerImages.get("TowerFire"));
        towerImages.add(this.towerImages.get("TowerTree"));
        towerImages.add(this.towerImages.get("TowerIce"));

        // Get the tower prices
        List<String> towerPrices = new ArrayList<>();
        towerPrices.add("1200");
        towerPrices.add("250");
        towerPrices.add("425");
        String shopText = towerPrices.get(0) + "        " + towerPrices.get(1) + "          " + towerPrices.get(2);


        RenderingUtils.drawImageCells(graphics2D, shopField, converter, towerImages);

        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(new Font("Helvetica", Font.BOLD, 30));
        Inf101Graphics.drawCenteredString(graphics2D, shopText, shopTextRectangle);

    }
}
