package baboon.industry.compat.btwaila;

import baboon.industry.Industry2;
import baboon.industry.block.machines.basic.entity.TileEntityMachineCannery;
import net.minecraft.core.block.entity.TileEntity;
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip;
import toufoumaster.btwaila.TooltipGroup;
import toufoumaster.btwaila.TooltipRegistry;
import toufoumaster.btwaila.gui.GuiBlockOverlay;

public class TooltipMachineCannery implements IBTWailaCustomBlockTooltip {

    @Override
    public void addTooltip() {
        TooltipGroup tooltipGroup = new TooltipGroup(Industry2.MOD_ID, TileEntityMachineCannery.class, this);
        tooltipGroup.addTooltip(TileEntityMachineCannery.class);
        TooltipRegistry.tooltipMap.add(tooltipGroup);
    }

    @Override
    public void drawAdvancedTooltip(TileEntity tileEntity, GuiBlockOverlay guiBlockOverlay) {
        TileEntityMachineCannery tile = (TileEntityMachineCannery) tileEntity;
        guiBlockOverlay.drawStringWithShadow("Current Health: " + tile.machineHealth, 0);
        guiBlockOverlay.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);

        if (tile.active)
            guiBlockOverlay.drawStringWithShadow("Machine Progress: " + tile.currentMachineTime + " / " + tile.maxMachineTime, 0);

        guiBlockOverlay.drawInventory(tile, 0);
    }
}
