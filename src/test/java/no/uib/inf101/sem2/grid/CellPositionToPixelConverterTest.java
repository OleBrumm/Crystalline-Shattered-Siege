package no.uib.inf101.sem2.grid;

import no.uib.inf101.sem2.grid.converter.CellPositionToPixelConverter;
import no.uib.inf101.sem2.model.TowerDefenseField;
import no.uib.inf101.sem2.screen.ScreenPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CellPositionToPixelConverterTest {

    private CellPositionToPixelConverter converter;
    private Rectangle2D canvasRectangle;
    private GridDimension gridDimension;

    @BeforeEach
    void setUp() {
        canvasRectangle = new Rectangle2D.Double(0, 0, 500, 500);
        gridDimension = new TowerDefenseField(10, 10);
        converter = new CellPositionToPixelConverter(canvasRectangle, gridDimension, 0);
    }

    @Test
    void testGetBoundsForCell() {
        CellPosition cellPosition = new CellPosition(0, 0);
        Rectangle2D bounds = converter.getBoundsForCell(cellPosition);

        assertEquals(0, bounds.getX(), 0.1);
        assertEquals(0, bounds.getY(), 0.1);
        assertEquals(50, bounds.getWidth(), 0.1);
        assertEquals(50, bounds.getHeight(), 0.1);
    }

    @Test
    void testGetCenterForCell() {
        CellPosition cellPosition = new CellPosition(0, 0);
        ScreenPosition center = converter.getCenterForCell(cellPosition);

        assertEquals(25, center.x(), 0.01);
        assertEquals(25, center.y(), 0.01);
    }

    @Test
    void testGetCellPositionFromScreenPosition() {
        ScreenPosition screenPosition = new ScreenPosition(25.5, 25.5);
        CellPosition cellPosition = converter.getCellPositionFromScreenPosition(screenPosition);

        assertEquals(0, cellPosition.row());
        assertEquals(0, cellPosition.col());
    }
}
