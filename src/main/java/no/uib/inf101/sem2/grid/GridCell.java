package no.uib.inf101.sem2.grid;

/**
 * A cell in a grid.
 *
 * @param pos   The position of the cell
 * @param value The value of the cell
 */
public record GridCell<E>(CellPosition pos, E value) { }