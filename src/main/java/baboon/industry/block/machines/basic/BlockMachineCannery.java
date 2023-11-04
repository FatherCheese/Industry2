package baboon.industry.block.machines.basic;

import baboon.industry.block.machines.basic.entity.TileEntityMachineCannery;
import net.minecraft.core.block.BlockTileEntityRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import sunsetsatellite.energyapi.interfaces.mixins.IEntityPlayer;

public class BlockMachineCannery extends BlockTileEntityRotatable {

    public BlockMachineCannery(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityMachineCannery();
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isClientSide) {
            TileEntityMachineCannery tileEntity = (TileEntityMachineCannery) world.getBlockTileEntity(x, y, z);

            if (tileEntity == null)
                return false;
            ((IEntityPlayer) player).displayGuiScreen_energyapi(tileEntity);
        }
        return true;
    }
}
