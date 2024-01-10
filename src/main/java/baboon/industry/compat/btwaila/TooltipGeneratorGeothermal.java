package baboon.industry.compat.btwaila;

import baboon.industry.block.generator.entity.TileEntityGeneratorGeothermal;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipGeneratorGeothermal extends TileTooltip<TileEntityGeneratorGeothermal> {
    @Override
    public void initTooltip() {
        addClass(TileEntityGeneratorGeothermal.class);
    }

    @Override
    public void drawAdvancedTooltip(TileEntityGeneratorGeothermal tile, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
        advancedInfoComponent.drawStringWithShadow("Lava Level: " + tile.currentFuelTime + " / " + tile.maxFuelTime, 0);

        advancedInfoComponent.drawInventory(tile, 0);
    }
}
