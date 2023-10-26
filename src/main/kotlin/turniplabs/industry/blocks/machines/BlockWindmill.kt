package turniplabs.industry.blocks.machines

import net.minecraft.core.block.BlockTileEntityRotatable
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.world.World
import sunsetsatellite.energyapi.EnergyAPI
import sunsetsatellite.energyapi.interfaces.mixins.IEntityPlayer
import turniplabs.industry.blocks.entities.TileEntityWindmill

class BlockWindmill(key: String?, id: Int, material: Material?) : BlockTileEntityRotatable(key, id, material) {
    private var keepInventory = false

    override fun getNewBlockEntity(): TileEntity {
        return TileEntityWindmill()
    }

    override fun blockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?): Boolean {
        if (!world?.isClientSide!!) {
            val tileEntity: TileEntityWindmill = world.getBlockTileEntity(x, y, z) as TileEntityWindmill

            tileEntity ?: return false
            EnergyAPI.displayGui(player, tileEntity)
        }
        return true
    }
}