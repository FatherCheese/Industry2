package baboon.industry.compat.btwaila;

import baboon.industry.Industry2;
import baboon.industry.block.generator.entity.TileEntityGeneratorWindmill;
import net.minecraft.core.block.entity.TileEntity;
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip;
import toufoumaster.btwaila.TooltipGroup;
import toufoumaster.btwaila.TooltipRegistry;
import toufoumaster.btwaila.gui.GuiBlockOverlay;

public class TooltipGeneratorWindmill implements IBTWailaCustomBlockTooltip {

    @Override
    public void addTooltip() {
        TooltipGroup tooltipGroup = new TooltipGroup(Industry2.MOD_ID, TileEntityGeneratorWindmill.class, this);
        tooltipGroup.addTooltip(TileEntityGeneratorWindmill.class);
        TooltipRegistry.tooltipMap.add(tooltipGroup);
    }

    @Override
    public void drawAdvancedTooltip(TileEntity tileEntity, GuiBlockOverlay guiBlockOverlay) {
        TileEntityGeneratorWindmill tile = (TileEntityGeneratorWindmill) tileEntity;
        guiBlockOverlay.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
        guiBlockOverlay.drawStringWithShadow("Current Height: " + tile.yCoord, 0);

        guiBlockOverlay.drawInventory(tile, 0);
    }
}
