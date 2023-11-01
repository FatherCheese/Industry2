package turniplabs.industry.gui.plugins.tooltips

import net.minecraft.core.block.entity.TileEntity
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip
import toufoumaster.btwaila.TooltipGroup
import toufoumaster.btwaila.TooltipRegistry
import toufoumaster.btwaila.gui.GuiBlockOverlay
import turniplabs.industry.Industry2
import turniplabs.industry.blocks.entities.TileEntityEnergyConductorDamageable
import turniplabs.industry.blocks.entities.transformer.TileEntityTransformerHVtoMV
import turniplabs.industry.blocks.entities.transformer.TileEntityTransformerMVtoLV
import turniplabs.industry.blocks.entities.transformer.TileEntityTransformerSHVtoHV

object TransformerTooltip : IBTWailaCustomBlockTooltip {
    override fun addTooltip() {
        val tooltipGroup = TooltipGroup(Industry2.MOD_ID, TileEntityEnergyConductorDamageable::class.java, this)
        tooltipGroup.addTooltip(TileEntityTransformerMVtoLV::class.java)
        tooltipGroup.addTooltip(TileEntityTransformerHVtoMV::class.java)
        tooltipGroup.addTooltip(TileEntityTransformerSHVtoHV::class.java)
        TooltipRegistry.tooltipMap.add(tooltipGroup)
    }

    override fun drawAdvancedTooltip(tileEntity: TileEntity?, guiBlockOverlay: GuiBlockOverlay) {
        val tile = tileEntity as TileEntityEnergyConductorDamageable
        guiBlockOverlay.drawStringWithShadow("Current Health: ${tile.getMachineHealth}", 0)
        guiBlockOverlay.drawStringWithShadow("Stored Energy: ${tile.energy} / ${tile.capacity}", 0)
    }
}