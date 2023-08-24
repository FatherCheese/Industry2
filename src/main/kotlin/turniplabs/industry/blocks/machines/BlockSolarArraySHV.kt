package turniplabs.industry.blocks.machines

import net.minecraft.core.block.BlockTileEntity
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.world.World
import sunsetsatellite.energyapi.EnergyAPI
import turniplabs.industry.blocks.entities.TileEntitySolarSHV
import turniplabs.industry.gui.ContainerSolarBase
import turniplabs.industry.gui.GuiSolarArraySHV

class BlockSolarArraySHV(key: String?, id: Int, material: Material?) : BlockTileEntity(key, id, material) {

    override fun getNewBlockEntity(): TileEntity {
        return TileEntitySolarSHV()
    }

    override fun blockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?): Boolean {
        if (!world?.isClientSide!!) {
            val tileEntity: TileEntitySolarSHV = world.getBlockTileEntity(x, y, z) as TileEntitySolarSHV

            tileEntity ?: return false
            EnergyAPI.displayGui(
                player, GuiSolarArraySHV(player?.inventory, tileEntity),
                ContainerSolarBase(player?.inventory, tileEntity),
                player?.inventory
            )
        }
        return true
    }
}