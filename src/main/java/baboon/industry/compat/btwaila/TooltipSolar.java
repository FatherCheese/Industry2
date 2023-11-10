package baboon.industry.compat.btwaila;

import baboon.industry.Industry2;
import baboon.industry.block.generator.entity.*;
import net.minecraft.core.block.entity.TileEntity;
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip;
import toufoumaster.btwaila.TooltipGroup;
import toufoumaster.btwaila.TooltipRegistry;
import toufoumaster.btwaila.gui.GuiBlockOverlay;

public class TooltipSolar implements IBTWailaCustomBlockTooltip {

    @Override
    public void addTooltip() {
        TooltipGroup tooltipGroup = new TooltipGroup(Industry2.MOD_ID, TileEntitySolarBase.class, this);
        tooltipGroup.addTooltip(TileEntityGeneratorSolar.class);
        tooltipGroup.addTooltip(TileEntityArrayLV.class);
        tooltipGroup.addTooltip(TileEntityArrayMV.class);
        tooltipGroup.addTooltip(TileEntityArrayHV.class);
        tooltipGroup.addTooltip(TileEntityArrayEHV.class);
        TooltipRegistry.tooltipMap.add(tooltipGroup);
    }

    @Override
    public void drawAdvancedTooltip(TileEntity tileEntity, GuiBlockOverlay guiBlockOverlay) {
        TileEntitySolarBase tile = (TileEntitySolarBase) tileEntity;
        guiBlockOverlay.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
        guiBlockOverlay.drawStringWithShadow("Is sun visible? " + tile.getIsSkyVisible(), 0);

        guiBlockOverlay.drawInventory(tile, 0);
    }
}
