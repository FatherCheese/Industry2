package baboon.industry.compat.btwaila;

import baboon.industry.block.energy.cables.entity.TileEntityCable;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipCable extends TileTooltip<TileEntityCable> {
    @Override
    public void initTooltip() {
        addClass(TileEntityCable.class);
    }

    @Override
    public void drawAdvancedTooltip(TileEntityCable tileEntityCable, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Current Health: " + tileEntityCable.machineHealth, 0);
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + tileEntityCable.energy + " / " + tileEntityCable.capacity, 0);
    }
}
