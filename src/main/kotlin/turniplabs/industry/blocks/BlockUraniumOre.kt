package turniplabs.industry.blocks

import net.minecraft.core.block.Block
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.enums.EnumDropCause
import net.minecraft.core.item.ItemStack
import net.minecraft.core.world.World
import turniplabs.industry.Industry2

class BlockUraniumOre(key: String?, id: Int, material: Material?) : Block(key, id, material) {
    override fun getBreakResult(
        world: World?,
        dropCause: EnumDropCause?,
        x: Int,
        y: Int,
        z: Int,
        meta: Int,
        tileEntity: TileEntity?
    ): Array<ItemStack>? {
        return when (dropCause) {
            EnumDropCause.SILK_TOUCH, EnumDropCause.PICK_BLOCK -> arrayOf(ItemStack(this))
            EnumDropCause.PROPER_TOOL -> arrayOf(ItemStack(Industry2.rawUranium))
            else -> null
        }
    }
}