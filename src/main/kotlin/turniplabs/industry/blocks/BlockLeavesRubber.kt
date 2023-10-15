package turniplabs.industry.blocks

import net.minecraft.core.block.Block
import net.minecraft.core.block.BlockLeavesBase
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.enums.EnumDropCause
import net.minecraft.core.item.ItemStack
import net.minecraft.core.world.World

class BlockLeavesRubber(key: String?, id: Int, material: Material?, flag: Boolean) : BlockLeavesBase(key, id, material, flag) {

    override fun getBreakResult(
        world: World,
        dropCause: EnumDropCause,
        x: Int,
        y: Int,
        z: Int,
        meta: Int,
        tileEntity: TileEntity?
    ): Array<ItemStack>? {
        return if (dropCause != EnumDropCause.PICK_BLOCK && dropCause != EnumDropCause.SILK_TOUCH) {
            if (world.rand.nextInt(20) != 0) null else arrayOf(ItemStack(this.sapling, 1))
        } else {
            arrayOf(ItemStack(this))
        }
    }

    override fun getSapling(): Block {
        return IndustryBlocks.rubberSapling
    }
}