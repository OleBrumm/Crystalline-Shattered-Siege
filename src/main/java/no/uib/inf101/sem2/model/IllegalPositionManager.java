package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;

import java.util.HashSet;
import java.util.Set;

public class IllegalPositionManager {
    private final Set<CellPosition> illegalPositions;

    public IllegalPositionManager() {
        this.illegalPositions = new HashSet<>();
    }

    /**
     * Adds an illegal position to the manager.
     *
     * @param position the position to mark as illegal
     */
    public void addIllegalPosition(CellPosition position) {
        illegalPositions.add(position);
    }

    /**
     * Checks if the given position is legal.
     *
     * @param position the position to check
     * @return true if the position is legal, false otherwise
     */
    public boolean isPositionLegal(CellPosition position) {
        return !illegalPositions.contains(position);
    }
}
