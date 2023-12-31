package baboon.industry.compat.btwaila;

import baboon.industry.Industry2;
import baboon.industry.block.reactor.entity.TileEntityReactorNew;
import net.minecraft.core.block.entity.TileEntity;
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip;
import toufoumaster.btwaila.TooltipGroup;
import toufoumaster.btwaila.TooltipRegistry;
import toufoumaster.btwaila.gui.GuiBlockOverlay;

public class TooltipReactor implements IBTWailaCustomBlockTooltip {

    @Override
    public void addTooltip() {
        TooltipGroup tooltipGroup = new TooltipGroup(Industry2.MOD_ID, TileEntityReactorNew.class, this);
        tooltipGroup.addTooltip(TileEntityReactorNew.class);
        TooltipRegistry.tooltipMap.add(tooltipGroup);
    }

    @Override
    public void drawAdvancedTooltip(TileEntity tileEntity, GuiBlockOverlay guiBlockOverlay) {
        TileEntityReactorNew tile = (TileEntityReactorNew) tileEntity;

        guiBlockOverlay.drawStringWithShadow("Current Heat: " + tile.heat + " / " + tile.maxHeat, 0);
        guiBlockOverlay.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
        guiBlockOverlay.drawStringWithShadow("Disabled: " + tile.isDisabled, 0);
        guiBlockOverlay.drawInventory(tile, 0);
    }
}
