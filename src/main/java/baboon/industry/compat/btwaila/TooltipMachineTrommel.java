package baboon.industry.compat.btwaila;

import baboon.industry.Industry2;
import baboon.industry.block.machines.basic.entity.TileEntityMachineTrommel;
import net.minecraft.core.block.entity.TileEntity;
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip;
import toufoumaster.btwaila.TooltipGroup;
import toufoumaster.btwaila.TooltipRegistry;
import toufoumaster.btwaila.gui.GuiBlockOverlay;

public class TooltipMachineTrommel implements IBTWailaCustomBlockTooltip {

    @Override
    public void addTooltip() {
        TooltipGroup tooltipGroup = new TooltipGroup(Industry2.MOD_ID, TileEntityMachineTrommel.class, this);
        tooltipGroup.addTooltip(TileEntityMachineTrommel.class);
        TooltipRegistry.tooltipMap.add(tooltipGroup);
    }

    @Override
    public void drawAdvancedTooltip(TileEntity tileEntity, GuiBlockOverlay guiBlockOverlay) {
        TileEntityMachineTrommel tile = (TileEntityMachineTrommel) tileEntity;
        guiBlockOverlay.drawStringWithShadow("Current Health: " + tile.machineHealth, 0);
        guiBlockOverlay.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);

        if (tile.active)
            guiBlockOverlay.drawStringWithShadow("Machine Progress: " + tile.currentMachineTime + " / " + tile.maxMachineTime, 0);

        guiBlockOverlay.drawInventory(tile, 0);
    }
}
