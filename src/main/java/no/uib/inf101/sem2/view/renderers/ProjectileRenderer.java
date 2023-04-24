package no.uib.inf101.sem2.view.renderers;

import no.uib.inf101.sem2.entity.projectile.Projectile;
import no.uib.inf101.sem2.entity.projectile.projectiletypes.ProjectileApple;
import no.uib.inf101.sem2.entity.projectile.projectiletypes.ProjectileFire;
import no.uib.inf101.sem2.entity.projectile.projectiletypes.ProjectileIce;
import no.uib.inf101.sem2.screen.ScreenPosition;
import no.uib.inf101.sem2.screen.ScreenPositionProjectileBounds;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public class ProjectileRenderer {

    private final Map<String, Image> projectileImages;

    public ProjectileRenderer(Map<String, Image> projectileImages) {
        this.projectileImages = projectileImages;
    }

    public void drawProjectiles(Graphics2D graphics2D, Iterable<Projectile> projectiles) {
        for (Projectile projectile : projectiles) {
            drawProjectile(graphics2D, projectile);
        }
    }

    private void drawProjectile(Graphics2D graphics2D, Projectile projectile) {
        int projectileSize = projectile.getSize();
        Rectangle2D projectileRectangle = new ScreenPositionProjectileBounds().getBounds(new ScreenPosition(projectile.getX(), projectile.getY()), projectileSize);
        Image projectileImage = getProjectileImage(projectile);

        // Save the current transform
        AffineTransform oldTransform = graphics2D.getTransform();

        // Move the Graphics2D object's origin to the center of the projectile
        double centerX = projectileRectangle.getCenterX();
        double centerY = projectileRectangle.getCenterY();
        graphics2D.translate(centerX, centerY);

        // Draw the projectile image centered around the new origin
        int halfWidth = projectileSize / 2;
        int halfHeight = projectileSize / 2;
        graphics2D.drawImage(projectileImage, -halfWidth, -halfHeight, projectileSize, projectileSize, null);

        // Restore the original transform
        graphics2D.setTransform(oldTransform);
    }


    /**
     * Returns the image for the given projectile
     *
     * @param projectile the projectile to get the image for
     * @return the image for the given projectile
     */
    private Image getProjectileImage(Projectile projectile) {
        if (projectile instanceof ProjectileApple) {
            return projectileImages.get("ProjectileTree");
        } else if (projectile instanceof ProjectileFire) {
            return projectileImages.get("ProjectileFire");
        } else if (projectile instanceof ProjectileIce) {
            return projectileImages.get("ProjectileIce");
        } else {
            return null;
        }
    }
}
