package no.uib.inf101.sem2.entity.enemy;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.TowerDefenseField;
import no.uib.inf101.sem2.screen.ScreenPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WaypointsTest {
    private Waypoints waypoints;
    private GridDimension gridDimension;
    private Rectangle2D canvasRectangle;
    private double cellMargin;

    @BeforeEach
    public void setUp() {
        gridDimension = new TowerDefenseField(20, 20);
        canvasRectangle = new Rectangle2D.Double(0, 0, 800, 800);
        cellMargin = 2;
        waypoints = new Waypoints(canvasRectangle, gridDimension, cellMargin);
    }

    @Test
    public void testInitWaypoints() {
        List<ScreenPosition> waypointList = waypoints.getWaypoints();
        assertNotNull(waypointList);
        assertEquals(15, waypointList.size());
    }

    @Test
    public void testAddWaypoint() {
        CellPosition newWaypoint = new CellPosition(12, 12);
        waypoints.addWaypoint(newWaypoint);
        List<ScreenPosition> waypointList = waypoints.getWaypoints();
        assertEquals(16, waypointList.size());
        ScreenPosition addedWaypointScreenPosition = waypointList.get(waypointList.size() - 1);
        // Verify that the added waypoint's ScreenPosition is correct
        assertEquals(addedWaypointScreenPosition.x(), canvasRectangle.getWidth()/gridDimension.cols() * newWaypoint.col() + newWaypoint.col() * cellMargin - 2 * cellMargin, 0.5);
        assertEquals(addedWaypointScreenPosition.y(), canvasRectangle.getHeight()/gridDimension.rows() * newWaypoint.row() + newWaypoint.row() * cellMargin - 2 * cellMargin, 0.5);
    }
}
