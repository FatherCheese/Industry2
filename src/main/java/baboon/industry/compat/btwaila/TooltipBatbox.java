package baboon.industry.compat.btwaila;

import baboon.industry.block.storage.entity.TileEntityBatboxBase;
import baboon.industry.block.storage.entity.TileEntityBatboxEHV;
import baboon.industry.block.storage.entity.TileEntityBatboxHV;
import baboon.industry.block.storage.entity.TileEntityBatboxLV;
import baboon.industry.block.storage.entity.TileEntityBatboxMV;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipBatbox extends TileTooltip<TileEntityBatboxBase> {
    @Override
    public void initTooltip() {
        addClass(TileEntityBatboxLV.class);
        addClass(TileEntityBatboxMV.class);
        addClass(TileEntityBatboxHV.class);
        addClass(TileEntityBatboxEHV.class);
    }

    @Override
    public void drawAdvancedTooltip(TileEntityBatboxBase batBox, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Current Health: " + batBox.machineHealth, 0);
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + batBox.energy + " / " + batBox.capacity, 0);
        advancedInfoComponent.drawInventory(batBox, 0);
    }
}
