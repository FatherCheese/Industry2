package turniplabs.industry.blocks.cables

import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.enums.EnumDropCause
import net.minecraft.core.item.ItemStack
import net.minecraft.core.world.World
import turniplabs.industry.items.IndustryItems

class BlockCableCopperInsulated(key: String?, id: Int, material: Material?, capacity: Int, transfer: Int, dangerLevel: Int) :
    BlockCable(key, id, material, capacity, transfer, dangerLevel) {

    override fun getBreakResult(
        world: World?,
        dropCause: EnumDropCause?,
        x: Int,
        y: Int,
        z: Int,
        meta: Int,
        tileEntity: TileEntity?
    ): Array<ItemStack> {
        return arrayOf(ItemStack(IndustryItems.itemInsulatedCopperCable))
    }
}