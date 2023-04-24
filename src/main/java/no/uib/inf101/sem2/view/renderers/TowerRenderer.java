package no.uib.inf101.sem2.view.renderers;

import no.uib.inf101.sem2.entity.tower.Tower;
import no.uib.inf101.sem2.grid.converter.CellPositionToPixelConverter;
import no.uib.inf101.sem2.util.RenderingUtils;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;

public class TowerRenderer {

    private final Map<String, Image> towerImages;

    public TowerRenderer(Map<String, Image> towerImages) {
        this.towerImages = towerImages;
    }

    public void drawTowers(Graphics2D graphics2D, CellPositionToPixelConverter converter, List<Tower> towers) {
        for (Tower tower : towers) {
            drawTower(graphics2D, tower, converter);
        }
    }

    private void drawTower(Graphics2D graphics2D, Tower tower, CellPositionToPixelConverter converter) {
        Rectangle2D towerRectangle = converter.getBoundsForCell(tower.getCellPosition());
        Image towerImage = getTowerImage(tower);
        RenderingUtils.drawImageRectangle(graphics2D, towerRectangle, towerImage);
    }

    /**
     * Returns the image for the given tower
     *
     * @param tower the tower to get the image for
     * @return the image for the given tower
     */
    private Image getTowerImage(Tower tower) {
        return switch (tower.getType()) {
            case "TREE" -> towerImages.get("TowerTree");
            case "ICE" -> towerImages.get("TowerIce");
            case "FIRE" -> towerImages.get("TowerFire");
            default -> null;
        };
    }
}
