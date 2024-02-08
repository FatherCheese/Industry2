package baboon.industry.item;

import baboon.industry.IndustryTags;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class ItemWrench extends Item {
    public ItemWrench(String name, int id) {
        super(name, id);
        setMaxDamage(127);
    }
    @Override
    public boolean onBlockDestroyed(World world, ItemStack itemstack, int id, int x, int y, int z, EntityLiving entityliving) {
        Block block = Block.blocksList[id];
        if (block != null && block.getHardness() > 0.0f && isSilkTouch()) {
            itemstack.damageItem(1, entityliving);
        }
        return true;
    }
    @Override
    public boolean canHarvestBlock(Block block) {
        return block.hasTag(IndustryTags.REQUIRES_WRENCH);
    }
}
