package no.uib.inf101.sem2.entity.tower.towerTypes;

import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.grid.CellPositionToPixelConverter;

public class TowerTree extends Tower {
    public TowerTree(int x, int y, CellPositionToPixelConverter converter) {
        super(x, y, 100, 100, 850, 250, 300, "TREE", converter);
    }
}
