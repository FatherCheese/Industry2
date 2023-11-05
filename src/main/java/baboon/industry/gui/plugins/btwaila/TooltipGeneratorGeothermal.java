package baboon.industry.gui.plugins.btwaila;

import baboon.industry.Industry2;
import baboon.industry.block.generator.entity.TileEntityGeneratorGeothermal;
import net.minecraft.core.block.entity.TileEntity;
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip;
import toufoumaster.btwaila.TooltipGroup;
import toufoumaster.btwaila.TooltipRegistry;
import toufoumaster.btwaila.gui.GuiBlockOverlay;

public class TooltipGeneratorGeothermal implements IBTWailaCustomBlockTooltip {

    @Override
    public void addTooltip() {
        TooltipGroup tooltipGroup = new TooltipGroup(Industry2.MOD_ID, TileEntityGeneratorGeothermal.class, this);
        tooltipGroup.addTooltip(TileEntityGeneratorGeothermal.class);
        TooltipRegistry.tooltipMap.add(tooltipGroup);
    }

    @Override
    public void drawAdvancedTooltip(TileEntity tileEntity, GuiBlockOverlay guiBlockOverlay) {
        TileEntityGeneratorGeothermal tile = (TileEntityGeneratorGeothermal) tileEntity;
        guiBlockOverlay.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
        guiBlockOverlay.drawStringWithShadow("Lava Level: " + tile.currentFuelTime + " / " + tile.maxFuelTime, 0);

        guiBlockOverlay.drawInventory(tile, 0);
    }
}
