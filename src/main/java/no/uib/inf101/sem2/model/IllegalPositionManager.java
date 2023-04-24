package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.grid.CellPosition;

import java.util.HashSet;
import java.util.Set;

public class IllegalPositionManager {
    private final Set<CellPosition> illegalPositions;

    public IllegalPositionManager() {
        this.illegalPositions = new HashSet<>();
        initializeIllegalPositions();
    }

    /**
     * Adds an illegal position to the manager.
     *
     * @param position the position to mark as illegal
     */
    public void addIllegalPosition(CellPosition position) {
        illegalPositions.add(position);
    }

    private void initializeIllegalPositions(){
        int[][] illegalGrid = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0},
                {1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0}
        };

        for (int row = 0; row < illegalGrid.length; row++) {
            for (int col = 0; col < illegalGrid[row].length; col++) {
                if (illegalGrid[row][col] == 1) {
                    illegalPositions.add(new CellPosition(row, col));
                }
            }
        }
    }

    /**
     * Checks if the given position is legal.
     *
     * @param position the position to check
     * @return true if the position is legal, false otherwise
     */
    public boolean isPositionLegal(CellPosition position) {
        System.out.println("Position: " + position);
        boolean isNotIllegal = !illegalPositions.contains(position);
        boolean isWithinBounds = position.row() >= 0 && position.row() < 16 && position.col() >= 0 && position.col() < 12;
        System.out.println("isNotIllegal: " + isNotIllegal);
        System.out.println("isWithinBounds: " + isWithinBounds);
        return isNotIllegal && isWithinBounds;
    }

    public void reset() {
        illegalPositions.clear();
    }
}
