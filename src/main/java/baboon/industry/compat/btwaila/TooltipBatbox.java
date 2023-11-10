package baboon.industry.compat.btwaila;

import baboon.industry.Industry2;
import baboon.industry.block.storage.entity.*;
import net.minecraft.core.block.entity.TileEntity;
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip;
import toufoumaster.btwaila.TooltipGroup;
import toufoumaster.btwaila.TooltipRegistry;
import toufoumaster.btwaila.gui.GuiBlockOverlay;

public class TooltipBatbox implements IBTWailaCustomBlockTooltip {

    @Override
    public void addTooltip() {
        TooltipGroup tooltipGroup = new TooltipGroup(Industry2.MOD_ID, TileEntityBatboxBase.class, this);
        tooltipGroup.addTooltip(TileEntityBatboxLV.class);
        tooltipGroup.addTooltip(TileEntityBatboxMV.class);
        tooltipGroup.addTooltip(TileEntityBatboxHV.class);
        tooltipGroup.addTooltip(TileEntityBatboxEHV.class);
        TooltipRegistry.tooltipMap.add(tooltipGroup);
    }

    @Override
    public void drawAdvancedTooltip(TileEntity tileEntity, GuiBlockOverlay guiBlockOverlay) {
        TileEntityBatboxBase tile = (TileEntityBatboxBase) tileEntity;
        guiBlockOverlay.drawStringWithShadow("Current Health: " + tile.machineHealth, 0);
        guiBlockOverlay.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);

        guiBlockOverlay.drawInventory(tile, 0);
    }
}
