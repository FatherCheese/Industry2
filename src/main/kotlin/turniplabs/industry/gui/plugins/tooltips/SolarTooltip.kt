package turniplabs.industry.gui.plugins.tooltips

import net.minecraft.core.block.entity.TileEntity
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip
import toufoumaster.btwaila.TooltipGroup
import toufoumaster.btwaila.TooltipRegistry
import toufoumaster.btwaila.gui.GuiBlockOverlay
import turniplabs.industry.Industry2
import turniplabs.industry.blocks.entities.TileEntitySolarGenerator
import turniplabs.industry.blocks.entities.solar.*

object SolarTooltip : IBTWailaCustomBlockTooltip {

    override fun addTooltip() {
        val tooltipGroup = TooltipGroup(Industry2.MOD_ID, TileEntitySolarBase::class.java, this)
        tooltipGroup.addTooltip(TileEntitySolarBase::class.java)
        tooltipGroup.addTooltip(TileEntitySolarGenerator::class.java)
        tooltipGroup.addTooltip(TileEntitySolarLV::class.java)
        tooltipGroup.addTooltip(TileEntitySolarMV::class.java)
        tooltipGroup.addTooltip(TileEntitySolarHV::class.java)
        tooltipGroup.addTooltip(TileEntitySolarSHV::class.java)
        TooltipRegistry.tooltipMap.add(tooltipGroup)
    }

    override fun drawAdvancedTooltip(tileEntity: TileEntity?, guiBlockOverlay: GuiBlockOverlay) {
        val tile = tileEntity as TileEntitySolarBase
        guiBlockOverlay.drawStringWithShadow("Stored Energy: ${tile.energy} / ${tile.capacity}", 0)
        guiBlockOverlay.drawStringWithShadow("Can see the sky: ${tile.isFacingSky()}", 0)
        guiBlockOverlay.drawInventory(tile, 0)
    }
}