package baboon.industry.compat.btwaila;

import baboon.industry.block.machines.basic.entity.TileEntityMachineTrommel;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipMachineTrommel extends TileTooltip<TileEntityMachineTrommel> {
    @Override
    public void initTooltip() {
        addClass(TileEntityMachineTrommel.class);
    }
    @Override
    public void drawAdvancedTooltip(TileEntityMachineTrommel tile, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Current Health: " + tile.machineHealth, 0);
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);

        if (tile.active)
            advancedInfoComponent.drawStringWithShadow("Machine Progress: " + tile.currentMachineTime + " / " + tile.maxMachineTime, 0);

        advancedInfoComponent.drawInventory(tile, 0);
    }
}
