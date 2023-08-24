package turniplabs.industry.blocks.machines

import net.minecraft.core.block.BlockTileEntity
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.world.World
import sunsetsatellite.energyapi.EnergyAPI
import turniplabs.industry.blocks.entities.TileEntitySolarMV
import turniplabs.industry.gui.ContainerSolarGenerator
import turniplabs.industry.gui.GuiSolarGenerator

class BlockSolarArrayMV(key: String?, id: Int, material: Material?) : BlockTileEntity(key, id, material) {

    override fun getNewBlockEntity(): TileEntity {
        return TileEntitySolarMV()
    }

    override fun blockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?): Boolean {
        if (!world?.isClientSide!!) {
            val tileEntity: TileEntitySolarMV = world.getBlockTileEntity(x, y, z) as TileEntitySolarMV

            tileEntity ?: return false
            EnergyAPI.displayGui(
                player, GuiSolarGenerator(player?.inventory, tileEntity),
                ContainerSolarGenerator(player?.inventory, tileEntity),
                player?.inventory
            )
        }
        return true
    }
}