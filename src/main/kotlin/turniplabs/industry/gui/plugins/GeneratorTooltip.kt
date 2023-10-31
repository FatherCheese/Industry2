package turniplabs.industry.gui.plugins

import net.minecraft.core.block.entity.TileEntity
import toufoumaster.btwaila.IBTWailaCustomBlockTooltip
import toufoumaster.btwaila.TooltipGroup
import toufoumaster.btwaila.TooltipRegistry
import toufoumaster.btwaila.gui.GuiBlockOverlay
import turniplabs.industry.Industry2
import turniplabs.industry.blocks.entities.TileEntityGenerator


class GeneratorTooltip : IBTWailaCustomBlockTooltip {

    override fun addTooltip() {
        val tooltipGroup = TooltipGroup(Industry2.MOD_ID, TileEntityGenerator::class.java, this)
        tooltipGroup.addTooltip(TileEntityGenerator::class.java)
        TooltipRegistry.tooltipMap.add(tooltipGroup)
    }

    override fun drawAdvancedTooltip(tileEntity: TileEntity?, guiBlockOverlay: GuiBlockOverlay) {
        val generator: TileEntityGenerator = tileEntity as TileEntityGenerator
        guiBlockOverlay.drawStringWithShadow("Stored Energy: ${(generator).energy} / ${generator.capacity}", 0)
        guiBlockOverlay.drawStringWithShadow("Burn Time: ${generator.currentBurnTime} / ${generator.maxBurnTime}", 0)
        guiBlockOverlay.drawStringWithShadow("Current Fuel: ${generator.currentFuel}", 0)
    }
}