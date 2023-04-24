package no.uib.inf101.sem2.entity.enemy;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.converter.CellPositionToPixelConverter;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.screen.ScreenPosition;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Waypoints {
    private final List<ScreenPosition> waypoints;
    private final CellPositionToPixelConverter converter;

    public Waypoints(Rectangle2D canvasRectangle, GridDimension gridDimension, double cellMargin) {
        this.waypoints = new ArrayList<>();
        this.converter = new CellPositionToPixelConverter(canvasRectangle, gridDimension, cellMargin);
        initWaypoints();
    }

    private void initWaypoints() {
        addWaypoint(new CellPosition(10, 0));
        addWaypoint(new CellPosition(10, 2));
        addWaypoint(new CellPosition(8, 2));
        addWaypoint(new CellPosition(8, 6));
        addWaypoint(new CellPosition(6, 6));
        addWaypoint(new CellPosition(6, 3));
        addWaypoint(new CellPosition(4, 3));
        addWaypoint(new CellPosition(4, 9));
        addWaypoint(new CellPosition(5, 9));
        addWaypoint(new CellPosition(5, 11));
        addWaypoint(new CellPosition(6, 11));
        addWaypoint(new CellPosition(6, 14));
        addWaypoint(new CellPosition(1, 14));
        addWaypoint(new CellPosition(1, 11));
        addWaypoint(new CellPosition(0, 11));
    }

    public void addWaypoint(CellPosition cellPosition) {
        waypoints.add(converter.getCenterForCell(cellPosition));
    }

    public List<ScreenPosition> getWaypoints() {
        return waypoints;
    }
}
