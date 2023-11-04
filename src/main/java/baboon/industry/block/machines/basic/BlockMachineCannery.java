package baboon.industry.block.machines.basic;

import baboon.industry.block.machines.basic.entity.TileEntityMachineBase;
import baboon.industry.block.machines.basic.entity.TileEntityMachineCannery;
import net.minecraft.core.block.BlockTileEntityRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
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

    private void dropContents(World world, int x, int y, int z) {
        TileEntityMachineCannery tileEntity = (TileEntityMachineCannery) world.getBlockTileEntity(x, y, z);
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
