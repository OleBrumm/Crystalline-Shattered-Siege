package no.uib.inf101.sem2.grid;

import no.uib.inf101.sem2.screen.ScreenPosition;

import java.awt.geom.Rectangle2D;

public class ScreenPositionToBoundsConverter {

    public Rectangle2D getBoundsForObject(ScreenPosition screenPosition, double width, double height) {
        return new Rectangle2D.Double(screenPosition.x() - width / 2, screenPosition.y() - height / 2, width, height);
    }

    public Rectangle2D getBoundsForObject(ScreenPosition screenPosition, double size) {
        return getBoundsForObject(screenPosition, size, size);
    }

}
