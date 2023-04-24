package no.uib.inf101.sem2.entity.tower.towertypes;

import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.grid.converter.CellPositionToPixelConverter;

public class TowerTree extends Tower {
    public TowerTree(int x, int y, CellPositionToPixelConverter converter) {
        super(x, y, 100, 100, 400, 250,  "TREE", converter);
    }
}
