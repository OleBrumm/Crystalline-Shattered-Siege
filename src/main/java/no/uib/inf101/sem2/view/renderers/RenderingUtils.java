package no.uib.inf101.sem2.view.renderers;

import no.uib.inf101.sem2.grid.CellPositionToPixelConverter;
import no.uib.inf101.sem2.grid.GridCell;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class RenderingUtils {

    // Utility method to draw an image rectangle
    public static void drawImageRectangle(Graphics2D graphics2D, Rectangle2D rectangle, Image image) {
        graphics2D.drawImage(image, (int) rectangle.getX(), (int) rectangle.getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight(), null);
    }

    public static void drawImageRectangleWithBackground(Graphics2D graphics2D,
                                                        Rectangle2D rectangle,
                                                        Image image,
                                                        Color backgroundColor) {
        graphics2D.setColor(backgroundColor);
        graphics2D.fill(rectangle);
        drawImageRectangle(graphics2D, rectangle, image);
    }

    // Utility method to cells with images instead of colors
    public static void drawImageCells(Graphics2D graphics2D,
                                       Iterable<GridCell<Character>> cells,
                                       CellPositionToPixelConverter converter,
                                       List<Image> towerImages) {
        int i = 0;
        for (GridCell<Character> cell : cells) {
            drawImageCell(graphics2D, cell, converter, towerImages.get(i));
            i++;
        }
    }
    private static void drawImageCell(Graphics2D graphics2D,
                                      GridCell<Character> cell,
                                      CellPositionToPixelConverter converter,
                                      Image towerImage) {
        Rectangle2D cellRectangle = converter.getBoundsForCell(cell.pos());
        drawImageRectangle(graphics2D, cellRectangle, towerImage);
    }

    // Utility method to draw cells with colors
    public static void drawCells(Graphics2D graphics2D,
                                 Iterable<GridCell<Character>> cells,
                                 CellPositionToPixelConverter converter,
                                 Color backgroundColor) {
        for (GridCell<Character> cell : cells) {
            drawCell(graphics2D, cell, converter, backgroundColor);
        }
    }
    private static void drawCell(Graphics2D graphics2D,
                                 GridCell<Character> cell,
                                 CellPositionToPixelConverter converter,
                                 Color backgroundColor) {
        Rectangle2D cellRectangle = converter.getBoundsForCell(cell.pos());
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(cellRectangle);
        graphics2D.setColor(backgroundColor);
        graphics2D.fill(cellRectangle);
    }
}
