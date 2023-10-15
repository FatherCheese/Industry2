package turniplabs.industry.items

import net.minecraft.core.HitResult.HitType
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import net.minecraft.core.util.helper.MathHelper
import net.minecraft.core.util.phys.Vec3d
import net.minecraft.core.world.World

class ItemCell(id: Int) : Item(id) {

    // Translated code from ItemBucketEmpty
    override fun onItemRightClick(itemstack: ItemStack, world: World, entityplayer: EntityPlayer): ItemStack {
        val xRot = entityplayer.xRotO + (entityplayer.xRot - entityplayer.xRotO) * 1.0f
        val yRot = entityplayer.yRotO + (entityplayer.yRot - entityplayer.yRotO) * 1.0f

        val x = entityplayer.xo + (entityplayer.x - entityplayer.xo) * 1.0f.toDouble()
        val y =
            entityplayer.yo + (entityplayer.y - entityplayer.yo) * 1.0f.toDouble() + 1.62 - entityplayer.heightOffset.toDouble()
        val z = entityplayer.zo + (entityplayer.z - entityplayer.zo) * 1.0f.toDouble()

        val vec3d = Vec3d.createVector(x, y, z)

        val negYRot1 = MathHelper.cos(-yRot * 0.01745329f - 3.141593f)
        val negYRot2 = MathHelper.sin(-yRot * 0.01745329f - 3.141593f)
        val negXRot1 = -MathHelper.cos(-xRot * 0.01745329f)
        val negXRot2 = MathHelper.sin(-xRot * 0.01745329f)
        val rotMix1 = negYRot2 * negXRot1
        val rotMix2 = negYRot1 * negXRot1

        val vec3d1 = vec3d.addVector(rotMix1.toDouble() * 5.0f, negXRot2.toDouble() * 5.0f, rotMix2.toDouble() * 5.0f)

        val movingObjectPosition = world.checkBlockCollisionBetweenPoints(vec3d, vec3d1, true)
        return if (movingObjectPosition == null)
            itemstack
        else {
            if (movingObjectPosition.hitType == HitType.TILE) {
                val mopX = movingObjectPosition.x
                val mopY = movingObjectPosition.y
                val mopZ = movingObjectPosition.z
                if (!world.canMineBlock(entityplayer, mopX, mopY, mopZ))
                    return itemstack

                if (world.getBlockMaterial(mopX, mopY, mopZ) === Material.water && world.getBlockMetadata(mopX, mopY, mopZ) == 0) {
                    if (useCell(entityplayer, ItemStack(IndustryItems.waterCell))) {
                        world.setBlockWithNotify(mopX, mopY, mopZ, 0)
                        entityplayer.swingItem()
                    }
                } else
                    if (world.getBlockMaterial(mopX, mopY, mopZ) === Material.lava && world.getBlockMetadata(mopX, mopY, mopZ) == 0 && useCell(
                        entityplayer, ItemStack(
                            IndustryItems.lavaCell
                        )
                    )
                ) {
                    world.setBlockWithNotify(mopX, mopY, mopZ, 0)
                    entityplayer.swingItem()
                }
            }
            return itemstack
        }
    }

    fun useCell(player: EntityPlayer, itemToGive: ItemStack): Boolean {
        return if (player.inventory.getCurrentItem().stackSize <= 1) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, itemToGive)
            true
        } else {
            player.inventory.insertItem(itemToGive, true)
            if (itemToGive.stackSize < 1) {
                player.inventory.getCurrentItem().consumeItem(player)
                true
            } else {
                false
            }
        }
    }
}