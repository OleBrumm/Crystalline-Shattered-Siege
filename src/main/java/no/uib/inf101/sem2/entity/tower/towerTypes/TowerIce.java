package no.uib.inf101.sem2.entity.tower.towerTypes;

import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.grid.CellPositionToPixelConverter;

public class TowerIce extends Tower {

    public TowerIce(int x, int y, CellPositionToPixelConverter converter) {
        super(x, y, 100, 100, 750,425, 350, "ICE", converter);
    }
}
