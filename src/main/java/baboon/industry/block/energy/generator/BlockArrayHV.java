package baboon.industry.block.energy.generator;

import baboon.industry.block.energy.generator.entity.TileEntityArrayHV;
import baboon.industry.block.energy.generator.entity.TileEntitySolarBase;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import sunsetsatellite.catalyst.Catalyst;

public class BlockArrayHV extends BlockTileEntity {

    public BlockArrayHV(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityArrayHV();
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isClientSide) {
            TileEntitySolarBase tileEntity = (TileEntitySolarBase) world.getBlockTileEntity(x, y, z);

            if (tileEntity == null)
                return false;
            Catalyst.displayGui(player, tileEntity, tileEntity.getInvName());
        }
        return true;
    }

    private void dropContents(World world, int x, int y, int z) {
        TileEntitySolarBase tileEntity = (TileEntitySolarBase) world.getBlockTileEntity(x, y, z);
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
        this.dropContents(world, x, y, z);
        super.onBlockRemoved(world, x, y, z, data);
    }
}
