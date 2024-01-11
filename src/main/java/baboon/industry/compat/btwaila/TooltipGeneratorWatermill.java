package baboon.industry.compat.btwaila;

import baboon.industry.block.generator.entity.TileEntityGeneratorWatermill;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipGeneratorWatermill extends TileTooltip<TileEntityGeneratorWatermill> {
    @Override
    public void initTooltip() {
        addClass(TileEntityGeneratorWatermill.class);
    }
    @Override
    public void drawAdvancedTooltip(TileEntityGeneratorWatermill tile, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
        advancedInfoComponent.drawStringWithShadow("Water Level: " + tile.currentFuelTime + " / " + tile.maxFuelTime, 0);

        advancedInfoComponent.drawInventory(tile, 0);
    }
}
