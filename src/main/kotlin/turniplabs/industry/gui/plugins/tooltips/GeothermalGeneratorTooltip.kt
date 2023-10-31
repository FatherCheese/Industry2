package turniplabs.industry.gui.plugins.tooltips

import net.minecraft.core.block.entity.TileEntity
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip
import toufoumaster.btwaila.TooltipGroup
import toufoumaster.btwaila.TooltipRegistry
import toufoumaster.btwaila.gui.GuiBlockOverlay
import turniplabs.industry.Industry2
import turniplabs.industry.blocks.entities.TileEntityGeothermal


object GeothermalGeneratorTooltip : IBTWailaCustomBlockTooltip {

    override fun addTooltip() {
        val tooltipGroup = TooltipGroup(Industry2.MOD_ID, TileEntityGeothermal::class.java, this)
        tooltipGroup.addTooltip(TileEntityGeothermal::class.java)
        TooltipRegistry.tooltipMap.add(tooltipGroup)
    }

    override fun drawAdvancedTooltip(tileEntity: TileEntity?, guiBlockOverlay: GuiBlockOverlay) {
        val tile = tileEntity as TileEntityGeothermal
        guiBlockOverlay.drawStringWithShadow("Stored Energy: ${(tile).energy} / ${tile.capacity}", 0)
        guiBlockOverlay.drawStringWithShadow("Lava Level: ${tile.currentFuelTime} / ${tile.maxFuelTime}", 0)
        guiBlockOverlay.drawInventory(tile, 0)
    }
}