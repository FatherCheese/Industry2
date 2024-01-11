package baboon.industry.compat.btwaila;

import baboon.industry.block.machines.basic.entity.TileEntityMachineCannery;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipMachineCannery extends TileTooltip<TileEntityMachineCannery> {
    @Override
    public void initTooltip() {
        addClass(TileEntityMachineCannery.class);
    }

    @Override
    public void drawAdvancedTooltip(TileEntityMachineCannery tile, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Current Health: " + tile.machineHealth, 0);
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);

        if (tile.active)
            advancedInfoComponent.drawStringWithShadow("Machine Progress: " + tile.currentMachineTime + " / " + tile.maxMachineTime, 0);

        advancedInfoComponent.drawInventory(tile, 0);
    }
}
