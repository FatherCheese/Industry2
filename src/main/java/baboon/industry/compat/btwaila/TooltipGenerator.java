package baboon.industry.compat.btwaila;

import baboon.industry.Industry2;
import baboon.industry.block.generator.entity.TileEntityGenerator;
import net.minecraft.core.block.entity.TileEntity;
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip;
import toufoumaster.btwaila.TooltipGroup;
import toufoumaster.btwaila.TooltipRegistry;
import toufoumaster.btwaila.gui.GuiBlockOverlay;

public class TooltipGenerator implements IBTWailaCustomBlockTooltip {

    @Override
    public void addTooltip() {
        TooltipGroup tooltipGroup = new TooltipGroup(Industry2.MOD_ID, TileEntityGenerator.class, this);
        tooltipGroup.addTooltip(TileEntityGenerator.class);
        TooltipRegistry.tooltipMap.add(tooltipGroup);
    }

    @Override
    public void drawAdvancedTooltip(TileEntity tileEntity, GuiBlockOverlay guiBlockOverlay) {
        TileEntityGenerator tile = (TileEntityGenerator) tileEntity;
        guiBlockOverlay.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);

        if (tile.active)
            guiBlockOverlay.drawStringWithShadow("Machine Progress: " + tile.currentBurnTime + " / " + tile.maxBurnTime, 0);

        guiBlockOverlay.drawInventory(tile, 0);
    }
}
