package baboon.industry.block.reactor;

import baboon.industry.block.reactor.entity.TileEntityReactorNewer;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import sunsetsatellite.catalyst.Catalyst;

public class BlockReactor extends BlockTileEntity {
    public boolean halfHeat;

    public BlockReactor(String key, int id, boolean halfHeat) {
        super(key, id, Material.metal);
        this.halfHeat = halfHeat;
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityReactorNewer();
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isClientSide) {
            TileEntityReactorNewer tile = (TileEntityReactorNewer) world.getBlockTileEntity(x, y, z);
            if (!tile.isAssembled())
                return false;
            Catalyst.displayGui(player, tile, tile.getInvName());
        }
        return true;
    }

    private void dropContents(World world, int x, int y, int z) {
        TileEntityReactorNewer tileEntity = (TileEntityReactorNewer) world.getBlockTileEntity(x, y, z);
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
    public void onBlockRemoved(World world, int x, int y, int z, int data) {
        dropContents(world, x, y, z);
        super.onBlockRemoved(world, x, y, z, data);
    }
}
