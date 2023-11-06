package baboon.industry.block.reactor;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.block.reactor.entity.TileEntityReactorChamber;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import sunsetsatellite.energyapi.interfaces.mixins.IEntityPlayer;

import java.util.Random;

public class BlockReactorChamber extends BlockTileEntity {
    private int reactorCount = 0;

    public BlockReactorChamber(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityReactorChamber();
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isClientSide) {
            TileEntityReactorChamber tileEntity = (TileEntityReactorChamber) world.getBlockTileEntity(x, y, z);

            if (tileEntity == null)
                return false;

            ((IEntityPlayer) player).displayGuiScreen_energyapi(tileEntity);
        }
        return true;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);
    }
}
