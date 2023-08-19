package turniplabs.industry.blocks.cables

import net.minecraft.core.block.BlockTileEntity
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.world.World
import turniplabs.industry.blocks.entities.TileEntityCable

open class BlockCable(
    key: String?,
    id: Int,
    material: Material?,
    internal val capacity: Int,
    internal val transfer: Int,
    internal val dangerLevel: Int
) : BlockTileEntity(key, id, material) {

    override fun getNewBlockEntity(): TileEntity {
        return TileEntityCable(capacity, 0, transfer, dangerLevel)
    }

    override fun renderAsNormalBlock(): Boolean {
        return false
    }

    override fun blockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?): Boolean {
        return false
    }

    override fun isOpaqueCube(): Boolean {
        return false
    }
}