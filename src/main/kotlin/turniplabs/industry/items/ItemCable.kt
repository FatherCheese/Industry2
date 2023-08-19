package turniplabs.industry.items

import net.minecraft.core.block.Block
import net.minecraft.core.item.ItemPlaceable
import net.minecraft.core.item.ItemStack
import net.minecraft.core.net.command.TextFormatting
import sunsetsatellite.sunsetutils.util.ICustomDescription
import turniplabs.industry.blocks.cables.BlockCable

class ItemCable(name: String?, id: Int, blockToPlace: Block?) : ItemPlaceable(name, id, blockToPlace), ICustomDescription {
    private val capacity = (blockToPlace as BlockCable).capacity
    private val transfer = (blockToPlace as BlockCable).transfer

    override fun getDescription(p0: ItemStack?): String {
        val text = StringBuilder()
        return text.append("${TextFormatting.WHITE}Max Transfer: ${TextFormatting.LIGHT_GRAY}IN: $capacity${TextFormatting.WHITE} / ${TextFormatting.LIGHT_GRAY}OUT: $transfer").toString()
    }
}