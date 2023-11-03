package baboon.industry.block.generator;

import baboon.industry.block.generator.entity.TileEntityGeneratorWindmill;
import net.minecraft.core.block.BlockTileEntityRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import sunsetsatellite.energyapi.interfaces.mixins.IEntityPlayer;

public class BlockGeneratorWindmill extends BlockTileEntityRotatable {

    public BlockGeneratorWindmill(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityGeneratorWindmill();
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isClientSide) {
            TileEntityGeneratorWindmill tileEntity = (TileEntityGeneratorWindmill) world.getBlockTileEntity(x, y, z);

            if (tileEntity == null)
                return false;
            ((IEntityPlayer) player).displayGuiScreen_energyapi(tileEntity);
        }
        return true;
    }
}
