package no.uib.inf101.sem2.view.views;

import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.CellPositionToPixelConverter;
import no.uib.inf101.sem2.grid.ScreenPositionToBoundsConverter;
import no.uib.inf101.sem2.model.TowerDefenseModel;
import no.uib.inf101.sem2.screen.ScreenPosition;
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

    private final Map<String, Rectangle2D> shopButtonBounds;

    // Variables to support dragging of towers
    private Tower draggedTower = null;
    private Point draggedTowerPosition = null;
    // Converter to convert screen positions to bounds of the game board
    ScreenPositionToBoundsConverter screenPositionConverter;

    // Draw the background
    Rectangle2D gameRectangle;

    // Draw the grid lines on the game board
    CellPositionToPixelConverter cellPositionConverter;

    public GameRenderer(TowerDefenseModel model, BackgroundImages backgroundImages, ImageResources imageResources, int screenWidth, int screenHeight) {
        this.model = model;
        this.backgroundImages = backgroundImages;
        this.imageResources = imageResources;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.screenPositionConverter = new ScreenPositionToBoundsConverter();
        this.gameRectangle = new Rectangle2D.Double(0,
                0,
                screenWidth,
                8 * (double) screenHeight / 10);
        this.cellPositionConverter = new CellPositionToPixelConverter(gameRectangle, model.getGridDimension(), 0);

        this.shopButtonBounds = new HashMap<>();

    }

    /**
     * Draws the game
     *
     * @param graphics2D the graphics object to draw on
     */
    public void drawGame(Graphics2D graphics2D) {


        RenderingUtils.drawImageRectangle(graphics2D, gameRectangle, backgroundImages.gameplayBgWithPathImage);

        // Add the enemy images to a map
        Map<String, Image> enemyImages = new HashMap<>();
        enemyImages.put("EnemyRed", imageResources.getImage("EnemyRed"));
        enemyImages.put("EnemyBlue", imageResources.getImage("EnemyBlue"));
        enemyImages.put("EnemyYellow", imageResources.getImage("EnemyYellow"));

        // Draw the enemies
        EnemyRenderer enemyRenderer = new EnemyRenderer(enemyImages);
        enemyRenderer.drawEnemies(graphics2D, screenPositionConverter, model.getEnemies());

        // Add the tower images to a map
        Map<String, Image> towerImages = new HashMap<>();
        towerImages.put("TowerTree", imageResources.getImage("TowerTree"));
        towerImages.put("TowerIce", imageResources.getImage("TowerIce"));
        towerImages.put("TowerFire", imageResources.getImage("TowerFire"));

        // Draw the towers
        TowerRenderer towerRenderer = new TowerRenderer(towerImages);
        towerRenderer.drawTowers(graphics2D, cellPositionConverter, model.getTowers());

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

        // Add the explosion image to a map
        Map<String, Image> explosionImages = new HashMap<>();
        explosionImages.put("Explosion", imageResources.getImage("Explosion"));

        // Draw the explosions
        ExplosionRenderer explosionRenderer = new ExplosionRenderer(explosionImages);
        explosionRenderer.drawExplosions(graphics2D, model.getExplosions());

        // Draw the dragged tower if there is one
        if (draggedTower != null && draggedTowerPosition != null) {
            drawDraggedTower(graphics2D, draggedTower, draggedTowerPosition);
        }

        int iteration = 0;
        // Draw the shop buttons and store their bounds in the map
        for (String towerType : towerImages.keySet()) {
            // Calculate the bounds of the shop button for the current tower type
            Rectangle2D shopButtonBound = new Rectangle2D.Double((double) screenWidth / 2 + iteration * (double) screenWidth / 6,
                    8 * (double) screenHeight / 10,
                    (double) screenWidth / 6,
                    2 * (double) screenHeight / 10);
            shopButtonBounds.put(towerType, shopButtonBound);
            iteration++;
        }
    }

    /**
     * Gets the tower type for a given point
     *
     * @param point the point to check
     * @return the tower type for the given point, or null if no tower type is found
     */
    public String getTowerTypeForPoint(Point point) {
        for (Map.Entry<String, Rectangle2D> entry : shopButtonBounds.entrySet()) {
            if (entry.getValue().contains(point)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /** Sets the dragged tower and its position
     *
     * @param draggedTower the dragged tower
     * @param draggedTowerPosition the position of the dragged tower
     */
    public void setDraggedTower(Tower draggedTower, Point draggedTowerPosition) {
        this.draggedTower = draggedTower;
        this.draggedTowerPosition = draggedTowerPosition;
    }

    /**
     * Gets the dragged tower
     *
     * @return the dragged tower
     */
    public Tower getDraggedTower() {
        return draggedTower;
    }

    /**
     * Draws the dragged tower
     *
     * @param graphics2D the graphics object to draw on
     * @param tower the dragged tower
     * @param position the position of the dragged tower
     */
    public void drawDraggedTower(Graphics2D graphics2D, Tower tower, Point position) {
        Image towerImage = getTowerImage(tower.getType());
        if (towerImage != null) {
            CellPosition closestCell = getClosestCell(position);
            double cellWidth = (double) screenWidth / model.getGridDimension().cols();
            double cellHeight = (double) screenHeight * 0.8 / model.getGridDimension().rows();
            int x = (int) (closestCell.col() * cellWidth);
            int y = (int) (closestCell.row() * cellHeight);
            if (isPointInGameRectangle(position)) {
                drawDraggedTowerRadius(graphics2D, closestCell, tower.getRange());
                if (model.isTowerPlacementValid(closestCell)) {
                    RenderingUtils.drawImageRectangle(graphics2D, new Rectangle2D.Double(x, y, cellWidth, cellHeight), towerImage);
                } else {
                    RenderingUtils.drawImageRectangleWithBackground(graphics2D, new Rectangle2D.Double(x, y, cellWidth, cellHeight), towerImage, new Color(255, 0, 0, 100));
                }
            }
        }
    }

    private void drawDraggedTowerRadius(Graphics2D graphics2D, CellPosition closestCell, int range) {
        ScreenPosition center = cellPositionConverter.getCenterForCell(closestCell);
        RenderingUtils.drawCircle(graphics2D, center, range, new Color(0, 0, 0, 100));
    }

    /**
     * Checks if a point is in the game rectangle
     *
     * @param point the point to check
     * @return true if the point is in the game rectangle, false otherwise
     */
    public boolean isPointInGameRectangle(Point point) {
        Rectangle2D gameRectangle = new Rectangle2D.Double(0, 0, screenWidth, 8 * (double) screenHeight / 10);
        return gameRectangle.contains(point);
    }

    /**
     * Gets the closest cell to a given point in the game rectangle
     *
     * @param position the point to check
     * @return the closest cell to the given point
     */
    public CellPosition getClosestCell(Point position) {
        double gameHeight = screenHeight * 0.8;
        int row = (int) (position.y / gameHeight * model.getGridDimension().rows());
        int col = (int) (position.x / (double) screenWidth * model.getGridDimension().cols());
        return new CellPosition(row, col);
    }


    /**
     * Gets the image for a given tower type
     *
     * @param type the tower type
     * @return the image for the given tower type
     */
    public Image getTowerImage(String type) {
        return switch (type) {
            case "TREE" -> imageResources.getImage("TowerTree");
            case "ICE" -> imageResources.getImage("TowerIce");
            case "FIRE" -> imageResources.getImage("TowerFire");
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    public CellPositionToPixelConverter getCellPositionConverter() {
        return cellPositionConverter;
    }

}
