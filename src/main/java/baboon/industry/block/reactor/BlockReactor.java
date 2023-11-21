package baboon.industry.block.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactorNew;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
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

    private void dropContents(World world, int x, int y, int z) {
        TileEntityReactorNew tileEntity = (TileEntityReactorNew) world.getBlockTileEntity(x, y, z);
        if (tileEntity == null)
            System.out.println("Can't drop inventory at X: " + x + " Y: " + y + " Z: " + z + " because TileEntity is null");
        else {
            for (int i = 0; i < tileEntity.getSizeInventory(); ++i) {
                ItemStack itemStack = tileEntity.getStackInSlot(i);
                if (itemStack != null) {
                    EntityItem item = world.dropItem(x, y, z, itemStack);
                    item.xd *= 0.5;
                    item.yd *= 0.5;
                    item.zd *= 0.5;
                    item.delayBeforeCanPickup = 0;
                }
            }
        }
    }

    @Override
    public void onBlockRemoval(World world, int x, int y, int z) {
        dropContents(world, x, y, z);
        super.onBlockRemoval(world, x, y, z);
    }
}
