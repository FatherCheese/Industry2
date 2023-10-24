package turniplabs.industry.blocks.cables

import net.minecraft.core.block.BlockTileEntity
import net.minecraft.core.block.entity.TileEntity
import net.minecraft.core.block.material.Material
import net.minecraft.core.entity.player.EntityPlayer
import net.minecraft.core.util.phys.AABB
import net.minecraft.core.world.World
import sunsetsatellite.energyapi.api.IEnergy
import turniplabs.industry.blocks.entities.TileEntityCable

open class BlockCable(
    key: String?,
    id: Int,
    material: Material?,
    internal val capacity: Int,
    internal val transfer: Int,
    internal val dangerLevel: Int

) : BlockTileEntity(key, id, material) {
    init {
        val boundMin1 = 0.375f
        val boundMax1 = 0.625f
        setBlockBounds(0.25f, 0.25f, 0.25f, 0.75f, 0.75f, 0.75f)
    }

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
    override fun getSelectedBoundingBoxFromPool(world: World, x: Int, y: Int, z: Int): AABB? {
        return cableBoundingBox(world,this, x, y, z)
    }
    override fun getCollisionBoundingBoxFromPool(world: World, x: Int, y: Int, z: Int): AABB? {
        return cableBoundingBox(world,this, x, y, z)
    }
    private fun cableBoundingBox(blockAccess: World, blockCable: BlockCable, x: Int, y: Int, z: Int): AABB {
        val boundMin1 = 0.375
        val boundMax1 = 0.625
        val defaultBox = AABB(0.0, 0.0, 0.0, 0.0,0.0, 0.0)

        val aPosX: Boolean = blockAccess.getBlockId(x + 1, y, z) == blockCable.id ||
                blockAccess.getBlockTileEntity(x + 1, y, z) is IEnergy

        val aNegX: Boolean = blockAccess.getBlockId(x - 1, y, z) == blockCable.id ||
                blockAccess.getBlockTileEntity(x - 1, y, z) is IEnergy

        val aPosY: Boolean = blockAccess.getBlockId(x, y + 1, z) == blockCable.id ||
                blockAccess.getBlockTileEntity(x, y + 1, z) is IEnergy

        val aNegY: Boolean = blockAccess.getBlockId(x, y - 1, z) == blockCable.id ||
                blockAccess.getBlockTileEntity(x, y - 1, z) is IEnergy

        val aPosZ: Boolean = blockAccess.getBlockId(x, y, z + 1) == blockCable.id ||
                blockAccess.getBlockTileEntity(x, y, z + 1) is IEnergy

        val aNegZ: Boolean = blockAccess.getBlockId(x, y, z - 1) == blockCable.id ||
                blockAccess.getBlockTileEntity(x, y, z - 1) is IEnergy


        // Credit to UselessBullets & Apollo from the modding Discord for help!
        // TODO (fix hitboxes and add corners)
        val offset = boundMax1/2
        defaultBox.setBounds(boundMin1 - if (aNegX) offset else 0.0, boundMin1 - if (aNegY) offset else 0.0, boundMin1 - if (aNegZ) offset else 0.0, boundMax1 + if (aPosX) offset else 0.0, boundMax1 + if (aPosY) offset else 0.0, boundMax1 + if (aPosZ) offset else 0.0)

        return AABB.getBoundingBoxFromPool(x + defaultBox.minX, y + defaultBox.minY, z + defaultBox.minZ, x + defaultBox.maxX, y + defaultBox.maxY, z + defaultBox.maxZ)
    }
}