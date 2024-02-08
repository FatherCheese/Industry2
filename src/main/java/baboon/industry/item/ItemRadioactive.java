package baboon.industry.item;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;

public class ItemRadioactive extends Item {
    int damageTimer = 0;

    public ItemRadioactive(String name, int id) {
        super(name, id);
    }

    private boolean isArmoured(Entity entity) {
        for (int i = 0; i < 4; i++)
            if (((EntityPlayer) entity).inventory.armorInventory[i] == null)
                return false;

        return ((EntityPlayer) entity).inventory.armorInventory[3].itemID == I2Items.armorHelmetHazmat.id     &&
                ((EntityPlayer) entity).inventory.armorInventory[2].itemID == I2Items.armorChestplateHazmat.id &&
                ((EntityPlayer) entity).inventory.armorInventory[1].itemID == I2Items.armorLeggingsHazmat.id   &&
                ((EntityPlayer) entity).inventory.armorInventory[0].itemID == I2Items.armorBootsHazmat.id;
    }

    @Override
    public void inventoryTick(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
        if (!world.isClientSide) {
            if (entity instanceof EntityPlayer && ((EntityPlayer) entity).getGamemode() != Gamemode.creative) {
                damageTimer++;

                if (damageTimer >= 20) {
                    if (isArmoured(entity)) {
                        ((EntityPlayer) entity).inventory.damageArmor(1, 3);
                        ((EntityPlayer) entity).inventory.damageArmor(1, 2);
                        ((EntityPlayer) entity).inventory.damageArmor(1, 1);
                        ((EntityPlayer) entity).inventory.damageArmor(1, 0);
                    } else
                        entity.hurt(null, 1, DamageType.FIRE);

                    damageTimer = 0;
                }
            }
        }
    }
}
