package turniplabs.industry.blocks.machines

import net.minecraft.core.block.BlockTileEntity
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.world.World
import sunsetsatellite.energyapi.EnergyAPI
import turniplabs.industry.blocks.entities.TIleEntityBatboxLV
import turniplabs.industry.gui.ContainerBatboxBase
import turniplabs.industry.gui.GuiBatboxLV

class BlockBatboxLV(key: String?, id: Int, material: Material?) : BlockTileEntity(key, id, material) {

    override fun getNewBlockEntity(): TileEntity {
        return TIleEntityBatboxLV()
    }

    override fun blockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?): Boolean {
        if (!world!!.isClientSide) {
            val tileEntity: TIleEntityBatboxLV = world.getBlockTileEntity(x, y, z) as TIleEntityBatboxLV ?: return false

            EnergyAPI.displayGui(
                player,
                GuiBatboxLV(player?.inventory, tileEntity),
                ContainerBatboxBase(player?.inventory, tileEntity),
                player?.inventory
            )
        }
        return true
    }
}