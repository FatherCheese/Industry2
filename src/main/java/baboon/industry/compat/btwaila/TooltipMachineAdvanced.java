package baboon.industry.compat.btwaila;

import baboon.industry.block.machines.advanced.entity.TIleEntityAdvancedExtractor;
import baboon.industry.block.machines.advanced.entity.TileEntityAdvancedBase;
import baboon.industry.block.machines.advanced.entity.TileEntityAdvancedCompressor;
import baboon.industry.block.machines.advanced.entity.TileEntityAdvancedFurnace;
import baboon.industry.block.machines.advanced.entity.TileEntityAdvancedMacerator;
import baboon.industry.block.machines.advanced.entity.TileEntityAdvancedWiremill;
import toufoumaster.btwaila.gui.components.AdvancedInfoComponent;
import toufoumaster.btwaila.tooltips.TileTooltip;

public class TooltipMachineAdvanced extends TileTooltip<TileEntityAdvancedBase> {
    @Override
    public void initTooltip() {
        addClass(TileEntityAdvancedFurnace.class);
        addClass(TileEntityAdvancedMacerator.class);
        addClass(TileEntityAdvancedCompressor.class);
        addClass(TileEntityAdvancedWiremill.class);
        addClass(TIleEntityAdvancedExtractor.class);
    }

    @Override
    public void drawAdvancedTooltip(TileEntityAdvancedBase tile, AdvancedInfoComponent advancedInfoComponent) {
        advancedInfoComponent.drawStringWithShadow("Current Health: " + tile.machineHealth, 0);
        advancedInfoComponent.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
        advancedInfoComponent.drawStringWithShadow("Stored Redstone: " + tile.redstone + " / " + tile.maxRedstone, 0);

        if (tile.active)
            advancedInfoComponent.drawStringWithShadow("Machine Progress: " + tile.currentMachineTime + " / " + tile.maxMachineTime, 0);

        advancedInfoComponent.drawInventory(tile, 0);
    }
}
