package no.uib.inf101.sem2.view.renderers;

import no.uib.inf101.sem2.entity.enemy.Enemy;
import no.uib.inf101.sem2.entity.enemy.enemyTypes.EnemyBlue;
import no.uib.inf101.sem2.entity.enemy.enemyTypes.EnemyRed;
import no.uib.inf101.sem2.entity.enemy.enemyTypes.EnemyYellow;
import no.uib.inf101.sem2.grid.ScreenPositionToBoundsConverter;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;

public class EnemyRenderer {
    private final Map<String, Image> enemyImages;

    public EnemyRenderer(Map<String, Image> enemyImages) {
        this.enemyImages = enemyImages;
    }

    public void drawEnemies(Graphics2D graphics2D, ScreenPositionToBoundsConverter converter, List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            drawEnemy(graphics2D, enemy, converter);
        }
    }

    private void drawEnemy(Graphics2D graphics2D, Enemy enemy, ScreenPositionToBoundsConverter converter) {
        Rectangle2D enemyRectangle = converter.getBoundsForObject(enemy.getPosition(), enemy.getSize());
        Image enemyImage = getEnemyImage(enemy);
        RenderingUtils.drawImageRectangle(graphics2D, enemyRectangle, enemyImage);
    }

    /**
     * Returns the image for the given enemy
     *
     * @param enemy the enemy to get the image for
     * @return the image for the given enemy
     */
    private Image getEnemyImage(Enemy enemy) {
        if (enemy instanceof EnemyRed) {
            return enemyImages.get("EnemyRed");
        } else if (enemy instanceof EnemyBlue) {
            return enemyImages.get("EnemyBlue");
        } else if (enemy instanceof EnemyYellow) {
            return enemyImages.get("EnemyYellow");
        } else {
            return null;
        }
    }
}
