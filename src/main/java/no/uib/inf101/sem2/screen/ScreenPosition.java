package no.uib.inf101.sem2.screen;

import java.awt.geom.Rectangle2D;

/**
 * A position in a grid.
 *
 * @param x The x-position of an object
 * @param y The y-position of an object
 */
public record ScreenPosition(double x, double y) {

    public Rectangle2D getBounds(int size) {
        return new Rectangle2D.Double(x, y, size, size);
    }

}
