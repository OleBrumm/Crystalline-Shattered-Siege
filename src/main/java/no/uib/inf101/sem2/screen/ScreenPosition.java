package no.uib.inf101.sem2.screen;

/**
 * A position in a grid.
 *
 * @param x The x-position of an object
 * @param y The y-position of an object
 */
public record ScreenPosition(double x, double y) {
    public double distanceTo(ScreenPosition position) {
        return Math.sqrt(Math.pow(position.x - x, 2) + Math.pow(position.y - y, 2));
    }
}
