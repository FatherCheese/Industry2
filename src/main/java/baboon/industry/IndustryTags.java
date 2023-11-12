package baboon.industry;

import net.minecraft.core.block.Block;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.item.Item;

public abstract class IndustryTags {
    public static final Tag<Item> PREVENT_ITEM_RECYCLING = Tag.of("prevent_item_recycling");
    public static final Tag<Item> PREVENT_FABRICATING = Tag.of("prevent_fabricating");
    public static final Tag<Block> REQUIRES_WRENCH = Tag.of("requires_wrench");
}
