package no.uib.inf101.sem2.view.views;

import no.uib.inf101.sem2.grid.CellPositionToPixelConverter;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.view.backgrounds.BackgroundImages;
import no.uib.inf101.sem2.view.renderers.*;
import no.uib.inf101.sem2.view.resources.ImageResources;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class GameRenderer {

    private final TowerDefenseModel model;
    private final BackgroundImages backgroundImages;
    private final ImageResources imageResources;
    private final int screenWidth;
    private final int screenHeight;

    public GameRenderer(TowerDefenseModel model, BackgroundImages backgroundImages, ImageResources imageResources, int screenWidth, int screenHeight) {
        this.model = model;
        this.backgroundImages = backgroundImages;
        this.imageResources = imageResources;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * Draws the game
     *
     * @param graphics2D the graphics object to draw on
     */
    public void drawGame(Graphics2D graphics2D) {

        // Draw the background
        Rectangle2D gameRectangle =
                new Rectangle2D.Double(0,
                        0,
                        screenWidth,
                        8 * (double) screenHeight / 10);
        RenderingUtils.drawImageRectangle(graphics2D, gameRectangle, backgroundImages.gameplayBgWithPathImage);

        // Draw the grid lines on the game board
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(gameRectangle, model.getGridDimension(), 0);
        RenderingUtils.drawCells(graphics2D, model.getTilesOnBoard(), converter, new Color(0, 0, 0, 0));

        // Add the enemy images to a map
        Map<String, Image> enemyImages = new HashMap<>();
        enemyImages.put("EnemyRed", imageResources.getImage("EnemyRed"));
        enemyImages.put("EnemyBlue", imageResources.getImage("EnemyBlue"));
        enemyImages.put("EnemyYellow", imageResources.getImage("EnemyYellow"));

        // Draw the enemies
        EnemyRenderer enemyRenderer = new EnemyRenderer(enemyImages);
        enemyRenderer.drawEnemies(graphics2D, converter, model.getEnemies());

        // Add the tower images to a map
        Map<String, Image> towerImages = new HashMap<>();
        towerImages.put("TowerTree", imageResources.getImage("TowerTree"));
        towerImages.put("TowerIce", imageResources.getImage("TowerIce"));
        towerImages.put("TowerFire", imageResources.getImage("TowerFire"));

        // Draw the towers
        TowerRenderer towerRenderer = new TowerRenderer(towerImages);
        towerRenderer.drawTowers(graphics2D, converter, model.getTowers());

        // Add the projectile images to a map
        Map<String, Image> projectileImages = new HashMap<>();
        projectileImages.put("ProjectileTree", imageResources.getImage("ProjectileTree"));
        projectileImages.put("ProjectileIce", imageResources.getImage("ProjectileIce"));
        projectileImages.put("ProjectileFire", imageResources.getImage("ProjectileFire"));


        // Draw the projectiles
        ProjectileRenderer projectileRenderer = new ProjectileRenderer(projectileImages);
        projectileRenderer.drawProjectiles(graphics2D, model.getProjectiles());

        // Add the icon images to a map
        Map<String, Image> iconImages = new HashMap<>();
        iconImages.put("GoldIcon", imageResources.getImage("GoldIcon"));
        iconImages.put("HeartIcon", imageResources.getImage("HeartIcon"));
        iconImages.put("WaveIcon", imageResources.getImage("WaveIcon"));

        // Draw the game status bar
        GameStatusBarRenderer gameStatusBarRenderer = new GameStatusBarRenderer(screenWidth, screenHeight, backgroundImages.gameStatusBarBgImage, towerImages, iconImages, model);
        gameStatusBarRenderer.drawGameStatusBar(graphics2D);
    }


}
