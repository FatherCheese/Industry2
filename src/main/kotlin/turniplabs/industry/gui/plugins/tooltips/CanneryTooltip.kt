package turniplabs.industry.gui.plugins.tooltips

import net.minecraft.core.block.entity.TileEntity
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip
import toufoumaster.btwaila.TooltipGroup
import toufoumaster.btwaila.TooltipRegistry
import toufoumaster.btwaila.gui.GuiBlockOverlay
import turniplabs.industry.Industry2
import turniplabs.industry.blocks.entities.lv.TileEntityCannery


object CanneryTooltip : IBTWailaCustomBlockTooltip {

    override fun addTooltip() {
        val tooltipGroup = TooltipGroup(Industry2.MOD_ID, TileEntityCannery::class.java, this)
        tooltipGroup.addTooltip(TileEntityCannery::class.java)
        TooltipRegistry.tooltipMap.add(tooltipGroup)
    }

    override fun drawAdvancedTooltip(tileEntity: TileEntity?, guiBlockOverlay: GuiBlockOverlay) {
        val tile = tileEntity as TileEntityCannery
        guiBlockOverlay.drawStringWithShadow("Current Health: ${tile.getMachineHealth}", 0)
        guiBlockOverlay.drawStringWithShadow("Stored Energy: ${(tile).energy} / ${tile.capacity}", 0)
        guiBlockOverlay.drawStringWithShadow("Machine Progress: ${tile.currentMachineTime} / ${tile.maxMachineTime}", 0)
        guiBlockOverlay.drawInventory(tile, 0)
    }
}