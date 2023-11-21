package baboon.industry.compat.btwaila;

import baboon.industry.Industry2;
import baboon.industry.block.reactor.entity.TileEntityReactorIO;
import baboon.industry.block.reactor.entity.TileEntityReactorNew;
import net.minecraft.core.block.entity.TileEntity;
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip;
import toufoumaster.btwaila.TooltipGroup;
import toufoumaster.btwaila.TooltipRegistry;
import toufoumaster.btwaila.gui.GuiBlockOverlay;

public class TooltipReactorIO implements IBTWailaCustomBlockTooltip {

    @Override
    public void addTooltip() {
        TooltipGroup tooltipGroup = new TooltipGroup(Industry2.MOD_ID, TileEntityReactorIO.class, this);
        tooltipGroup.addTooltip(TileEntityReactorIO.class);
        TooltipRegistry.tooltipMap.add(tooltipGroup);
    }

    @Override
    public void drawAdvancedTooltip(TileEntity tileEntity, GuiBlockOverlay guiBlockOverlay) {
        TileEntityReactorIO tile = (TileEntityReactorIO) tileEntity;

        guiBlockOverlay.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
    }
}
