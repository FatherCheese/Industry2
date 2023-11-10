package baboon.industry.compat.btwaila;

import baboon.industry.Industry2;
import baboon.industry.block.storage.entity.TileEntityTransformerBase;
import baboon.industry.block.storage.entity.TileEntityTransformerEHVtoHV;
import baboon.industry.block.storage.entity.TileEntityTransformerHVtoMV;
import baboon.industry.block.storage.entity.TileEntityTransformerMVtoLV;
import net.minecraft.core.block.entity.TileEntity;
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip;
import toufoumaster.btwaila.TooltipGroup;
import toufoumaster.btwaila.TooltipRegistry;
import toufoumaster.btwaila.gui.GuiBlockOverlay;

public class TooltipTransformer implements IBTWailaCustomBlockTooltip {

    @Override
    public void addTooltip() {
        TooltipGroup tooltipGroup = new TooltipGroup(Industry2.MOD_ID, TileEntityTransformerBase.class, this);
        tooltipGroup.addTooltip(TileEntityTransformerMVtoLV.class);
        tooltipGroup.addTooltip(TileEntityTransformerHVtoMV.class);
        tooltipGroup.addTooltip(TileEntityTransformerEHVtoHV.class);
        TooltipRegistry.tooltipMap.add(tooltipGroup);
    }

    @Override
    public void drawAdvancedTooltip(TileEntity tileEntity, GuiBlockOverlay guiBlockOverlay) {
        TileEntityTransformerBase tile = (TileEntityTransformerBase) tileEntity;
        guiBlockOverlay.drawStringWithShadow("Current Health: " + tile.machineHealth, 0);
        guiBlockOverlay.drawStringWithShadow("Stored Energy: " + tile.energy + " / " + tile.capacity, 0);
    }
}
