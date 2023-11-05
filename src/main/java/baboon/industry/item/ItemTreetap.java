package baboon.industry.item;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class ItemTreetap extends Item {

    public ItemTreetap(int id) {
        super(id);
        setMaxDamage(64);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        return super.onItemRightClick(itemstack, world, entityplayer);
    }
}
