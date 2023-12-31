package no.uib.inf101.sem2.screen;

import java.awt.geom.Rectangle2D;

public class ScreenPositionProjectileBounds {

    public Rectangle2D getBounds(ScreenPosition screenPosition, int size) {
        return new Rectangle2D.Double(screenPosition.x() - (double) size/2, screenPosition.y() - (double) size/2, size, size);
    }

}
