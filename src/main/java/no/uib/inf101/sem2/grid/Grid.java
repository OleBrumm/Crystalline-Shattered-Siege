package no.uib.inf101.sem2.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E> {

    // The grid
    private final E[][] grid;
    // The number of rows and columns
    private final int cols;
    private final int rows;

    /**
     * Create a new grid
     *
     * @param rows         The number of rows
     * @param cols         The number of columns
     * @param defaultValue The default value for each cell
     */
    public Grid(int rows, int cols, E defaultValue) {
        // Check that rows and columns are non-negative
        if (rows < 0 || cols < 0) {
            throw new IllegalArgumentException("Rows and columns must be non-negative");
        }
        // Create the grid
        this.rows = rows;
        this.cols = cols;
        this.grid = (E[][]) new Object[rows][cols];
        for (E[] row : grid) {
            Arrays.fill(row, defaultValue);
        }
    }

    @Override
    public void set(CellPosition pos, E value) {
        if (!positionIsOnGrid(pos)) {
            throw new IllegalArgumentException("Position " + pos + " is not on the grid");
        }
        grid[pos.col()][pos.row()] = value;
    }

    @Override
    public E get(CellPosition pos) {
        return grid[pos.col()][pos.row()];
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        return pos.col() >= 0 && pos.col() < rows && pos.row() >= 0 && pos.row() < cols;
    }

    @Override
    public Iterator iterator() {
        List<GridCell<E>> list = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                list.add(new GridCell<>(new CellPosition(i, j), grid[i][j]));
            }
        }
        return list.iterator();
    }

    @Override
    public int rows() {
        return rows;
    }

    @Override
    public int cols() {
        return cols;
    }

}
