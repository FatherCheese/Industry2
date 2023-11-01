package turniplabs.industry.blocks.machines.lv

import net.minecraft.core.block.BlockTileEntityRotatable
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.enums.EnumDropCause
import net.minecraft.core.item.ItemStack
import net.minecraft.core.world.World
import sunsetsatellite.energyapi.interfaces.mixins.IEntityPlayer
import turniplabs.industry.blocks.IndustryBlocks
import turniplabs.industry.blocks.entities.lv.TileEntityCannery

class BlockCannery(key: String?, id: Int, material: Material?) : BlockTileEntityRotatable(key, id, material) {
    private var keepInventory = false

    override fun getNewBlockEntity(): TileEntity {
        return TileEntityCannery()
    }

    override fun blockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?): Boolean {
        if (!world!!.isClientSide) {
            val tileEntity: TileEntityCannery = world.getBlockTileEntity(x, y, z) as TileEntityCannery

            tileEntity ?: return false
            (player as IEntityPlayer).displayGuiScreen_energyapi(tileEntity)
        }
        return true
    }

    override fun getBreakResult(
        world: World?,
        dropCause: EnumDropCause?,
        x: Int,
        y: Int,
        z: Int,
        meta: Int,
        tileEntity: TileEntity?
    ): Array<ItemStack> {
        return when (dropCause) {
            EnumDropCause.SILK_TOUCH, EnumDropCause.PICK_BLOCK, EnumDropCause.PROPER_TOOL -> arrayOf(ItemStack(this))
            else ->  arrayOf(ItemStack(IndustryBlocks.machineCasing))
        }
    }
}