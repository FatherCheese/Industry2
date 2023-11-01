package turniplabs.industry.items

import net.minecraft.core.block.Block
import net.minecraft.core.item.Item
import turniplabs.industry.IndustryTags

class ItemWrench(id: Int) : Item(id) {

    init {
        maxDamage = 128
    }

    override fun canHarvestBlock(block: Block): Boolean {
        return block.hasTag(IndustryTags.REQUIRES_WRENCH)
    }
}