package turniplabs.industry.blocks.machines

import net.minecraft.core.block.BlockTileEntity
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.world.World
import sunsetsatellite.energyapi.EnergyAPI
import turniplabs.industry.blocks.entities.TIleEntityBatboxSHV
import turniplabs.industry.gui.ContainerBatboxBase
import turniplabs.industry.gui.GuiBatboxSHV

class BlockBatboxSHV(key: String?, id: Int, material: Material?) : BlockTileEntity(key, id, material) {

    override fun getNewBlockEntity(): TileEntity {
        return TIleEntityBatboxSHV()
    }

    override fun blockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?): Boolean {
        if (!world!!.isClientSide) {
            val tileEntity: TIleEntityBatboxSHV = world.getBlockTileEntity(x, y, z) as TIleEntityBatboxSHV ?: return false

            EnergyAPI.displayGui(
                player,
                GuiBatboxSHV(player?.inventory, tileEntity),
                ContainerBatboxBase(player?.inventory, tileEntity),
                player?.inventory
            )
        }
        return true
    }
}