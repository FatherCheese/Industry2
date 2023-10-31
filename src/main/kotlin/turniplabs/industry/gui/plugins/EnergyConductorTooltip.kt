package turniplabs.industry.gui.plugins

import net.minecraft.core.block.entity.TileEntity
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip
import toufoumaster.btwaila.TooltipGroup
import toufoumaster.btwaila.TooltipRegistry
import toufoumaster.btwaila.gui.GuiBlockOverlay
import turniplabs.industry.Industry2
import turniplabs.industry.blocks.entities.TileEntityEnergyConductorDamageable

class EnergyConductorTooltip : IBTWailaCustomBlockTooltip {

    override fun addTooltip() {
        val tooltipGroup = TooltipGroup(Industry2.MOD_ID, TileEntityEnergyConductorDamageable::class.java, this)
        tooltipGroup.addTooltip(TileEntityEnergyConductorDamageable::class.java)
        TooltipRegistry.tooltipMap.add(tooltipGroup)
    }

    override fun drawAdvancedTooltip(tileEntity: TileEntity?, guiBlockOverlay: GuiBlockOverlay) {
        val conductor: TileEntityEnergyConductorDamageable = tileEntity as TileEntityEnergyConductorDamageable

        guiBlockOverlay.drawStringWithShadow("Current Health: ${conductor.getMachineHealth}", 0)
        guiBlockOverlay.drawStringWithShadow("Stored Energy: ${(conductor).energy} / ${conductor.capacity}", 0)
    }
}