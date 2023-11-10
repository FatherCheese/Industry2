package baboon.industry.compat.btwaila;

import baboon.industry.Industry2;
import baboon.industry.block.machines.endgame.entity.TileEntityEnergyFabricator;
import net.minecraft.core.block.entity.TileEntity;
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip;
import toufoumaster.btwaila.TooltipGroup;
import toufoumaster.btwaila.TooltipRegistry;
import toufoumaster.btwaila.gui.GuiBlockOverlay;

public class TooltipFabricator implements IBTWailaCustomBlockTooltip {

    @Override
    public void addTooltip() {
        TooltipGroup tooltipGroup = new TooltipGroup(Industry2.MOD_ID, TileEntityEnergyFabricator.class, this);
        tooltipGroup.addTooltip(TileEntityEnergyFabricator.class);
        TooltipRegistry.tooltipMap.add(tooltipGroup);
    }

    @Override
    public void drawAdvancedTooltip(TileEntity tileEntity, GuiBlockOverlay guiBlockOverlay) {
        TileEntityEnergyFabricator tile = (TileEntityEnergyFabricator) tileEntity;
        guiBlockOverlay.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
        guiBlockOverlay.drawStringWithShadow("Stored Scrap: " + tile.scrap + " / " + tile.maxScrap, 0);
        guiBlockOverlay.drawStringWithShadow("Current Machine Time: " + tile.currentMachineTime + " / " + tile.maxMachineTime, 0);
        guiBlockOverlay.drawInventory(tile, 0);
    }
}
