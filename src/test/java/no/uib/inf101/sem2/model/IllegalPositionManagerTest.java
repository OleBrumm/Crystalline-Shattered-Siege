package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IllegalPositionManagerTest {
    private IllegalPositionManager manager;

    @BeforeEach
    public void setUp() {
        manager = new IllegalPositionManager();
    }

    @Test
    public void testIsPositionLegal() {
        CellPosition legalPosition = new CellPosition(0, 12);
        CellPosition illegalPosition = new CellPosition(0, 1);
        CellPosition outOfBoundsPosition = new CellPosition(16, 12);

        assertTrue(manager.isPositionLegal(legalPosition));
        assertFalse(manager.isPositionLegal(illegalPosition));
        assertFalse(manager.isPositionLegal(outOfBoundsPosition));
    }

    @Test
    public void testAddIllegalPosition() {
        CellPosition newIllegalPosition = new CellPosition(0, 12);
        manager.addIllegalPosition(newIllegalPosition);
        assertFalse(manager.isPositionLegal(newIllegalPosition));
    }

    @Test
    public void testReset() {
        CellPosition illegalPosition = new CellPosition(0, 1);
        manager.reset();
        assertTrue(manager.isPositionLegal(illegalPosition));
    }
}
