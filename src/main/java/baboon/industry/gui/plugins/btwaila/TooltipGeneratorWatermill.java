package baboon.industry.gui.plugins.btwaila;

import baboon.industry.Industry2;
import baboon.industry.block.generator.entity.TileEntityGenerator;
import baboon.industry.block.generator.entity.TileEntityGeneratorWatermill;
import net.minecraft.core.block.entity.TileEntity;
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip;
import toufoumaster.btwaila.TooltipGroup;
import toufoumaster.btwaila.TooltipRegistry;
import toufoumaster.btwaila.gui.GuiBlockOverlay;

public class TooltipGeneratorWatermill implements IBTWailaCustomBlockTooltip {

    @Override
    public void addTooltip() {
        TooltipGroup tooltipGroup = new TooltipGroup(Industry2.MOD_ID, TileEntityGeneratorWatermill.class, this);
        tooltipGroup.addTooltip(TileEntityGeneratorWatermill.class);
        TooltipRegistry.tooltipMap.add(tooltipGroup);
    }

    @Override
    public void drawAdvancedTooltip(TileEntity tileEntity, GuiBlockOverlay guiBlockOverlay) {
        TileEntityGeneratorWatermill tile = (TileEntityGeneratorWatermill) tileEntity;
        guiBlockOverlay.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
        guiBlockOverlay.drawStringWithShadow("Water Level: " + tile.currentFuelTime + " / " + tile.maxFuelTime, 0);

        guiBlockOverlay.drawInventory(tile, 0);
    }
}
