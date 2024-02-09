package baboon.industry.block.energy.cables.model;

import baboon.industry.block.energy.cables.BlockCable;
import net.minecraft.client.render.RenderBlocks;
import net.minecraft.core.world.WorldSource;
import sunsetsatellite.catalyst.energy.api.IEnergy;

public class CableModel extends RenderBlocks {

    public static boolean renderCable(RenderBlocks renderBlocks, WorldSource blockAccess, BlockCable blockCable, int x, int y, int z) {
        float boundMin = 0.375f;
        float boundMax = 0.625f;

        boolean aPosX = blockAccess.getBlockId(x + 1, y, z) == blockCable.id ||
                blockAccess.getBlockTileEntity(x + 1, y, z) instanceof IEnergy;

        boolean aNegX = blockAccess.getBlockId(x - 1, y, z) == blockCable.id ||
                blockAccess.getBlockTileEntity(x - 1, y, z) instanceof IEnergy;

        boolean aPosY = blockAccess.getBlockId(x, y + 1, z) == blockCable.id ||
                blockAccess.getBlockTileEntity(x, y + 1, z) instanceof IEnergy;

        boolean aNegY = blockAccess.getBlockId(x, y - 1, z) == blockCable.id ||
                blockAccess.getBlockTileEntity(x, y - 1, z) instanceof IEnergy;

        boolean aPosZ = blockAccess.getBlockId(x, y, z + 1) == blockCable.id ||
                blockAccess.getBlockTileEntity(x, y, z + 1) instanceof IEnergy;

        boolean aNegZ = blockAccess.getBlockId(x, y, z - 1) == blockCable.id ||
                blockAccess.getBlockTileEntity(x, y, z - 1) instanceof IEnergy;

        blockCable.setBlockBounds(boundMin - 0.0001f, boundMin - 0.0001f, boundMin - 0.0001f, boundMax + 0.0001f, boundMax + 0.0001f, boundMax + 0.0001f);

        renderBlocks.renderStandardBlock(blockCable, x, y, z);

        if (aPosX || aNegX) {
            blockCable.setBlockBounds(
                    (float) (0.5 + (aNegX ? -0.5f : 0.0f)), boundMin, boundMin,
                    (float) (0.5 + (aPosX ? 0.5f : 0.0f)), boundMax, boundMax
            );
            renderBlocks.renderStandardBlock(blockCable, x, y, z);
        }

        if (aPosY || aNegY) {
            blockCable.setBlockBounds(
                    boundMin, (float) (0.5 + (aNegY ? -0.5f : 0.0f)), boundMin,
                    boundMax, (float) (0.5 + (aPosY ? 0.5f : 0.0f)), boundMax
            );
            renderBlocks.renderStandardBlock(blockCable, x, y, z);
        }
        if (aPosZ || aNegZ) {
            blockCable.setBlockBounds(
                    boundMin, boundMin, (float) (0.5 + (aNegZ ? -0.5f : 0.0f)),
                    boundMax, boundMax, (float) (0.5 + (aPosZ ? 0.5f : 0.0f))
            );
            renderBlocks.renderStandardBlock(blockCable, x, y, z);
            }

        blockCable.setBlockBounds(0.15f, 0.15f, 0.15f, 0.85f, 0.85f, 0.85f);

        return true;
    }
}
