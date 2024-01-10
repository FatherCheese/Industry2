package baboon.industry.compat.btwaila;

import baboon.industry.block.generator.entity.TileEntityGeneratorWindmill;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipGeneratorWindmill extends TileTooltip<TileEntityGeneratorWindmill> {
    @Override
    public void initTooltip() {
        addClass(TileEntityGeneratorWindmill.class);
    }

    @Override
    public void drawAdvancedTooltip(TileEntityGeneratorWindmill tile, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
        advancedInfoComponent.drawStringWithShadow("Current Height: " + tile.y, 0);

        advancedInfoComponent.drawInventory(tile, 0);
    }
}
