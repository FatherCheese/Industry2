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
        // TODO (fix hitboxes and add corners)
        blockCable.setBlockBounds(boundMin1 - 0.0001f, boundMin1 - 0.0001f, boundMin1 - 0.0001f, boundMax1 + 0.0001f, boundMax1 + 0.0001f, boundMax1 + 0.0001f)

        renderBlocks.renderStandardBlock(blockCable, x, y, z);
        if (aPosX || aNegX){
            blockCable.setBlockBounds(0.5f + if (aNegX) -0.5f else 0.0f, boundMin1, boundMin1, 0.5f + if (aPosX) 0.5f else 0.0f, boundMax1, boundMax1)
            renderBlocks.renderStandardBlock(blockCable, x, y, z);
        }
        if (aNegY || aPosY){
            blockCable.setBlockBounds(boundMin1, 0.5f + if (aNegY) -0.5f else 0.0f, boundMin1, boundMax1, 0.5f + if (aPosY) 0.5f else 0.0f, boundMax1)
            renderBlocks.renderStandardBlock(blockCable, x, y, z);
        }
        if (aNegZ || aPosZ){
            blockCable.setBlockBounds(boundMin1,boundMin1, 0.5f + if (aNegZ) -0.5f else 0.0f, boundMax1, boundMax1, 0.5f + if (aPosZ) 0.5f else 0.0f)
            renderBlocks.renderStandardBlock(blockCable, x, y, z);
        }


        blockCable.setBlockBounds(0.15f, 0.15f, 0.15f, 0.85f, 0.85f, 0.85f)

        return true
    }
}