package no.uib.inf101.sem2.entity.tower.towerTypes;

import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.grid.CellPositionToPixelConverter;

public class TowerFire extends Tower {

    public TowerFire(int x, int y, CellPositionToPixelConverter converter) {
        super(x, y, 100, 150, 600, 1200, 400, "FIRE", converter);
    }

}
