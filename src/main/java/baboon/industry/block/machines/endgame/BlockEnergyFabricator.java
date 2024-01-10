package baboon.industry.block.machines.endgame;

import baboon.industry.block.IndustryBlocks;
import baboon.industry.block.machines.endgame.entity.TileEntityEnergyFabricator;
import net.minecraft.core.block.BlockTileEntityRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import sunsetsatellite.catalyst.Catalyst;

public class BlockEnergyFabricator extends BlockTileEntityRotatable {

    public BlockEnergyFabricator(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    protected TileEntity getNewBlockEntity() {
        return new TileEntityEnergyFabricator();
    }

    private void dropContents(World world, int x, int y, int z) {
        TileEntityEnergyFabricator tileEntity = (TileEntityEnergyFabricator) world.getBlockTileEntity(x, y, z);
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
    public void onBlockRemoved(World world, int x, int y, int z, int meta) {
        dropContents(world, x, y, z);
        super.onBlockRemoved(world, x, y, z, meta);
    }

    @Override
    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isClientSide) {
            TileEntityEnergyFabricator tileEntity = (TileEntityEnergyFabricator) world.getBlockTileEntity(x, y, z);

            if (tileEntity == null)
                return false;

            Catalyst.displayGui(player, tileEntity, tileEntity.getInvName());
        }
        return true;
    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
        if (dropCause == EnumDropCause.PICK_BLOCK || dropCause == EnumDropCause.PROPER_TOOL)
            return new ItemStack[]{new ItemStack(this)};
        else
            return new ItemStack[]{new ItemStack(IndustryBlocks.nuclearChamber)};
    }
}
