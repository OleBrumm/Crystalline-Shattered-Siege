package no.uib.inf101.sem2.entity.tower.towertypes;

import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.grid.converter.CellPositionToPixelConverter;

public class TowerFire extends Tower {

    public TowerFire(int x, int y, CellPositionToPixelConverter converter) {
        super(x, y, 15, 150, 600, 1200, "FIRE", converter);
    }

}
