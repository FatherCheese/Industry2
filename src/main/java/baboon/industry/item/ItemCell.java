package baboon.industry.item;

import net.minecraft.core.HitResult;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.Vec3d;
import net.minecraft.core.world.World;

public class ItemCell extends Item {

    public ItemCell(String name, int id) {
        super(name, id);
    }

    public static boolean useCell(EntityPlayer player, ItemStack itemToGive) {
        if (player.inventory.getCurrentItem().stackSize <= 1) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, itemToGive);
            return true;
        } else {
            player.inventory.insertItem(itemToGive, true);
            if (itemToGive.stackSize < 1) {
                player.inventory.getCurrentItem().consumeItem(player);
                return true;
            } else {
                return false;
            }
        }
    }

    // Totally original code
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        float playerXRot = entityplayer.xRotO + (entityplayer.xRot - entityplayer.xRotO);
        float playerYRot = entityplayer.yRotO + (entityplayer.yRot - entityplayer.yRotO);
        double playerX = entityplayer.xo + (entityplayer.x - entityplayer.xo);
        double playerY = entityplayer.yo + (entityplayer.y - entityplayer.yo) + 1.62 - (double)entityplayer.heightOffset;
        double playerZ = entityplayer.zo + (entityplayer.z - entityplayer.zo);
        Vec3d vec3d = Vec3d.createVector(playerX, playerY, playerZ);
        float cosNegPlayerRotY = MathHelper.cos(-playerYRot * 0.01745329F - 3.141593F);
        float sinNegPlayerRotY = MathHelper.sin(-playerYRot * 0.01745329F - 3.141593F);
        float conNegPlayerRotX = -MathHelper.cos(-playerXRot * 0.01745329F);
        float sinNegPlayerRotX = MathHelper.sin(-playerXRot * 0.01745329F);
        float combo1 = sinNegPlayerRotY * conNegPlayerRotX;
        float combo2 = cosNegPlayerRotY * conNegPlayerRotX;
        Vec3d vec3d1 = vec3d.addVector((double)combo1 * 5.0, (double)sinNegPlayerRotX * 5.0, (double)combo2 * 5.0);
        HitResult movingObjectPosition = world.checkBlockCollisionBetweenPoints(vec3d, vec3d1, true);

        if (movingObjectPosition != null) {
            if (movingObjectPosition.hitType == HitResult.HitType.TILE) {
                int mopX = movingObjectPosition.x;
                int mopY = movingObjectPosition.y;
                int mopZ = movingObjectPosition.z;
                if (!world.canMineBlock(entityplayer, mopX, mopY, mopZ))
                    return itemstack;

                if (world.getBlockMaterial(mopX, mopY, mopZ) == Material.water && world.getBlockMetadata(mopX, mopY, mopZ) == 0) {
                    if (useCell(entityplayer, new ItemStack(I2Items.cellWater))) {
                        world.setBlockWithNotify(mopX, mopY, mopZ, 0);
                        entityplayer.swingItem();
                    }
                } else if (world.getBlockMaterial(mopX, mopY, mopZ) == Material.lava
                        && world.getBlockMetadata(mopX, mopY, mopZ) == 0
                        && useCell(entityplayer, new ItemStack(I2Items.cellLava))) {
                    world.setBlockWithNotify(mopX, mopY, mopZ, 0);
                    entityplayer.swingItem();
                }
            }

        }
        return itemstack;
    }
}
