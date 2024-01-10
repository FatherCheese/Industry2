package baboon.industry.compat.btwaila;

import baboon.industry.block.storage.entity.TileEntityTransformerBase;
import baboon.industry.block.storage.entity.TileEntityTransformerEHVtoHV;
import baboon.industry.block.storage.entity.TileEntityTransformerHVtoMV;
import baboon.industry.block.storage.entity.TileEntityTransformerMVtoLV;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipTransformer extends TileTooltip<TileEntityTransformerBase> {
    @Override
    public void initTooltip() {
        addClass(TileEntityTransformerMVtoLV.class);
        addClass(TileEntityTransformerHVtoMV.class);
        addClass(TileEntityTransformerEHVtoHV.class);
    }
    @Override
    public void drawAdvancedTooltip(TileEntityTransformerBase tile, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Current Health: " + tile.machineHealth, 0);
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
    }
}
