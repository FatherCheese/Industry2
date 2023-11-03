package baboon.industry.block.generator;

import baboon.industry.block.generator.entity.TileEntityGeneratorWatermill;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import sunsetsatellite.energyapi.interfaces.mixins.IEntityPlayer;

public class BlockGeneratorWatermill extends BlockTileEntity {

    public BlockGeneratorWatermill(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityGeneratorWatermill();
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isClientSide) {
            TileEntityGeneratorWatermill tileEntity = (TileEntityGeneratorWatermill) world.getBlockTileEntity(x, y, z);

            if (tileEntity == null)
                return false;
            ((IEntityPlayer) player).displayGuiScreen_energyapi(tileEntity);
        }
        return true;
    }
}
