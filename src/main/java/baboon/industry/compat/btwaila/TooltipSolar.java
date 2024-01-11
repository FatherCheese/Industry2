package baboon.industry.compat.btwaila;

import baboon.industry.block.generator.entity.TileEntityArrayEHV;
import baboon.industry.block.generator.entity.TileEntityArrayHV;
import baboon.industry.block.generator.entity.TileEntityArrayLV;
import baboon.industry.block.generator.entity.TileEntityArrayMV;
import baboon.industry.block.generator.entity.TileEntityGeneratorSolar;
import baboon.industry.block.generator.entity.TileEntitySolarBase;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipSolar extends TileTooltip<TileEntitySolarBase> {
    @Override
    public void initTooltip() {
        addClass(TileEntityGeneratorSolar.class);
        addClass(TileEntityArrayLV.class);
        addClass(TileEntityArrayMV.class);
        addClass(TileEntityArrayHV.class);
        addClass(TileEntityArrayEHV.class);
    }
    @Override
    public void drawAdvancedTooltip(TileEntitySolarBase tile, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
        advancedInfoComponent.drawStringWithShadow("Is sun visible? " + tile.getIsSkyVisible(), 0);

        advancedInfoComponent.drawInventory(tile, 0);
    }
}
