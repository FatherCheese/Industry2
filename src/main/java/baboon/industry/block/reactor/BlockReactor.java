package baboon.industry.block.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactorNew;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import sunsetsatellite.energyapi.interfaces.mixins.IEntityPlayer;

import java.util.Random;

public class BlockReactor extends BlockTileEntity {
    private final Random random = new Random();
    public BlockReactor(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityReactorNew();
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isClientSide) {
            TileEntityReactorNew tile = (TileEntityReactorNew) world.getBlockTileEntity(x, y, z);
            if (!tile.isAssembled())
                return false;
            ((IEntityPlayer) player).displayGuiScreen_energyapi(tile);
        }
        return true;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
        TileEntityReactorNew tile = (TileEntityReactorNew)world.getBlockTileEntity(x, y, z);
        tile.isDisabled = (world.isBlockGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y, z));
    }
}
