package no.uib.inf101.sem2.view.renderers;

import no.uib.inf101.sem2.entity.projectile.projectiletypes.Explosion;
import no.uib.inf101.sem2.util.RenderingUtils;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;

public class ExplosionRenderer {
    private final Map<String, Image> explosionImages;

    public ExplosionRenderer(Map<String, Image> explosionImages) {
        this.explosionImages = explosionImages;
    }

    public void drawExplosions(Graphics2D graphics2D, List<Explosion> explosions) {
        for (Explosion explosion : explosions) {
            drawExplosion(graphics2D, explosion);
        }
    }

    private void drawExplosion(Graphics2D graphics2D, Explosion explosion) {
        Image explosionImage = getExplosionImage();
        Rectangle2D explosionRectangle = getExplosionBounds(explosion);
        RenderingUtils.drawImageRectangle(graphics2D, explosionRectangle, explosionImage);
    }

    private Rectangle2D getExplosionBounds(Explosion explosion) {
        double x = explosion.getX();
        double y = explosion.getY();
        double size = explosion.getSize();
        return new Rectangle2D.Double(x - size / 2, y - size / 2, size, size);
    }

    private Image getExplosionImage() {
        return explosionImages.get("Explosion");
    }
}
