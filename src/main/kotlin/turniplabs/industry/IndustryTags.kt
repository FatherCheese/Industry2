package turniplabs.industry

import net.minecraft.core.block.Block
import net.minecraft.core.data.tag.Tag
import net.minecraft.core.item.Item

object IndustryTags {
    var PREVENT_ITEM_RECYCLING: Tag<Item> = Tag.of("prevent_item_recycling")

    var REQUIRES_WRENCH: Tag<Block> = Tag.of("requires_wrench")
}