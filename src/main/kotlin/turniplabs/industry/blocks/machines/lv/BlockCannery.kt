package turniplabs.industry.blocks.machines.lv

import net.minecraft.core.block.BlockTileEntityRotatable
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.world.World
import sunsetsatellite.energyapi.interfaces.mixins.IEntityPlayer
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
}