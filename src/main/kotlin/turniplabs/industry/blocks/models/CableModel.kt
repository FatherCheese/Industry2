package turniplabs.industry.blocks.models

import net.minecraft.client.Minecraft
import net.minecraft.client.render.RenderBlocks
import net.minecraft.core.world.World
import net.minecraft.core.world.WorldSource
import sunsetsatellite.energyapi.api.IEnergy
import turniplabs.industry.blocks.cables.BlockCable

class CableModel : RenderBlocks() {
    private val worldObj: World = Minecraft.getMinecraft(this).theWorld

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

        // Credit to UselessBullets & Apollo from the modding Discord for help!
        // TODO (fix hitboxes and the circling, and add corners)
        val xValMin = when {
            aPosX && aNegX -> 0.0f
            aPosX -> 0.5f
            aNegX -> 0.0f
            else -> boundMin1
        }

        val yValMin = when {
            aPosY && aNegY -> 0.0f
            aPosY -> 0.5f
            aNegY -> 0.0f
            else -> boundMin1
        }

        val zValMin = when {
            aPosZ && aNegZ -> 0.0f
            aPosZ -> 0.5f
            aNegZ -> 0.0f
            else -> boundMin1
        }

        val xValMax = when {
            aPosX && aNegX -> 1.0f
            aPosX -> 1.0f
            aNegX -> 0.5f
            else -> boundMax1
        }

        val yValMax = when {
            aPosY && aNegY -> 1.0f
            aPosY -> 1.0f
            aNegY -> 0.5f
            else -> boundMax1
        }

        val zValMax = when {
            aPosZ && aNegZ -> 1.0f
            aPosZ -> 1.0f
            aNegZ -> 0.5f
            else -> boundMax1
        }

        blockCable.setBlockBounds(xValMin, yValMin, zValMin, xValMax, yValMax, zValMax)
        renderBlocks.renderStandardBlock(blockCable, x, y, z)

        return true
    }
}