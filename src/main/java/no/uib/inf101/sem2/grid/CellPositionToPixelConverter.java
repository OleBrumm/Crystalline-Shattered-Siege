package no.uib.inf101.sem2.grid;

import java.awt.geom.Rectangle2D;

public class CellPositionToPixelConverter {
    // The rectangle to draw the grid in
    private final Rectangle2D canvasRectangle;
    // The dimension of the grid
    private final GridDimension gridDimension;
    // The margin between cells
    private final double cellMargin;

    /**
     * Create a new converter
     *
     * @param canvasRectangle The rectangle to draw the grid in
     * @param gridDimension   The dimension of the grid
     * @param cellMargin      The margin between cells
     */
    public CellPositionToPixelConverter(Rectangle2D canvasRectangle, GridDimension gridDimension, double cellMargin) {
        this.canvasRectangle = canvasRectangle;
        this.gridDimension = gridDimension;
        this.cellMargin = cellMargin;
    }

    /**
     * Get the bounds of a cell in pixel coordinates
     *
     * @param cellPosition The cell to get the bounds for
     * @return The bounds of the cell
     */
    public Rectangle2D getBoundsForCell(CellPosition cellPosition){
        double cellWidth = (canvasRectangle.getWidth() - (gridDimension.cols()+1)* cellMargin) / gridDimension.cols();
        double cellHeight = (canvasRectangle.getHeight() - (gridDimension.rows()+1)* cellMargin) / gridDimension.rows();
        double cellX = canvasRectangle.getX() + cellMargin + cellPosition.col() * (cellWidth + cellMargin);
        double cellY = canvasRectangle.getY() + cellMargin + cellPosition.row() * (cellHeight + cellMargin);

        return new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);
    }
}
