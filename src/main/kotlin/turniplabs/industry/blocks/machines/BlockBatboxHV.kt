package turniplabs.industry.blocks.machines

import net.minecraft.core.block.BlockTileEntity
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.world.World
import sunsetsatellite.energyapi.EnergyAPI
import turniplabs.industry.blocks.entities.TIleEntityBatboxHV
import turniplabs.industry.gui.ContainerBatboxBase
import turniplabs.industry.gui.GuiBatboxHV

class BlockBatboxHV(key: String?, id: Int, material: Material?) : BlockTileEntity(key, id, material) {

    override fun getNewBlockEntity(): TileEntity {
        return TIleEntityBatboxHV()
    }

    override fun blockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?): Boolean {
        if (!world!!.isClientSide) {
            val tileEntity: TIleEntityBatboxHV = world.getBlockTileEntity(x, y, z) as TIleEntityBatboxHV ?: return false

            EnergyAPI.displayGui(
                player,
                GuiBatboxHV(player?.inventory, tileEntity),
                ContainerBatboxBase(player?.inventory, tileEntity),
                player?.inventory
            )
        }
        return true
    }
}