package turniplabs.industry.blocks.models

import net.minecraft.client.render.RenderBlocks
import net.minecraft.core.world.WorldSource
import sunsetsatellite.energyapi.api.IEnergy
import turniplabs.industry.blocks.cables.BlockCable

class CableModel : RenderBlocks() {
    fun renderCable(renderBlocks: RenderBlocks, blockAccess: WorldSource, blockCable: BlockCable, x: Int, y: Int, z: Int): Boolean {
        val boundMin1 = 0.375f
        val boundMax1 = 0.625f

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

        if (aPosX) {
            blockCable.setBlockBounds(0.5f, boundMin1, boundMin1, 1.0f, boundMax1, boundMax1)
            renderBlocks.renderStandardBlock(blockCable, x, y, z)
        }

        if (aNegX) {
            blockCable.setBlockBounds(0.0f, boundMin1, boundMin1, 0.5f, boundMax1, boundMax1)
            renderBlocks.renderStandardBlock(blockCable, x, y, z)
        }

        if (aPosY) {
            blockCable.setBlockBounds(boundMin1, 0.5f, boundMin1, boundMax1, 1.0f, boundMax1)
            renderBlocks.renderStandardBlock(blockCable, x, y, z)
        }

        if (aNegY) {
            blockCable.setBlockBounds(boundMin1, 0.0f, boundMin1, boundMax1, 0.5f, boundMax1)
            renderBlocks.renderStandardBlock(blockCable, x, y, z)
        }

        if (aPosZ) {
            blockCable.setBlockBounds(boundMin1, boundMin1, 0.5f, boundMax1, boundMax1, 1.0f)
            renderBlocks.renderStandardBlock(blockCable, x, y, z)
        }

        if (aNegZ) {
            blockCable.setBlockBounds(boundMin1, boundMin1, 0.0f, boundMax1, boundMax1, 0.5f)
            renderBlocks.renderStandardBlock(blockCable, x, y, z)
        }

        blockCable.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)

        return true
    }
}